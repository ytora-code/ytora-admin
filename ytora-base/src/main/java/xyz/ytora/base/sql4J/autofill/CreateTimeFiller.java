package xyz.ytora.base.sql4J.autofill;

import xyz.ytora.sql4j.orm.autofill.ColumnFillerAdapter;

import java.time.LocalDateTime;

/**
 * created by YT on 2025/12/29 18:56:48
 * <br/>
 */
public class CreateTimeFiller extends ColumnFillerAdapter {

    @Override
    public Object fillOnInsert() {
        return LocalDateTime.now();
    }

    @Override
    public Object fillOnUpdate() {
        return null;
    }
}
