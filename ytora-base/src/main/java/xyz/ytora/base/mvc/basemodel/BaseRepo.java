package xyz.ytora.base.mvc.basemodel;

import lombok.Getter;
import xyz.ytora.base.exception.BaseException;
import xyz.ytora.sqlux.core.SQL;
import xyz.ytora.sqlux.orm.AbsEntity;
import xyz.ytora.sqlux.orm.IRepo;
import xyz.ytora.sqlux.orm.Page;
import xyz.ytora.sqlux.sql.condition.ExpressionBuilder;
import xyz.ytora.sqlux.sql.func.ColFunction;
import xyz.ytora.sqlux.translate.SqlResult;
import xyz.ytora.toolkit.collection.Colls;
import xyz.ytora.toolkit.text.Strs;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;

import static xyz.ytora.sqlux.core.SQL.*;

/**
 * 通用的数据持久层
 *
 * @author ytora
 * @since 1.0
 */
@Getter
public abstract class BaseRepo<T extends AbsEntity> implements IRepo<T> {

    private final Class<T> entityClazz;

    @SuppressWarnings("unchecked")
    public BaseRepo() {
        Type superClass = getClass().getGenericSuperclass();
        if (superClass instanceof ParameterizedType pt) {
            Type actualType = pt.getActualTypeArguments()[0];
            if (actualType instanceof Class<?> actualClazz
                    && AbsEntity.class.isAssignableFrom(actualClazz)) {
                this.entityClazz = (Class<T>) actualClazz;
                return;
            }
        }
        throw new IllegalArgumentException("Repo类的泛型必须实现 AbsEntity");
    }

    /**
     * 根据实体类条件进行分页查询
     *
     * @param entity 查询条件
     * @param pageNo 页码
     * @param pageSize 页尺寸
     * @return 分页对象
     */
    public Page<T> page(T entity, Integer pageNo, Integer pageSize) {
        return select(entityClazz)
                .from(entityClazz)
                .where(entity)
                .submit(Page.of(pageNo, pageSize));
    }

    /**
     * 根据查询条件进行分页查询
     *
     * @param whereExpr where条件表达式
     * @param pageNo 页码
     * @param pageSize 页尺寸
     * @return 分页对象
     */
    public Page<T> page(Consumer<ExpressionBuilder> whereExpr, Integer pageNo, Integer pageSize) {
        return select(entityClazz)
                .from(entityClazz)
                .where(whereExpr)
                .submit(Page.of(pageNo, pageSize));
    }

    /**
     * 根据实体类条件进行列表查询
     *
     * @param entity 查询条件
     * @return 列表对象
     */
    public List<T> list(T entity) {
        return select(entityClazz)
                .from(entityClazz)
                .where(entity)
                .submit(entityClazz);
    }

    /**
     * 根据查询条件进行列表查询
     *
     * @param whereExpr 查询条件
     * @return 列表对象
     */
    public List<T> list(Consumer<ExpressionBuilder> whereExpr) {
        return select(entityClazz)
                .from(entityClazz)
                .where(whereExpr)
                .submit(entityClazz);
    }

    /**
     * 根据实体类条件进行唯一数据
     * @param entity 查询条件
     * @return 数据
     */
    public T one(T entity) {
        SqlResult sqlResult = select().from(entityClazz)
                .where(entity)
                .toSql();
        List<T> list = SQL.getSqluxGlobal().getExecutor().query(sqlResult, entityClazz);
        if (list.isEmpty()) {
            return null;
        } else if (list.size() == 1) {
            return list.getFirst();
        } else {
            throw new BaseException("SQL执行失败：{}, 参数:{},期待唯一数据，但实际查到：{}",
                    sqlResult.getSql(),
                    sqlResult.getParams(),
                    list.size()
            );
        }
    }

    /**
     * 根据查询条件查询唯一数据
     *
     * @param whereExpr 查询条件
     * @return 数据
     */
    public T one(Consumer<ExpressionBuilder> whereExpr) {
        SqlResult sqlResult = select().from(entityClazz)
                .where(whereExpr)
                .toSql();
        List<T> list = SQL.getSqluxGlobal().getExecutor().query(sqlResult, entityClazz);
        if (list.isEmpty()) {
            return null;
        } else if (list.size() == 1) {
            return list.getFirst();
        } else {
            throw new BaseException("SQL执行失败：{}, 参数:{},期待唯一数据，但实际查到：{}",
                    sqlResult.getSql(),
                    sqlResult.getParams(),
                    list.size()
            );
        }
    }

    /**
     * 根据ID查询唯一数据
     *
     * @param id 主键ID
     * @return 对应的数据
     */
    public T queryById(String id) {
        Optional<T> optional = select().from(entityClazz)
                .where(w -> w.eq((ColFunction<AbsEntity, ?>) AbsEntity::getId, id))
                .submit(entityClazz, 0);
        return optional.orElse(null);
    }

    /**
     * 根据ID查询唯一数据
     *
     * @param ids 主键ID数组
     * @return 对应的数据
     */
    public List<T> queryById(List<String> ids) {
        return select().from(entityClazz)
                .where(w -> w.in((ColFunction<AbsEntity, ?>) AbsEntity::getId, ids))
                .submit(entityClazz);
    }

    /**
     * 根据字符串形式的ID数组查询指定数据
     *
     * @param ids 主键ID数组，多个ID以逗号分隔
     * @return 对应的数据
     */
    public List<T> queryByIds(String ids) {
        if (Strs.isEmpty(ids)) {
            return Collections.emptyList();
        }
        List<String> idList = Arrays.asList(ids.split(","));
        return queryByIds(idList);
    }

    /**
     * 根据ID数组查询指定数据
     *
     * @param ids 主键ID数组
     * @return 对应的数据
     */
    public List<T> queryByIds(Iterable<?> ids) {
        return select().from(entityClazz)
                .where(w -> w.in((ColFunction<AbsEntity, ?>) AbsEntity::getId, ids))
                .submit(entityClazz);
    }

    /**
     * 新增或编辑数据
     * @param entity 提交的数据
     * @return 处理结果
     */
    public String upsert(T entity) {
        int affectRows;
        if (entity.getId() == null) {
            affectRows = insert(entityClazz)
                    .into()
                    .values(entity)
                    .submit();
            return affectRows > 0 ? "新增成功" : "新增失败";
        } else {
            affectRows = update(entityClazz)
                    .set(entity)
                    .where(on -> on.eq((ColFunction<AbsEntity, ?>) AbsEntity::getId, entity.getId()))
                    .submit();
            return affectRows > 0 ? "编辑成功" : "编辑失败";
        }
    }

    /**
     * 批量新增或编辑数据
     * @param entities 提交的数据
     * @return 处理结果
     */
    public String upsertBatch(List<T> entities) {
        // 判断哪些数据需要新增，哪些数据需要编辑
        List<T> insertList = entities.stream().filter(i -> Strs.isEmpty(i.getId())).toList();
        List<T> updateList = entities.stream().filter(i -> Strs.isNotEmpty(i.getId())).toList();

        // 批量新增
        if (Colls.isNotEmpty(insertList)) {
            insert(entityClazz)
                    .into()
                    .values(insertList)
                    .submitBatch();

        }
        // 批量编辑
        if (Colls.isNotEmpty(updateList)) {
            update(entityClazz)
                    .set(updateList)
                    .submitBatch();
        }
        return "批量修改成功";
    }

    /**
     * 根据ID删除数据
     *
     * @param ids 要删除的数据id，可以传多个，多个id以逗号分隔，例如：{@code "1,2,3"}
     * @return 影响的数据行数
     */
    public int deleteByIds(String ids) {
        List<String> idList = Arrays.asList(ids.split(","));
        int affectRows;
        if (idList.isEmpty()) {
            affectRows = 0;
            return affectRows;
        } else if (idList.size() == 1) {
            affectRows = SQL.delete()
                    .from(entityClazz)
                    .where(w -> w.eq((ColFunction<AbsEntity, ?>) AbsEntity::getId, idList.getFirst()))
                    .submit();
        } else {
            affectRows = SQL.delete()
                    .from(entityClazz)
                    .where(w -> w.in((ColFunction<AbsEntity, ?>) AbsEntity::getId, idList))
                    .submit();
        }

        return affectRows;
    }

    /**
     * 根据实体类条件删除数据
     *
     * @param entity 实体类条件
     * @return 影响行数
     */
    public int delete(T entity) {
        return SQL.delete()
                .from(entityClazz)
                .where(entity)
                .submit();
    }

    /**
     * 根据指定的条件删除数据
     *
     * @param whereExpr 删除条件
     * @return 影响行数
     */
    public int delete(Consumer<ExpressionBuilder> whereExpr) {
        return SQL.delete()
                .from(entityClazz)
                .where(whereExpr)
                .submit();
    }

}
