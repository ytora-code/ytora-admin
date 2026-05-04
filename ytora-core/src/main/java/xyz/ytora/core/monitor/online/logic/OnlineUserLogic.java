package xyz.ytora.core.monitor.online.logic;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import xyz.ytora.base.auth.LoginUser;
import xyz.ytora.base.cache.Caches;
import xyz.ytora.base.mvc.enums.Const;
import xyz.ytora.base.util.Pages;
import xyz.ytora.core.monitor.online.model.data.OnlineUserData;
import xyz.ytora.core.monitor.online.model.param.OnlineUserParam;
import xyz.ytora.core.monitor.sse.logic.SseLogic;
import xyz.ytora.core.monitor.sse.model.param.SseSendParam;
import xyz.ytora.sqlux.orm.Page;
import xyz.ytora.toolkit.bean.Beans;
import xyz.ytora.toolkit.text.Strs;

import java.util.Comparator;
import java.util.List;

/**
 * 在线用户业务逻辑
 *
 * @author ytora
 * @since 1.0
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class OnlineUserLogic {

    private final Caches caches;
    private final SseLogic sseLogic;

    /**
     * 分页查询在线用户
     *
     * @param param 查询参数
     * @param pageNo 页码
     * @param pageSize 页大小
     * @return 分页结果
     */
    public Page<OnlineUserData> page(OnlineUserParam param, Integer pageNo, Integer pageSize) {
        List<OnlineUserData> list = caches.getAll()
                .stream()
                .filter(key -> key.startsWith(Const.LOGIN_TOKEN_PREFIXX.value()))
                .map(this::toOnlineUserData)
                .filter(item -> matches(item, param))
                .sorted(Comparator.comparing(OnlineUserData::getLastRequestTime, Comparator.nullsLast(Comparator.reverseOrder()))
                        .thenComparing(OnlineUserData::getLoginTime, Comparator.nullsLast(Comparator.reverseOrder()))
                        .thenComparing(OnlineUserData::getUserName, Comparator.nullsLast(String::compareTo)))
                .toList();
        return Pages.toPage(pageNo, pageSize, list);
    }

    /**
     * 根据token踢出在线用户
     *
     * @param tokens token列表，英文逗号分隔
     * @return 成功踢出数量
     */
    public int kickByTokens(String tokens) {
        if (Strs.isEmpty(tokens)) {
            return 0;
        }

        int affectRows = 0;
        for (String token : tokens.split(",")) {
            try {
                String trimToken = token.trim();
                if (Strs.isEmpty(trimToken)) {
                    continue;
                }
                String cacheKey = buildTokenCacheKey(trimToken);
                if (caches.get(cacheKey) == null) {
                    continue;
                }
                LoginUser loginUser = caches.remove(cacheKey);
                if (loginUser != null) {
                    sseLogic.send(new SseSendParam("logout", loginUser.getUserName(), "你已被注销"));
                }
                affectRows++;
            } catch (Exception e) {
                log.error("踢出用户[" + token + "]失败", e);
            }

        }
        return affectRows;
    }

    private OnlineUserData toOnlineUserData(String cacheKey) {
        Object cacheValue = caches.get(cacheKey);
        OnlineUserData data = new OnlineUserData();
        data.setToken(extractToken(cacheKey));
        if (cacheValue != null) {
            Beans.copyProperties(cacheValue, data);
        }
        return data;
    }

    private boolean matches(OnlineUserData data, OnlineUserParam param) {
        if (param == null) {
            return true;
        }
        return containsIgnoreCase(data.getUserName(), param.getUserName())
                && containsIgnoreCase(data.getRealName(), param.getRealName());
    }

    private boolean containsIgnoreCase(String source, String keyword) {
        if (Strs.isEmpty(keyword)) {
            return true;
        }
        if (Strs.isEmpty(source)) {
            return false;
        }
        return source.toLowerCase().contains(keyword.toLowerCase());
    }

    private String extractToken(String cacheKey) {
        return cacheKey.substring(Const.LOGIN_TOKEN_PREFIXX.value().length());
    }

    private String buildTokenCacheKey(String token) {
        return Const.LOGIN_TOKEN_PREFIXX.value() + token;
    }

}
