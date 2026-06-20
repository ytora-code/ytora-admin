package xyz.ytora.core.ai.logic.aiimpl.support.api;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import xyz.ytora.base.exception.BaseException;
import xyz.ytora.core.ai.logic.aiimpl.support.api.model.ApiChatMessage;
import xyz.ytora.core.ai.logic.aiimpl.support.api.model.ApiChatRequest;
import xyz.ytora.core.ai.logic.aiimpl.support.api.model.ApiChatResponse;
import xyz.ytora.core.ai.logic.aiimpl.support.api.model.ApiUsage;
import xyz.ytora.toolkit.text.Strs;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * OpenAI兼容API适配器，DeepSeek/Qwen兼容模式等可复用。
 *
 * @author ytora
 * @since 1.0
 */
@Slf4j
@Component
public class OpenAiCompatibleAiApiAdapter {

    @Resource
    private ObjectMapper objectMapper;

    @Resource
    private AiApiProperty property;

    public ApiChatResponse chat(ApiChatRequest request) {
        try {
            String requestBody = buildRequestBody(request);
            HttpRequest httpRequest = HttpRequest.newBuilder()
                    .uri(URI.create(buildChatUri()))
                    .timeout(Duration.ofSeconds(property.getTimeoutSeconds()))
                    .header("Authorization", "Bearer " + property.getApiKey())
                    .header("Content-Type", "application/json")
                    .POST(HttpRequest.BodyPublishers.ofString(requestBody))
                    .build();

            HttpResponse<String> response = HttpClient.newHttpClient()
                    .send(httpRequest, HttpResponse.BodyHandlers.ofString());
            if (response.statusCode() < 200 || response.statusCode() >= 300) {
                throw new BaseException("AI API调用失败，status:{}, body:{}", response.statusCode(), response.body());
            }
            return parseResponse(request, requestBody, response.body());
        } catch (IOException e) {
            log.error("AI API调用出错", e);
            throw new BaseException("AI API调用出错", e);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            log.error("AI API调用被中断", e);
            throw new BaseException("AI API调用被中断", e);
        }
    }

    private String buildRequestBody(ApiChatRequest request) throws IOException {
        Map<String, Object> body = new HashMap<>();
        body.put("model", request.getModel());
        body.put("stream", false);
        body.put("messages", request.getMessages().stream()
                .map(this::toMessageBody)
                .toList());
        if (request.getTemperature() != null) {
            body.put("temperature", request.getTemperature());
        }
        if (request.getMaxTokens() != null) {
            body.put("max_tokens", request.getMaxTokens());
        }
        return objectMapper.writeValueAsString(body);
    }

    private Map<String, String> toMessageBody(ApiChatMessage message) {
        Map<String, String> body = new HashMap<>();
        body.put("role", message.getRole());
        body.put("content", message.getContent());
        return body;
    }

    private String buildChatUri() {
        String baseUri = property.getBaseUri();
        if (Strs.isEmpty(baseUri)) {
            throw new BaseException("ytora.ai.api.base-uri不能为空");
        }
        return baseUri.endsWith("/") ? baseUri + "chat/completions" : baseUri + "/chat/completions";
    }

    private ApiChatResponse parseResponse(ApiChatRequest request, String requestBody, String responseBody)
            throws IOException {
        JsonNode root = objectMapper.readTree(responseBody);
        JsonNode choice = root.path("choices").isArray() && !root.path("choices").isEmpty()
                ? root.path("choices").get(0)
                : objectMapper.createObjectNode();
        JsonNode usage = root.path("usage");

        ApiUsage apiUsage = new ApiUsage();
        apiUsage.setInputTokens(usage.path("prompt_tokens").asInt());
        apiUsage.setOutputTokens(usage.path("completion_tokens").asInt());
        apiUsage.setTotalTokens(usage.path("total_tokens").asInt());
        apiUsage.setCacheHitTokens(usage.path("prompt_cache_hit_tokens").asInt());

        ApiChatResponse response = new ApiChatResponse();
        response.setProvider(request.getProvider());
        response.setModel(root.path("model").asText(request.getModel()));
        response.setSessionId(request.getSessionId());
        response.setChatId(root.path("id").asText(null));
        response.setContent(choice.path("message").path("content").asText(null));
        response.setFinishReason(choice.path("finish_reason").asText(null));
        response.setUsage(apiUsage);
        response.setRequestJson(requestBody);
        response.setRawJson(responseBody);
        return response;
    }

}
