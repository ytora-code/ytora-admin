package xyz.ytora.core.monitor.online.model.param;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 在线用户查询参数
 *
 * @author ytora 
 * @since 1.0
 */
@Data
@Accessors(chain=true)
public class OnlineUserParam {

    /**
     * 用户名
     */
    @Schema(description = "用户名")
    private String userName;

    /**
     * 真实姓名
     */
    @Schema(description = "真实姓名")
    private String realName;
}
