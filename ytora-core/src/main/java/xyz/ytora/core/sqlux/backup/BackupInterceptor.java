package xyz.ytora.core.sqlux.backup;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import xyz.ytora.core.sys.log.LogType;
import xyz.ytora.core.sys.log.logic.SysLogLogic;
import xyz.ytora.core.sys.log.model.entity.SysLog;
import xyz.ytora.core.sys.recyclebin.logic.SysRecycleBinLogic;
import xyz.ytora.sqlux.core.SQL;
import xyz.ytora.sqlux.core.enums.SqlType;
import xyz.ytora.sqlux.interceptor.Interceptor;
import xyz.ytora.sqlux.interceptor.SqlExecutionContext;
import xyz.ytora.sqlux.orm.type.Text;
import xyz.ytora.sqlux.sql.model.*;
import xyz.ytora.sqlux.translate.DialectFactory;
import xyz.ytora.sqlux.translate.SqlResult;
import xyz.ytora.toolkit.collection.Colls;
import xyz.ytora.toolkit.id.Ids;
import xyz.ytora.toolkit.text.Strs;

import java.util.List;
import java.util.Map;

/**
 * 数据备份拦截器。
 * <p>
 * 在删除前，判断即将被删除的表有没有标注注解{@link BackupOnDelete}
 * <br/>
 * 如果标注了，需要对即将删除的数据进行备份
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class BackupInterceptor implements Interceptor {

    private static final String ATTR_BACKUP_ROWS = "backup.delete.rows";
    private final SysLogLogic logLogic;
    private final SysRecycleBinLogic recycleBinLogic;

    @Override
    public void beforeExecute(SqlExecutionContext context) {
        if (!SqlType.DELETE.equals(context.getSqlType())) {
            return;
        }

        DeleteQuery deleteQuery = getSupportedDeleteQuery(context);
        if (deleteQuery == null) {
            return;
        }

        Class<?> entityClass = deleteQuery.getFrom().getTableClass();
        BackupOnDelete backupOnDelete = entityClass.getAnnotation(BackupOnDelete.class);
        if (backupOnDelete == null) {
            return;
        }

        // 根据删除条件，查询数据，进行备份
        List<Map<String, Object>> rows = loadRowsBeforeDelete(deleteQuery);
        // 避免数据量太大，备份前检查数据库是否超过阈值，超过了就不进行备份
        if (rows.size() > backupOnDelete.value()) {
            log.error("数据量太大：[{}]，超过了阈值：[{}]，跳过数据备份", rows.size(), backupOnDelete.value());
            logLogic.doLog(new SysLog()
                    .setType(LogType.ERROR_LOG)
                    .setTraceId(Ids.nextUuid())
                    .setThread(Thread.currentThread().getName())
                    .setContent(Text.of(Strs.format("数据量太大：[{}]，超过了阈值：[{}]，跳过数据备份", rows.size(), backupOnDelete.value())))
            );
            return;
        }
        context.getAttributes().put(ATTR_BACKUP_ROWS, rows);
    }

    @Override
    public void afterSuccess(SqlExecutionContext context) {
        if (!SqlType.DELETE.equals(context.getSqlType())) {
            return;
        }

        DeleteQuery deleteQuery = getSupportedDeleteQuery(context);
        if (deleteQuery == null) {
            return;
        }

        @SuppressWarnings("unchecked")
        List<Map<String, Object>> rows =
                (List<Map<String, Object>>) context.getAttributes().get(ATTR_BACKUP_ROWS);

        if (Colls.isEmpty(rows)) {
            return;
        }

        TableRef from = deleteQuery.getFrom();
        Class<?> entityClass = from.getTableClass();
        BackupOnDelete backupOnDelete = entityClass.getAnnotation(BackupOnDelete.class);
        if (backupOnDelete == null) {
            return;
        }

        // 备份数据
        recycleBinLogic.saveDeleteBackup(entityClass, rows);
    }

    @Override
    public void afterFailure(SqlExecutionContext context) {
        context.getAttributes().remove(ATTR_BACKUP_ROWS);
    }

    @Override
    public void afterFinally(SqlExecutionContext context) {
        context.getAttributes().remove(ATTR_BACKUP_ROWS);
    }

    private DeleteQuery getSupportedDeleteQuery(SqlExecutionContext context) {
        Object statement = context.getStatement();
        if (!(statement instanceof DeleteQuery deleteQuery)) {
            return null;
        }

        if (deleteQuery.getFrom() == null) {
            return null;
        }

        if (Colls.isNotEmpty(deleteQuery.getJoins())) {
            return null;
        }

        return deleteQuery;
    }

    private List<Map<String, Object>> loadRowsBeforeDelete(DeleteQuery deleteQuery) {
        SelectQuery selectQuery = new SelectQuery();
        selectQuery.getContextHolder().copyFrom(deleteQuery.getContextHolder());
        selectQuery.setFrom(deleteQuery.getFrom());
        selectQuery.setWhere(deleteQuery.getWhere());

        for (JoinClause join : deleteQuery.getJoins()) {
            selectQuery.addJoin(join);
        }

        selectQuery.addSelectColumn(TableWildcard.of(deleteQuery.getFrom().getTableClass()));

        SqlResult sqlResult = DialectFactory.getDialect(SQL.getSqluxGlobal().getDbType())
                .selectTranslator()
                .translate(selectQuery);

        // 查询数据（不走拦截器）
        return SQL.getSqluxGlobal().getExecutor().queryWithoutInterceptors(sqlResult);
    }
}