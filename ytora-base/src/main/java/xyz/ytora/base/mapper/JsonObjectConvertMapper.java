package xyz.ytora.base.mapper;

import org.springframework.stereotype.Component;
import xyz.ytora.base.util.json.Jsons;
import xyz.ytora.sqlux.orm.type.Json;
import xyz.ytora.sqlux.orm.type.Text;

/**
 * String 与 Text 类型转换器
 *
 * @author ytora
 * @since 1.0
 */
@Component
public class JsonObjectConvertMapper {

    public Json map(Object val) {
        if (val == null) {
            return null;
        }
        return Json.of(val);
    }

    public Object map(Json val) {
        return val == null ? null : val.unwrap();
    }

}
