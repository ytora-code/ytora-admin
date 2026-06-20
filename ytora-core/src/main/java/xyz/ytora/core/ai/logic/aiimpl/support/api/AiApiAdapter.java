package xyz.ytora.core.ai.logic.aiimpl.support.api;

import xyz.ytora.core.ai.logic.aiimpl.support.api.model.ApiChatRequest;
import xyz.ytora.core.ai.logic.aiimpl.support.api.model.ApiChatResponse;
import xyz.ytora.core.ai.model.enums.AiType;

/**
 * AI API厂商适配器
 *
 * @author ytora
 * @since 1.0
 */
public interface AiApiAdapter {

    /**
     * 是否支持当前供应商。
     */
    boolean supports(String provider);

    /**
     * 当前供应商对应的工具类型。
     */
    AiType getTool();

    /**
     * 调用AI对话接口。
     */
    ApiChatResponse chat(ApiChatRequest request);

}
