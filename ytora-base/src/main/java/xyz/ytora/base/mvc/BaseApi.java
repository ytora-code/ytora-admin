package xyz.ytora.base.mvc;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import xyz.ytora.base.scope.ScopedValueItem;
import xyz.ytora.sql4j.func.support.Raw;
import xyz.ytora.sql4j.orm.IRepo;
import xyz.ytora.sql4j.orm.Page;
import xyz.ytora.sql4j.orm.Pages;
import xyz.ytora.sql4j.orm.querygen.QueryGenerator;
import xyz.ytora.sql4j.sql.select.SelectBuilder;

/**
 * created by YT on 2025/12/23 22:01:30
 * <br/>
 */
public class BaseApi<T extends BaseEntity<T>, L extends BaseLogic<T, D>, D extends IRepo<T>> {
    protected L logic;

    protected D repository;

    @Autowired
    public void setLogic(L logic) {
        this.logic = logic;
    }

    @Autowired
    public void setRepository(D repository) {
        this.repository = repository;
    }

    /**
     * 分页查询
     */
    protected Page<BaseResp<T>> page(BaseReq<T> param, Integer pageNo, Integer pageSize) {
        SelectBuilder selectBuilder = query();
        Page<T> page = repository.page(pageNo, pageSize, selectBuilder);
        return Pages.transPage(page, BaseEntity::toResp);
    }

    /**
     * 根据id查询
     */
    protected BaseResp<T> queryById(String id) {
        T entity = repository.one(w -> w.eq(Raw.of("id"), id));
        if (entity == null) {
            return null;
        }
        return entity.toResp();
    }

    /**
     * 新增或编辑
     */
    protected String insertOrUpdate(BaseReq<T> data) {
        if (data.getId() == null) {
            repository.insert(data.toEntity());
            return "新增成功";
        } else {
            repository.update(data.toEntity(), w -> w.eq(Raw.of("id"), data.getId()));
            return "编辑成功";
        }
    }

    /**
     * 根据id删除
     */
    protected R<String> delete() {
        SelectBuilder selectBuilder = query();
        repository.delete(selectBuilder.getWhereStage().getWhere());
        return R.success("删除成功");
    }

    public static SelectBuilder query() {
        HttpServletRequest request = ScopedValueItem.REQUEST.get();
        String queryString = request.getQueryString();
        return QueryGenerator.where(queryString);
    }
}
