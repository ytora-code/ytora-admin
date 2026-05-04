package xyz.ytora.base.mapper;

import org.springframework.stereotype.Component;
import xyz.ytora.sqlux.orm.type.Text;

/**
 * String 与 Text 类型转换器
 *
 * @author ytora
 * @since 1.0
 */
@Component
public class StringTextConvertMapper {

    public Text map(String val) {
        if (val == null || val.isBlank()) {
            return null;
        }
        return Text.of(val);
    }

    public String map(Text val) {
        return val == null ? null : val.getValue();
    }

}
