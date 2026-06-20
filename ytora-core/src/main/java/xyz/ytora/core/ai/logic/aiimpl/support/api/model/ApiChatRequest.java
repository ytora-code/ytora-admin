package xyz.ytora.core.ai.logic.aiimpl.support.api.model;

import lombok.Data;

import java.util.List;

/**
 * 统一AI API对话请求
 *
 * @author ytora
 * @since 1.0
 */
@Data
public class ApiChatRequest {

    private String provider;

    private String model;

    private String sessionId;

    private List<ApiChatMessage> messages;

    private Boolean stream;

    private Double temperature;

    private Integer maxTokens;

}
