package xyz.ytora.core.rbac.datascope;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;
import xyz.ytora.base.exception.BaseException;
import xyz.ytora.base.mvc.basemodel.BaseData;
import xyz.ytora.base.scope.ScopedValueContext;
import xyz.ytora.base.scope.Scopes;
import xyz.ytora.core.rbac.datascope.logic.SysDataScopeGroupLogic;
import xyz.ytora.core.rbac.datascope.logic.SysDataScopeLogic;
import xyz.ytora.core.rbac.datascope.model.entity.SysDataScope;
import xyz.ytora.core.rbac.datascope.model.entity.SysDataScopeGroup;
import xyz.ytora.core.rbac.role.logic.SysRoleLogic;
import xyz.ytora.core.rbac.role.model.data.SysRoleData;

import java.util.List;

/**
 * 对 DataScope 进行增强
 */
@Slf4j
@Aspect
@Component
@RequiredArgsConstructor
public class DataScopeAspect {

    private final SysDataScopeGroupLogic dataScopeGroupLogic;
    private final SysDataScopeLogic dataScopeLogic;
    private final SysRoleLogic roleLogic;

    @Around("@annotation(dataScope)")
    public Object handleDownload(ProceedingJoinPoint joinPoint, DataScope dataScope) throws Throwable {
        SysDataScopeGroup group = null;
        try {
            group = dataScopeGroupLogic.queryGroupByCode(dataScope.value());
        } catch (Exception e) {
            log.error("查询数据范围分组时出现异常", e);
        }

        if (group == null) {
            return joinPoint.proceed();
        }

        // 查询当前决定的数据规则
        List<SysRoleData> roles = roleLogic.listCurrentRole();
        List<String> roleIds = roles.stream().map(BaseData::getId).toList();
        List<String> dataScopeIds = dataScopeLogic.listDataScopeByGroupId(roleIds, group.getId());
        List<SysDataScope> dataScopes = dataScopeLogic.queryByIds(dataScopeIds);


        return Scopes.start()
                .where(ScopedValueContext.DATA_SCOPE, dataScopes)
                .run(() -> {
                    try {
                        return joinPoint.proceed();
                    } catch (Throwable e) {
                        throw new BaseException(e);
                    }
                });
    }

}
