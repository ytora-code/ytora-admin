package xyz.ytora.base.mvc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestParam;
import xyz.ytora.base.querygen.WhereGenerator;
import xyz.ytora.sql4j.func.support.Raw;
import xyz.ytora.sql4j.orm.IRepo;
import xyz.ytora.sql4j.orm.Page;
import xyz.ytora.sql4j.orm.Pages;
import xyz.ytora.sql4j.sql.ConditionExpressionBuilder;

import java.util.List;

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
    protected Page<BaseResp<T>> page(BaseReq<T> baseReq, Integer pageNo, Integer pageSize) {
        ConditionExpressionBuilder where = WhereGenerator.where();
        Page<T> page = repository.page(pageNo, pageSize, where);
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
    protected String insertOrUpdate(BaseReq<T> baseReq) {
        if (baseReq.getId() == null) {
            repository.insert(baseReq.toEntity());
            return "新增成功";
        } else {
            repository.update(baseReq.toEntity(), w -> w.eq(Raw.of("id"), baseReq.getId()));
            return "编辑成功";
        }
    }

    /**
     * 根据id删除
     */
    protected R<String> delete(List<String> ids) {
        repository.delete(w -> w.in(Raw.of("id"), ids));
        return R.success("删除成功");
    }
}
