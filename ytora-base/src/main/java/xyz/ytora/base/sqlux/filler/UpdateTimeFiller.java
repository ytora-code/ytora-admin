package xyz.ytora.base.sqlux.filler;

import xyz.ytora.sqlux.orm.filler.FillerAdapter;

import java.time.LocalDateTime;

/**
 * 新增数据时的自动填充数据创建人
 *
 * @author ytora 
 * @since 1.0
 */
public class UpdateTimeFiller extends FillerAdapter {

    @Override
    public Object onInsert() {
        return LocalDateTime.now();
    }

    @Override
    public Object onUpdate() {
        return LocalDateTime.now();
    }

}
