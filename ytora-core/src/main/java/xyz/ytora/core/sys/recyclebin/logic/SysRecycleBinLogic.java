package xyz.ytora.core.sys.recyclebin.logic;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import xyz.ytora.base.mvc.basemodel.BaseLogic;
import xyz.ytora.base.scope.ScopedValueContext;
import xyz.ytora.core.sys.recyclebin.model.entity.SysRecycleBin;
import xyz.ytora.core.sys.recyclebin.repo.SysRecycleBinRepo;
import xyz.ytora.sqlux.core.anno.Table;
import xyz.ytora.sqlux.orm.type.Json;
import xyz.ytora.sqlux.util.NamedUtil;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 回收站模块的业务逻辑层
 *
 * @author 杨桐
 * @since 1.0-SNAPSHOT
 */
@Service
@AllArgsConstructor
public class SysRecycleBinLogic extends BaseLogic<SysRecycleBin, SysRecycleBinRepo> {

    /**
     * 执行数据备份
     * @param clazz 目标表
     * @param rows 数据行
     */
    public void saveDeleteBackup(Class<?> clazz, List<Map<String, Object>> rows) {
        // 用户名
        String userName = ScopedValueContext.LOGIN_USER.isBound() ? ScopedValueContext.LOGIN_USER.get().getUserName() : null;
        // 目标表名称
        String tableName = NamedUtil.parseTableName(clazz);
        // 主键名称
        String keyName = null;
        Table tableAnno = clazz.getAnnotation(Table.class);
        if (tableAnno != null) {
            String[] keyArr = tableAnno.key();
            if (keyArr != null && keyArr.length > 0) {
                keyName = tableAnno.key()[0];
            }
        }
        // 没有指定主键，就使用默认主键
        if (keyName == null) {
            keyName = "id";
        }

        List<SysRecycleBin> recycleBins = new ArrayList<>();
        for (Map<String, Object> row : rows) {
            SysRecycleBin recycleBin = new SysRecycleBin();
            recycleBin.setDeletedBy(userName);
            recycleBin.setDeletedTime(LocalDateTime.now());
            recycleBin.setOriginalTable(tableName);
            recycleBin.setOriginalId(String.valueOf(row.get(keyName)));
            recycleBin.setOriginalData(Json.of(row));
            recycleBins.add(recycleBin);
        }
        repository.upsertBatch(recycleBins);

    }
}
