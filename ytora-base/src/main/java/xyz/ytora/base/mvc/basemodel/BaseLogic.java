package xyz.ytora.base.mvc.basemodel;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import xyz.ytora.base.util.HttpUtil;
import xyz.ytora.base.util.Pages;
import xyz.ytora.sqlux.orm.AbsEntity;
import xyz.ytora.sqlux.orm.Page;
import xyz.ytora.sqlux.query.SqluxQuery;
import xyz.ytora.sqlux.sql.model.SelectQuery;
import xyz.ytora.sqlux.sql.stage.select.AbsSelect;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

/**
 * 通用的逻辑层
 *
 * @author ytora
 * @since 1.0
 */
public abstract class BaseLogic<T extends AbsEntity, R extends BaseRepo<T>> {

    protected R repository;

    @Autowired
    @SuppressWarnings("unchecked")
    public void setRepository(R repository) {
        this.repository = repository;
    }

    /**
     * 默认的分页查询
     * @param param 查询参数
     * @return 分页对象Page
     */
    public Page<T> page(BaseParam<T> param) {
        return page(param, null);
    }

    /**
     * 默认的分页查询
     * @param param 查询参数
     * @param consumer 对默认select的自定义改造
     * @return 分页对象Page
     */
    public Page<T> page(BaseParam<T> param, Consumer<SelectQuery> consumer) {
        Page<T> page = Pages.<T>getPage();
        AbsSelect select = installSelect();
        if (consumer != null) {
            consumer.accept(select.getQuery());
        }
        return select.submit(page);
    }

    /**
     * 默认的列表查询
     * @param param 查询参数
     * @return 分页对象Page
     */
    public List<T> list(BaseParam<T> param) {
        return list(param, null);
    }

    /**
     * 默认的列表查询
     * @param param 查询参数
     * @param consumer 对默认select的自定义改造
     * @return 列表对象
     */
    public List<T> list(BaseParam<T> param, Consumer<SelectQuery> consumer) {
        AbsSelect select = installSelect();
        if (consumer != null) {
            consumer.accept(select.getQuery());
        }
        return select.submit(repository.getEntityClazz());
    }

    /**
     * 根据ID查询唯一数据
     * @param id 主键ID
     * @return 对应的数据
     */
    public T queryById(String id) {
        return repository.queryById(id);
    }

    /**
     * 根据ID数组查询数据
     * @param ids 主键ID数组
     * @return 对应的数据数组
     */
    public List<T> queryByIds(List<String> ids) {
        return repository.queryByIds(ids);
    }

    /**
     * 新增或编辑数据
     * @param entity 提交的数据
     * @return 处理结果
     */
    public String upsert(T entity) {
        return repository.upsert(entity);
    }

    /**
     * 批量新增或编辑数据
     * @param entities 提交的数据
     * @return 处理结果
     */
    public String upsertBatch(List<T> entities) {
        return repository.upsertBatch(entities);
    }

    public int deleteByIds(String ids) {
        return repository.deleteByIds(ids);
    }

    /**
     * 根据请求数据获取 AbsSelect
     * @return AbsSelect 对象
     */
    protected AbsSelect installSelect() {
        Map<String, String> params = new HashMap<>();
        HttpServletRequest request = HttpUtil.getReq();
        if (request != null) {
            Map<String, String[]> parameterMap = request.getParameterMap();
            params = new HashMap<>();
            for (String key : parameterMap.keySet()) {
                params.put(key, String.join(",", parameterMap.get(key)));
            }
        }

        return SqluxQuery.select(repository.getEntityClazz(), params);
    }

}
