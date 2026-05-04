package xyz.ytora.core.online.db.logic;

import freemarker.cache.ClassTemplateLoader;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import lombok.Getter;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import xyz.ytora.base.exception.BaseException;

import java.io.File;
import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static freemarker.template.Configuration.VERSION_2_3_31;

/**
 * 填充代码模板的逻辑
 *
 * @author ytora
 * @since 1.0
 */
@Getter
public class TemplateFillLogic {

    private static final String TEMPLATE_BASE_PATH = "/template/codegen";
    private static final String TEMPLATE_SCAN_PATTERN = "classpath*:" + TEMPLATE_BASE_PATH + "/**/*.ftl";
    private final Configuration cfg;
    private final Map<String, Object> model;
    private final List<String> templateNames;

    public TemplateFillLogic(Map<String, Object> model) {
        this.model = model;
        try {
            //1.创建Freemarker配置类
            Configuration cfg = new Configuration(VERSION_2_3_31);
            cfg.setDefaultEncoding("UTF-8");
            //2.加载模板（适配 JAR 包）
            ClassTemplateLoader loader = new ClassTemplateLoader(this.getClass(), TEMPLATE_BASE_PATH);
            cfg.setTemplateLoader(loader);
            this.cfg = cfg;

            //获取类路径下的模板文件
            this.templateNames = scanTemplateNames();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public List<String> listTemplateNames() {
        return this.templateNames;
    }

    public List<File> listFiles() {
        return null;
    }

    public String renderFileName(String templateStr) {
        try (StringWriter sw = new StringWriter()) {
            Template template = new Template("st", new StringReader(templateStr), cfg);
            template.process(model, sw);
            //返回生成的目标文件的路径
            return sw.toString();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public String renderContent(String fileName) {
        //渲染文件内容
        try (StringWriter contentWriter = new StringWriter()) {
            Template template = cfg.getTemplate(fileName);
            template.process(model, contentWriter);
            return contentWriter.toString();
        } catch (IOException | TemplateException e) {
            throw new BaseException(e);
        }
    }

    //获取模板文件在jar包内的相对路径
    private List<String> scanTemplateNames() throws IOException {
        PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        Resource[] resources = resolver.getResources(TEMPLATE_SCAN_PATTERN);

        List<String> names = new ArrayList<>();
        for (Resource resource : resources) {
            //确保是一个可以读取内容的文件
            if (resource.isReadable() && !resource.getFilename().endsWith("/")) {
                String path = URLDecoder.decode(resource.getURL().getPath(), StandardCharsets.UTF_8);
                int index = path.indexOf("template/codegen/");
                if (index >= 0) {
                    String relativePath = path.substring(index + "template/codegen/".length());
                    names.add(relativePath);
                }
            }
        }

        if (names.isEmpty()) {
            throw new RuntimeException("未能加载任何模板文件！");
        }

        return names;
    }

}
