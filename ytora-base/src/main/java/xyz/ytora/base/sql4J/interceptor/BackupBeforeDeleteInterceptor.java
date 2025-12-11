package xyz.ytora.base.sql4J.interceptor;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import xyz.ytora.base.scope.ScopedValueItem;
import xyz.ytora.sql4j.anno.Table;
import xyz.ytora.sql4j.enums.SqlType;
import xyz.ytora.sql4j.interceptor.SqlInterceptorAdapter;
import xyz.ytora.sql4j.sql.ConditionExpressionBuilder;
import xyz.ytora.sql4j.sql.SqlInfo;
import xyz.ytora.sql4j.sql.delete.DeleteBuilder;
import xyz.ytora.sql4j.sql.delete.DeleteWhereStage;
import xyz.ytora.ytool.classcache.ClassCache;
import xyz.ytora.ytool.classcache.classmeta.ClassMetadata;
import xyz.ytora.ytool.json.Jsons;
import xyz.ytora.ytool.str.Strs;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 数据删除前备份
 */
@Component
@RequiredArgsConstructor
public class BackupBeforeDeleteInterceptor extends SqlInterceptorAdapter {

    @Override
    public Boolean before(SqlInfo sqlInfo) {
        if (sqlInfo.getSqlType().equals(SqlType.DELETE)) {
            String deletedBy;
            try {
                deletedBy = ScopedValueItem.LOGIN_USER.get().getUserName();
            } catch (Exception e) {
                deletedBy = "-";
            }
            LocalDateTime now = LocalDateTime.now();

            DeleteBuilder deleteBuilder = (DeleteBuilder) sqlInfo.getSqlBuilder();
            Class<?> tableClass = deleteBuilder.getFromStage().getTableClass();
            if (tableClass == null) {
                return true;
            }
            ClassMetadata<?> metadata = ClassCache.get(tableClass);
            Table tableAnno = metadata.getAnnotation(Table.class);
            if (tableAnno == null || !tableAnno.backupOnDelete()) {
                return true;
            }

            DeleteWhereStage whereStage = deleteBuilder.getWhereStage();
            if (whereStage != null && whereStage.getWhereExpression() != null) {
                ConditionExpressionBuilder whereExpression = whereStage.getWhereExpression();
                if (whereExpression != null && Strs.isNotEmpty(whereExpression.build())) {
                    String tableName = deleteBuilder.getFromStage().getTableName();
                    String querySourceDataSql = "SELECT * FROM " + tableName + " WHERE " + whereExpression.build();
                    List<Map<String, Object>> deleteData = sqlInfo.getSqlBuilder().getSQLHelper().submitSQL(querySourceDataSql, whereExpression.getParams()).toBeans();
                    if (deleteData.isEmpty()) {
                        return true;
                    }
                    StringBuilder sql = new StringBuilder("INSERT INTO sys_recycle_bin (deleted_by, deleted_time, delete_reason, original_table, original_id, original_data) VALUES ");
                    List<Object> params = new ArrayList<>();

                    for (int i = 0; i < deleteData.size(); i++) {
                        Map<String, Object> deleteDatum = deleteData.get(i);
                        // 主键的字段名称固定位 id
                        Object id = deleteDatum.get("id");
                        String jsonStr = Jsons.toJsonStr(deleteDatum);
                        params.add(deletedBy);
                        params.add(now);
                        params.add("-");
                        params.add(tableName);
                        params.add(id);
                        params.add(jsonStr);
                        sql.append("(?,?,?,?,?,?::jsonb)");
                        if (i < deleteData.size() - 1) {
                            sql.append(",");
                        }
                    }

                    sqlInfo.getSqlBuilder().getSQLHelper().submitSQL(sql.toString(), params);
                }
            }
        }
        return true;
    }
}
