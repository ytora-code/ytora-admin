package xyz.ytora.base.mvc.basemodel;

import org.springframework.beans.factory.annotation.Autowired;

/**
 * 通用的API层
 *
 * @author ytora
 * @since 1.0
 */
public abstract class BaseApi<L extends BaseLogic<?, ?>> {

    protected L logic;

    @Autowired
    public void setRepository(L logic) {
        this.logic = logic;
    }

}
