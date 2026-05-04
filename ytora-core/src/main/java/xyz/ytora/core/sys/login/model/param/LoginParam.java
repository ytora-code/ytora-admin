package xyz.ytora.core.sys.login.model.param;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

/**
 * created by yangtong on 2025/5/20 22:42:31
 * <br />
 * 登录请求数据
 */
@Data
public class LoginParam {

    @NotBlank
    @Schema(description = "用户名")
    private String username;

    @NotBlank
    @Schema(description = "密码")
    private String password;

    @Schema(description = "code")
    private String code;

}
