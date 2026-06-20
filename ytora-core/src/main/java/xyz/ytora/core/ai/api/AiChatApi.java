package xyz.ytora.core.ai.api;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import xyz.ytora.base.mvc.result.R;
import xyz.ytora.core.ai.logic.aiimpl.IAiLogic;
import xyz.ytora.core.ai.model.entity.AiChatDetail;
import xyz.ytora.core.ai.model.entity.AiChatRecord;
import xyz.ytora.core.ai.model.entity.AiChatSession;
import xyz.ytora.core.ai.model.param.AiChatParam;
import xyz.ytora.toolkit.text.Strs;

import java.util.List;

/**
 * AI 模块的API层
 *
 * @author 杨桐
 * @since 1.0
 */
@Tag(name = "AI")
@RestController
@RequestMapping("/ai/chat")
@RequiredArgsConstructor
public class AiChatApi {

    private final IAiLogic aiLogic;

    /**
     * 获取会话列表
     */
    @Operation(summary = "获取AI会话列表", description = "获取AI会话列表")
    @GetMapping("/sessions")
    public R<List<AiChatSession>> listSessions() {
        return R.success(aiLogic.listSessions());
    }

    /**
     * 获取指定会话信息
     */
    @Operation(summary = "获取AI会话信息", description = "获取AI会话信息")
    @GetMapping("/sessions/{sessionId}")
    public R<AiChatSession> querySession(@PathVariable String sessionId) {
        return R.success(aiLogic.querySession(sessionId));
    }

    /**
     * 获取指定会话所有对话
     */
    @Operation(summary = "获取指定会话所有对话", description = "获取指定会话所有对话")
    @GetMapping("/sessions/{sessionId}/records")
    public R<List<AiChatRecord>> listRecords(@PathVariable String sessionId) {
        return R.success(aiLogic.listRecords(sessionId));
    }

    /**
     * 获取指定会话所有事件明细
     */
    @Operation(summary = "获取指定会话所有事件明细", description = "获取指定会话所有事件明细")
    @GetMapping("/sessions/{sessionId}/details")
    public R<List<AiChatDetail>> listSessionDetails(@PathVariable String sessionId) {
        return R.success(aiLogic.listDetails(sessionId, null));
    }

    /**
     * 获取指定对话所有事件明细
     */
    @Operation(summary = "获取指定对话所有事件明细", description = "获取指定对话所有事件明细")
    @GetMapping("/sessions/{sessionId}/records/{chatId}/details")
    public R<List<AiChatDetail>> listRecordDetails(@PathVariable String sessionId, @PathVariable String chatId) {
        return R.success(aiLogic.listDetails(sessionId, chatId));
    }

    /**
     * 开启新对话
     */
    @Operation(summary = "开启新对话", description = "开启新对话")
    @PostMapping("/new")
    public R<AiChatRecord> newChat(@Valid @RequestBody AiChatParam param) {
        return R.success(aiLogic.newChat(param.getMsg(), null, param.getArgs()));
    }

    /**
     * 继续对话
     */
    @Operation(summary = "继续AI对话", description = "继续AI对话")
    @PostMapping("/continue")
    public R<AiChatRecord> continueChat(@Valid @RequestBody AiChatParam param) {
        return R.success(aiLogic.continueChat(param.getSessionId(), param.getMsg(), null, param.getArgs()));
    }

    /**
     * AI 对话。sessionId为空时开启新对话，否则继续对话。
     */
    @Operation(summary = "AI 对话", description = "AI 对话")
    @PostMapping("/chat")
    public R<AiChatRecord> chat(@Valid @RequestBody AiChatParam param) {
        AiChatRecord record;
        if (Strs.isEmpty(param.getSessionId())) {
            record = aiLogic.newChat(param.getMsg(), null, param.getArgs());
        } else {
            record = aiLogic.continueChat(param.getSessionId(), param.getMsg(), null, param.getArgs());
        }
        return R.success(record);
    }

}
