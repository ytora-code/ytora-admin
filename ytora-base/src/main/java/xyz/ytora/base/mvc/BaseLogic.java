package xyz.ytora.base.mvc;

import org.springframework.beans.factory.annotation.Autowired;
import xyz.ytora.sql4j.orm.IRepo;

/**
 * created by YT on 2025/12/21 16:29:49
 * <br/>
 * Service层基础操作
 */
public abstract class BaseLogic<T extends BaseEntity<T>, R extends IRepo<T>> {

    protected R repository;

    @Autowired
    public void setRepository(R repository) {
        this.repository = repository;
    }
}
