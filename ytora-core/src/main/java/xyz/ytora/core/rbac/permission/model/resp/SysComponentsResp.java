package xyz.ytora.core.rbac.permission.model.resp;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import xyz.ytora.sql4j.caster.SQLReader;
import xyz.ytora.sql4j.caster.SQLWriter;
import xyz.ytora.ytool.json.JSON;
import xyz.ytora.ytool.json.Jsons;
import xyz.ytora.ytool.str.Strs;

import java.math.BigDecimal;

/**
 * created by YT on 2026/1/1 23:46:24
 * <br/>
 */
@Data
@Schema(description = "组件")
public class SysComponentsResp implements SQLReader, SQLWriter {

    /**
     * 组件类型
     * 1.table 2.table-col::xxx 3.form 4.form-item:xxx
     */
    @Schema(description = "组件类型")
    private String type;

    /**
     * 组件key
     */
    @Schema(description = "组件key")
    private String key;

    /**
     * 组件宽度
     */
    @Schema(description = "组件宽度")
    private Integer width;

    /**
     * 组件高度
     */
    @Schema(description = "组件高度")
    private Integer height;

    /**
     * 组件attr属性
     */
    @Schema(description = "组件attr属性")
    private JSON attr;

    @Override
    public SysComponentsResp read(Object o) {
        if (o == null) {
            return null;
        }
        String string = o.toString();
        return Jsons.fromJsonStr(string, SysComponentsResp.class);
    }


    @Override
    public Object write() {
        // 在可以转换的情况下，转换attr的value为bool或者数组
        for (String key : attr.keySet()) {
            try {
                Object value = attr.get(key);
                if (value instanceof String strVal) {
                    // 如果value是"null"字符串，直接转换为null
                    if (strVal.equalsIgnoreCase("null")) {
                        attr.put(key, null);
                    }
                    // 如果value是true或false，直接转换
                    else if (strVal.equalsIgnoreCase("true")) {
                        attr.put(key, true);
                    } else if (strVal.equalsIgnoreCase("false")) {
                        attr.put(key, false);
                    } else {
                        // 如果是数字，直接转换。转换失败会返回NULL
                        BigDecimal decimalVal = Strs.tryToBigDecimal(strVal);
                        if (decimalVal != null) {
                            attr.put(key, decimalVal);
                        }
                    }
                }
            } catch (Exception e) {
                // 转换失败，跳过
            }
        }
        return Jsons.toJsonStr(this);
    }
}
