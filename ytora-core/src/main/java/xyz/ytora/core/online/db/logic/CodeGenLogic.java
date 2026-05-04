package xyz.ytora.core.online.db.logic;

import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;
import xyz.ytora.base.auth.LoginUser;
import xyz.ytora.base.exception.BaseException;
import xyz.ytora.base.scope.ScopedValueContext;
import xyz.ytora.sqlux.meta.IMetaService;
import xyz.ytora.sqlux.meta.model.ColumnMeta;
import xyz.ytora.sqlux.meta.model.TableMeta;
import xyz.ytora.toolkit.text.Strs;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 * 代码生成逻辑
 *
 * @author ytora 
 * @since 1.0
 */
@Slf4j
@Service
public class CodeGenLogic {

    @Value("${ytora.version}")
    private String currentVersion;

    @Resource
    private IMetaService metaService;

    private static final List<String> baseColumns = List.of("id", "createTime", "createBy", "updateTime", "updateBy", "departCode", "remark", "version");

    public byte[] gen(@NonNull String basePath, String catalog, String schema, String tableName) {
        basePath = basePath.trim();

        try (ByteArrayOutputStream baos = new ByteArrayOutputStream();
             ZipOutputStream zipOut = new ZipOutputStream(baos)) {
            TableMeta tableMeta = metaService.getTable(tableName);
            if (tableMeta == null) {
                throw new BaseException("未找到表【" + tableName + "】");
            }
            List<ColumnMeta> columnMetas = tableMeta.getColumnMetas();
            List<String> keys = tableMeta.getPrimaryKeys();
            //过滤表的字段
            List<ColumnMeta> columns = new ArrayList<>();
            for (ColumnMeta columnMeta : columnMetas) {
                //将下划线转为小驼峰
                String columnName = Strs.toCamelCase(columnMeta.getColumnName());
                columnMeta.setColumnName(columnName);
                //判断是否主键
                columnMeta.setPrimaryKey(keys.contains(columnMeta.getColumnName()));
                //判断对应Java类型
//                columnMeta.setJavaType();
                //过滤掉七大基本字段
                if (!baseColumns.contains(columnName)) {
                    columns.add(columnMeta);
                }
            }

            LoginUser loginUser = ScopedValueContext.LOGIN_USER.get();
            String currentUser = loginUser.getRealName();

            //生成该表的模型数据
            String comment = tableMeta.getComment();
            if (Strs.isEmpty(comment)) {
                comment = tableMeta.getName();
            }
            Map<String, Object> model = new HashMap<>();
            model.put("path", basePath);
            model.put("currentUser", currentUser);
            model.put("currentVersion", currentVersion);
            model.put("table_name", tableMeta.getName());
            model.put("tableName", Strs.toCamelCase(tableMeta.getName()));
            model.put("TableName", Strs.firstUppercase(Strs.toCamelCase(tableMeta.getName())));
            model.put("TableComment", comment);
            model.put("columnMetas", columns);

            //生成模板数据
            TemplateFillLogic templateFillService = new TemplateFillLogic(model);
            // 写入模板渲染结果到 ZIP
            for (String templatePath : templateFillService.listTemplateNames()) {
                String renderedName = templateFillService.renderFileName(templatePath);
                if (renderedName.endsWith(".ftl")) {
                    renderedName = renderedName.substring(0, renderedName.length() - 4);
                }
                renderedName = mapOutputPath(renderedName);
                String renderedContent = templateFillService.renderContent(templatePath);

                zipOut.putNextEntry(new ZipEntry(renderedName));
                zipOut.write(renderedContent.getBytes(StandardCharsets.UTF_8));
                zipOut.closeEntry();
            }

            zipOut.finish();
            return baos.toByteArray();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private String mapOutputPath(String renderedName) {
        String normalizedName = renderedName.replace("\\", "/");
        return "code/" + normalizedName;
    }

}
