<#assign packageBase = path>
<#if module?? && module?has_content>
    <#assign packageBase = packageBase + "." + module>
</#if>
<#assign businessName = TableComment!"业务数据">
<#if businessName?ends_with("表")>
    <#assign businessName = businessName?substring(0, businessName?length - 1)>
</#if>
package ${packageBase}.logic;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import xyz.ytora.base.mvc.basemodel.BaseLogic;
import ${packageBase}.model.entity.${TableName};
import ${packageBase}.repo.${TableName}Repo;

import static xyz.ytora.sqlux.core.SQL.*;

/**
 * ${businessName}模块的业务逻辑层
 *
 * @author ${currentUser!"ytora"}
 * @since ${currentVersion!"1.0"}
 */
@Service
@AllArgsConstructor
public class ${TableName}Logic extends BaseLogic<${TableName}, ${TableName}Repo> {

}
