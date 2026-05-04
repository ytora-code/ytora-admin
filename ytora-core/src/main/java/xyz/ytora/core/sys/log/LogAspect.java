package xyz.ytora.core.sys.log;

import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import xyz.ytora.base.exception.BaseException;
import xyz.ytora.base.scope.ScopedValueContext;
import xyz.ytora.base.scope.Scopes;
import xyz.ytora.base.util.HttpUtil;
import xyz.ytora.base.util.json.Jsons;
import xyz.ytora.core.sys.log.logic.SysLogLogic;
import xyz.ytora.core.sys.log.model.entity.SysLog;
import xyz.ytora.sqlux.orm.type.Text;
import xyz.ytora.toolkit.id.Ids;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 日志记录器
 *
 * <p>用来自动记录控制器方法和普通方法调用情况的日志</p>
 *
 * @author ytora 
 * @since 1.0
 */
@Slf4j
@Aspect
@Component
public class LogAspect {

    @Resource
    private SysLogLogic logLogic;

    @Around("@annotation(log)")
    public Object handleLogger(ProceedingJoinPoint jp, AutoLog log) {
        long startTime = System.currentTimeMillis();
        String traceId = Ids.nextUuid();
        SysLog logInfo = new SysLog();
        try {
            Signature signature = jp.getSignature();
            String happenPlace = null;
            Map<String, Object> methodParameter = new HashMap<>();
            if (signature instanceof MethodSignature ms) {
                Method method = ms.getMethod();
                // 类全路径
                String className = jp.getTarget().getClass().getName();
                // 方法名
                String methodName = method.getName();
                // 参数类型
                Class<?>[] types = method.getParameterTypes();
                String params = Arrays.stream(types)
                        .map(Class::getName)
                        .collect(Collectors.joining(","));
                happenPlace = className + "#" + methodName + "(" + params + ")";

                // 方法参数信息
                String[] names = ms.getParameterNames();
                Object[] values = jp.getArgs();
                for (int i = 0; i < names.length; i++) {
                    String name = names[i];
                    Object value = values[i];
                    methodParameter.put(name, value);
                }
            }

            // 记录当前上下文信息，用于写入日志
            logInfo.setTraceId(traceId);
            logInfo.setThread(Thread.currentThread().getName());
            logInfo.setHappenPlace(happenPlace);
            logInfo.setContent(Text.of(log.value()));

            String paramStr = Jsons.toJsonStr(methodParameter);
            logInfo.setParamLength(paramStr.length());
            logInfo.setParams(Text.of(paramStr.length() < 10240 ? paramStr : paramStr.substring(0, 10240)));

            HttpServletRequest request = HttpUtil.getReq();

            // 非web上下文
            if (request == null) {
                logInfo.setType(LogType.REQUEST_LOG);
            }
            // web上下文
            else {
                logInfo.setType(LogType.NORMAL_LOG);
                logInfo.setIp(HttpUtil.getIp());
                logInfo.setRequestUrl(request.getRequestURL().toString());
            }

            Object result = Scopes.start()
                    .where(ScopedValueContext.TRACE_ID, traceId)
                    .run(() -> {
                        // 先执行业务方法，拿到真实返回值
                        try {
                            return jp.proceed();
                        } catch (Throwable e) {
                            throw new BaseException(e);
                        }
                    });

            String resultStr = Jsons.toJsonStr(result);
            logInfo.setResultLength(resultStr.length());
            logInfo.setResult(Text.of(resultStr.length() < 10240 ? resultStr : resultStr.substring(0, 10240)));

            return result;
        } catch (Throwable e) {
            logInfo.setErrorStack(e);
            throw new RuntimeException(e);
        } finally {
            logInfo.setCost(System.currentTimeMillis() - startTime);
            logLogic.doLog(logInfo);
        }
    }
}
