package xyz.ytora.core.sys.login.model.req;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

/**
 * created by yangtong on 2025/5/20 22:42:31
 * <br />
 * 密码校验
 */
@Data
public class CheckPasswordReq {

    @NotBlank
    @Schema(description = "用户id")
    private String userId;

    @NotBlank
    @Schema(description = "密码")
    private String password;
}
