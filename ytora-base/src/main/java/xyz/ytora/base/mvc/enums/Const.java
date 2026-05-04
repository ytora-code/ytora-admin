package xyz.ytora.base.mvc.enums;

/**
 * 一些常量
 *
 * @author ytora
 * @since 1.0
 */
public enum Const {

    /**
     * 登录TOKEN的前缀
     */
    LOGIN_TOKEN_PREFIXX("TKN-");

    private String value;

    Const(String value) {
        this.value = value;
    }

    public String value() {
        return value;
    }
}
