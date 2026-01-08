package xyz.ytora.base.mvc;

import lombok.Data;
import xyz.ytora.sql4j.orm.Entity;

/**
 * EXEL
 */
@Data
public abstract class BaseExcel<T extends Entity<T>> {

    public abstract T toEntity();

}
