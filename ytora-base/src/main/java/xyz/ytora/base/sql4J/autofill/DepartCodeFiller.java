package xyz.ytora.base.sql4J.autofill;

import xyz.ytora.base.auth.LoginUser;
import xyz.ytora.base.scope.ScopedValueItem;
import xyz.ytora.sql4j.orm.autofill.ColumnFillerAdapter;

/**
 * created by YT on 2025/12/29 18:56:48
 * <br/>
 */
public class DepartCodeFiller extends ColumnFillerAdapter {

    @Override
    public Object fillOnInsert() {
        ScopedValue<LoginUser> loginUserScope = ScopedValueItem.LOGIN_USER;
        try {
            LoginUser loginUser = loginUserScope.get();
            return loginUser.getDepartCode();
        } catch (Exception e) {
            return "-";
        }
    }
}
