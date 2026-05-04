<#assign packageBase = path>
<#if module?? && module?has_content>
    <#assign packageBase = packageBase + "." + module>
</#if>
<#assign businessName = TableComment!"业务数据">
<#if businessName?ends_with("表")>
    <#assign businessName = businessName?substring(0, businessName?length - 1)>
</#if>
package ${packageBase}.repo;

import org.springframework.stereotype.Repository;
import xyz.ytora.base.mvc.basemodel.BaseRepo;
import ${packageBase}.model.entity.${TableName};

import static xyz.ytora.sqlux.core.SQL.*;

/**
 * ${businessName}模块的持久层
 *
 * @author ${currentUser!"ytora"}
 * @since ${currentVersion!"1.0"}
 */
@Repository
public class ${TableName}Repo extends BaseRepo<${TableName}> {
}
