package xyz.ytora.core.sqlux.backup;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 删除时备份数据
 *
 * @author ytora
 * @since 1.0
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface BackupOnDelete {

    /**
     * 如果即将删除的数据量超过指定阈值，就不进行备份
     */
    int value() default 10;

}
