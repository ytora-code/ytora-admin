package xyz.ytora.core.ai.logic.aiimpl.support.api.model;

import lombok.Data;

/**
 * 统一AI API消息
 *
 * @author ytora
 * @since 1.0
 */
@Data
public class ApiChatMessage {

    private String role;

    private String content;

    public static ApiChatMessage user(String content) {
        return of("user", content);
    }

    public static ApiChatMessage assistant(String content) {
        return of("assistant", content);
    }

    public static ApiChatMessage of(String role, String content) {
        ApiChatMessage message = new ApiChatMessage();
        message.setRole(role);
        message.setContent(content);
        return message;
    }

}
