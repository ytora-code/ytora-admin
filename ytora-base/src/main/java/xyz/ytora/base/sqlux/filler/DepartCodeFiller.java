package xyz.ytora.base.sqlux.filler;

import xyz.ytora.base.auth.LoginUser;
import xyz.ytora.base.scope.ScopedValueContext;
import xyz.ytora.sqlux.orm.filler.FillerAdapter;

import java.util.NoSuchElementException;

/**
 * 新增数据时的自动填充数据创建人
 *
 * @author ytora 
 * @since 1.0
 */
public class DepartCodeFiller extends FillerAdapter {

    @Override
    public Object onInsert() {
        try {
            LoginUser loginUser = ScopedValueContext.LOGIN_USER.get();
            return loginUser.getDepartCode();
        } catch (NoSuchElementException e) {
            return null;
        }
    }
}
