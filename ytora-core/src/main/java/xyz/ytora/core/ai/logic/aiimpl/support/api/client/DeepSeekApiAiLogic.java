package xyz.ytora.core.ai.logic.aiimpl.support.api.client;

import jakarta.annotation.Resource;
import org.springframework.stereotype.Component;
import xyz.ytora.core.ai.logic.aiimpl.support.api.AiApiAdapter;
import xyz.ytora.core.ai.logic.aiimpl.support.api.OpenAiCompatibleAiApiAdapter;
import xyz.ytora.core.ai.logic.aiimpl.support.api.model.ApiChatRequest;
import xyz.ytora.core.ai.logic.aiimpl.support.api.model.ApiChatResponse;
import xyz.ytora.core.ai.model.enums.AiType;

/**
 * DeepSeek API厂商适配。
 *
 * @author ytora
 * @since 1.0
 */
@Component
public class DeepSeekApiAiLogic implements AiApiAdapter {

    @Resource
    private OpenAiCompatibleAiApiAdapter openAiCompatibleAiApiAdapter;

    @Override
    public boolean supports(String provider) {
        return "deepseek".equalsIgnoreCase(provider);
    }

    @Override
    public AiType getTool() {
        return AiType.DEEP_SEEK_API;
    }

    @Override
    public ApiChatResponse chat(ApiChatRequest request) {
        return openAiCompatibleAiApiAdapter.chat(request);
    }

}
