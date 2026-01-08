package xyz.ytora.core.sys.recyclebin.logic;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import xyz.ytora.base.exception.BaseException;
import xyz.ytora.base.mvc.BaseApi;
import xyz.ytora.core.sys.recyclebin.model.entity.SysRecycleBin;
import xyz.ytora.core.sys.recyclebin.repo.SysRecycleBinRepo;
import xyz.ytora.sql4j.core.SQLHelper;
import xyz.ytora.sql4j.enums.OrderType;
import xyz.ytora.sql4j.func.support.Raw;
import xyz.ytora.sql4j.orm.Page;
import xyz.ytora.sql4j.sql.ConditionExpressionBuilder;
import xyz.ytora.sql4j.sql.select.SelectBuilder;
import xyz.ytora.ytool.json.JSON;
import xyz.ytora.ytool.str.Strs;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
@RequiredArgsConstructor
public class SysRecycleBinLogic {

    private final SQLHelper sqlHelper;
    private final SysRecycleBinRepo recycleBinRepo;

    /**
     * 操作符：长的放前面,目前只考虑下面6种情况
     */
    private static final String OPS = "(<>|!=|<=|>=|=|LIKE)";

    /**
     * left op right
     */
    private static final Pattern COND = Pattern.compile(
            "^\\s*([a-zA-Z_][\\w\\.]*?)\\s*" + OPS + "\\s*(.+?)\\s*$",
            Pattern.CASE_INSENSITIVE
    );

    // sys_recycle_bin 表的“普通列”，不走 original_data->> 改写
    private static final Set<String> PLAIN_COLUMNS = new HashSet<>(List.of(
            "original_table"
    ));

    /**
     * 分页查询指定数据库表的删除数据
     */
    public Page<Map<String, Object>> page(String originalTable, Integer pageNo, Integer pageSize) {
        // 根据表名称获取实体类
        Map<String, Class<?>> tableClassMapper = sqlHelper.getTableCreatorManager().getTableClassMapper();
        Class<?> entity = tableClassMapper.get(originalTable);
        if (entity == null) {
            throw new BaseException("请确定 {} 有对应的实体类", originalTable);
        }

        SelectBuilder selectBuilder = BaseApi.query();
        ConditionExpressionBuilder where = selectBuilder.getWhereStage().getWhere();
        // 对 WHERE 进行改写
        String whereSql = rewriteWhereForRecycleBin(where.build());
        // 查询数据总量
        List<Map<String, Object>> countList = sqlHelper.select(Raw.of("count(1)").as("count")).from(SysRecycleBin.class).where(w -> w.apply(whereSql, where.getParams())).submit();
        Map<String, Object> firstRow = countList.getFirst();
        long count = (long) firstRow.get("count");

        // 查询数据
        int limit = pageSize;
        int offset = (pageNo - 1) * pageSize;
        List<SysRecycleBin> list = sqlHelper.select(SysRecycleBin.class).from(SysRecycleBin.class)
                .where(w -> w.apply(whereSql, where.getParams())).orderBy(Raw.of("deleted_time"), OrderType.DESC)
                .limit(limit)
                .offset(offset)
                .submit(SysRecycleBin.class);

        // 封装返回结果
        Page<Map<String, Object>> page = new Page<>(pageNo, pageSize);
        List<Map<String, Object>> sourceDataList = new ArrayList<>();
        for (SysRecycleBin item : list) {
            JSON originalData = item.getOriginalData();
            Map<String, Object> data = new HashMap<>();
            data.put("binId", item.getId());
            data.put("deleteReason", item.getDeleteReason());
            data.put("deletedBy", item.getDeletedBy());
            data.put("deletedTime", item.getDeletedTime());
            for (String key : originalData.keySet()) {
                data.put(Strs.toLowerCamelCase(key), originalData.get(key));
            }
            sourceDataList.add(data);
        }

        page.setRecords(sourceDataList);
        page.setTotal(count);
        page.setPages((int) ((count + pageSize - 1) / pageSize));
        return page;
    }

    /**
     * 还原数据
     */
    @Transactional(rollbackFor = Exception.class)
    public void restore(String ids) {
        List<String> idList = Arrays.stream(ids.split(",")).toList();
        List<SysRecycleBin> recycleBinList = sqlHelper.select(SysRecycleBin::getId, SysRecycleBin::getOriginalTable, SysRecycleBin::getOriginalData, SysRecycleBin::getRestoreSql).from(SysRecycleBin.class).where(w -> w.in(SysRecycleBin::getId, idList)).submit(SysRecycleBin.class);
        for (SysRecycleBin sysRecycleBin : recycleBinList) {
            String restoreSql = sysRecycleBin.getRestoreSql();
            sqlHelper.submitSQL(restoreSql);
            sqlHelper.submitSQL(Strs.format("DELETE FROM sys_recycle_bin WHERE id = {}", sysRecycleBin.getId()));
        }

    }

    /**
     * 彻底删除
     */
    public void deleteCompletely(String ids) {
        List<String> idList = Arrays.stream(ids.split(",")).toList();
        sqlHelper.delete().from(SysRecycleBin.class).where(w -> w.in(SysRecycleBin::getId, idList)).submit();
    }

    /**
     * 清空指定表的回收站数据
     * @param table
     */
    public void clear(String table) {
        sqlHelper.delete().from(SysRecycleBin.class).where(w -> w.eq(SysRecycleBin::getOriginalTable, table)).submit();
    }

    /**
     * 将简单 where 片段（AND 连接）改写为回收站查询片段：
     * - 普通列（如 table_name）保持不变
     * - 其他字段统一改写为 original_data->>'field'
     * 例：
     *  "table_name = ? AND age >= ? AND name LIKE ?"
     *      =>"table_name = ? AND original_data->>'age' >= ? AND original_data->>'name' LIKE ?"
     */
    private String rewriteWhereForRecycleBin(String whereFragment) {
        if (whereFragment == null || whereFragment.trim().isEmpty()) return whereFragment;

        // 按顶层 AND 拆（假设片段里 AND 只作为连接符）
        String[] parts = whereFragment.split("(?i)\\s+AND\\s+");

        List<String> rewritten = new ArrayList<>(parts.length);

        for (String part : parts) {
            String p = part.trim();

            Matcher m = COND.matcher(p);
            if (!m.matches()) {
                // 不符合 "left op right" 的条件，保守原样返回
                rewritten.add(p);
                continue;
            }

            String left = m.group(1).trim();
            String op = m.group(2).trim();
            String right = m.group(3).trim();

            // left 可能是 t.age / schema.t.age -> 取最后一段作为字段名
            String field = left;
            int dot = field.lastIndexOf('.');
            if (dot >= 0) field = field.substring(dot + 1);
            field = field.trim();

            // 普通列不改写（如 table_name）
            if (PLAIN_COLUMNS.contains(field.toLowerCase(Locale.ROOT))) {
                rewritten.add(left + " " + op + " " + right);
                continue;
            }

            // 其他一律走 original_data
            String newLeft = "original_data->>'" + field + "'";
            rewritten.add(newLeft + " " + op + " " + right);
        }

        return String.join(" AND ", rewritten);
    }

}
