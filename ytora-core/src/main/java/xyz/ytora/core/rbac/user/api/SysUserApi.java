package xyz.ytora.core.rbac.user.api;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import xyz.ytora.core.rbac.user.logic.SysUserLogic;

/**
 * created by YT on 2025/12/8 22:25:56
 * <pre />
 * 用户 控制器
 */
@Tag(name = "用户")
@RestController
@RequestMapping("/sys/fileTest")
@RequiredArgsConstructor
public class SysUserApi {
    private final SysUserLogic sysUserLogic;
}
