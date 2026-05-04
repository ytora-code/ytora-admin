package xyz.ytora.core.monitor.jvm.api;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import xyz.ytora.core.monitor.jvm.logic.JvmLogic;
import xyz.ytora.core.monitor.jvm.model.data.*;

import java.util.List;

/**
 * created by YT on 2026/4/24
 * <br/>
 * JVM 监控接口。
 */
@Slf4j
@Tag(name = "JVM")
@RestController
@RequestMapping("/monitor/jvm")
@RequiredArgsConstructor
public class JvmApi {

    private final JvmLogic jvmLogic;

    // ======================== 静态数据 ========================>

    /**
     * JVM 基础信息。
     */
    @GetMapping("/basicInfo")
    @Operation(summary = "JVM基础信息", description = "JVM基础信息")
    public JvmBasicInfoData getBasicInfo() {
        return jvmLogic.getBasicInfo();
    }

    // ======================== 动态数据 ========================>

    /**
     * JVM 运行时信息。
     */
    @GetMapping("/runtime")
    @Operation(summary = "JVM运行时信息", description = "JVM运行时信息")
    public JvmRuntimeData getRuntime() {
        return jvmLogic.getRuntime();
    }

    /**
     * JVM 内存信息。
     */
    @GetMapping("/memory")
    @Operation(summary = "JVM内存信息", description = "JVM内存信息")
    public JvmMemoryData getMemory() {
        return jvmLogic.getMemory();
    }

    /**
     * JVM 线程信息。
     */
    @GetMapping("/thread")
    @Operation(summary = "JVM线程信息", description = "JVM线程信息")
    public JvmThreadData getThread() {
        return jvmLogic.getThread();
    }

    /**
     * JVM GC 信息。
     */
    @GetMapping("/gc")
    @Operation(summary = "JVM垃圾回收信息", description = "JVM垃圾回收信息")
    public List<JvmGarbageCollectorData> getGarbageCollectors() {
        return jvmLogic.getGarbageCollectors();
    }

    /**
     * JVM 类加载信息。
     */
    @GetMapping("/classLoading")
    @Operation(summary = "JVM类加载信息", description = "JVM类加载信息")
    public JvmClassLoadingData getClassLoading() {
        return jvmLogic.getClassLoading();
    }

    /**
     * JVM 即时编译信息。
     */
    @GetMapping("/compilation")
    @Operation(summary = "JVM即时编译信息", description = "JVM即时编译信息")
    public JvmCompilationData getCompilation() {
        return jvmLogic.getCompilation();
    }
}
