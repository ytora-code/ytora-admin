package xyz.ytora.core.monitor.online.model.data;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import xyz.ytora.base.auth.LoginUser;

/**
 * 在线用户响应数据
 *
 * @author ytora
 * @since 1.0
 */
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
@Schema(description = "在线用户响应数据")
public class OnlineUserData extends LoginUser {

    /**
     * 当前会话token
     */
    @Schema(description = "当前会话token")
    private String token;

}
