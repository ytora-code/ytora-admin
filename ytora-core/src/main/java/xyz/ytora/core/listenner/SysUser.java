package xyz.ytora.core.listenner;

import lombok.Data;
import xyz.ytora.sql4j.anno.Table;

/**
 * created by YT on 2025/12/6 01:11:55
 * <br/>
 */
@Table("sys_user")
@Data
public class SysUser {
    private String id;
}
