package xyz.ytora.core.sys.login.model.req;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

/**
 * created by yangtong on 2025/5/20 22:42:31
 * <br />
 * 密码更新
 */
@Data
public class RefreshPasswordReq {

    @NotBlank
    @Schema(description = "用户id")
    private String userId;

    @NotBlank
    @Schema(description = "旧密码")
    private String oldPassword;

    @NotBlank
    @Schema(description = "新密码")
    private String newPassword;
}
