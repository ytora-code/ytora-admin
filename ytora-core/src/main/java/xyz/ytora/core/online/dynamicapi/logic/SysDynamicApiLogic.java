package xyz.ytora.core.online.dynamicapi.logic;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import xyz.ytora.base.exception.BaseException;
import xyz.ytora.base.mvc.basemodel.BaseLogic;
import xyz.ytora.base.mvc.result.ResultCode;
import xyz.ytora.core.online.dynamicapi.model.data.SysDynamicApiTestExecParam;
import xyz.ytora.core.online.dynamicapi.model.entity.SysDynamicApi;
import xyz.ytora.core.online.dynamicapi.repo.SysDynamicApiRepo;
import xyz.ytora.toolkit.text.Strs;
import xyz.ytora.toolkit.text.dsl.YtoraDslEngine;

import java.util.List;
import java.util.Map;

import static xyz.ytora.sqlux.core.SQL.rawQuery;
import static xyz.ytora.sqlux.core.SQL.update;

/**
 * 动态API接口模块的业务逻辑层
 *
 * @author 杨桐
 * @since 1.0
 */
@Service
@AllArgsConstructor
public class SysDynamicApiLogic extends BaseLogic<SysDynamicApi, SysDynamicApiRepo> {

    private final YtoraDslEngine dslEngine;

    /**
     * 调用动态接口
     */
    public List<Map<String, Object>> api(String uri, Map<String, Object> param) {
        SysDynamicApi dynamicApi = repository.one(w -> w.eq(SysDynamicApi::getUri, uri));
        if (dynamicApi == null) {
            throw new BaseException(ResultCode.API_NOT_FOUND);
        }
        if (dynamicApi.getStatus() == 1) {
            throw new BaseException(ResultCode.API_NOT_PUBLISH);
        }
        String content = dynamicApi.getContent();
        if (Strs.isEmpty(content)) {
            return null;
        }
        if (dynamicApi.getType() == 1) {
            // 根据模板字符串 + 参数，得到完整SQL
            String sql = dslEngine.render(content, param);
            return rawQuery(sql).submit();
        }
        throw new BaseException("暂不支持的动态接口类型：" + dynamicApi.getType());
    }

    /**
     * 测试API接口
     */
    public List<Map<String, Object>> test(SysDynamicApiTestExecParam param) {
        // 根据模板字符串 + 参数，得到完整SQL
        String sql = dslEngine.render(param.getContent(), param.getParam());
        return rawQuery(sql).submit();
    }

    /**
     * 接口上线
     * @param id 接口ID
     */
    public void publish(String id) {
        update(SysDynamicApi.class)
                .set(SysDynamicApi::getStatus, 2)
                .where(w ->w.eq(SysDynamicApi::getId, id))
                .submit();
    }

    /**
     * 接口下线
     * @param id 接口ID
     */
    public void offline(String id) {
        update(SysDynamicApi.class)
                .set(SysDynamicApi::getStatus, 1)
                .where(w ->w.eq(SysDynamicApi::getId, id))
                .submit();
    }
}
