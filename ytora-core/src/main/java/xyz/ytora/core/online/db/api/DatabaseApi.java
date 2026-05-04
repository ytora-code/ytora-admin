package xyz.ytora.core.online.db.api;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import xyz.ytora.base.mvc.enums.Mimes;
import xyz.ytora.base.mvc.result.anno.DownloadMapper;
import xyz.ytora.core.online.db.logic.CodeGenLogic;

/**
 * 数据库相关API接口
 *
 * @author ytora
 * @since 1.0
 */
@Tag(name = "数据库")
@RestController
@RequestMapping("/sys/database")
@RequiredArgsConstructor
public class DatabaseApi {

    @Autowired
    private CodeGenLogic codeGenService;

    @Operation(summary = "根据表名称生成代码", description = "根据表名称生成代码")
    @DownloadMapper(value = "genByTable", mime = Mimes.APPLICATION_ZIP, filename = "#tableName + '.zip'")
    public byte[] genByTable(@RequestParam String path,
                             @RequestParam String catalog,
                             @RequestParam String schema,
                             @RequestParam String tableName) {
        return codeGenService.gen(path, catalog, schema, tableName);
    }

}
