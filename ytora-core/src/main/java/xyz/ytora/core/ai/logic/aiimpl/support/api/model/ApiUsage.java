package xyz.ytora.core.ai.logic.aiimpl.support.api.model;

import lombok.Data;

/**
 * 统一AI API用量
 *
 * @author ytora
 * @since 1.0
 */
@Data
public class ApiUsage {

    private Integer inputTokens;

    private Integer outputTokens;

    private Integer totalTokens;

    private Integer cacheHitTokens;

}
