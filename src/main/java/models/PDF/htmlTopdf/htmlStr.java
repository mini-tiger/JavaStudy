package models.PDF.htmlTopdf;

import freemarker.template.Configuration;
import freemarker.template.Template;

import java.io.File;
import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import java.util.Locale;
import java.util.Map;

public class htmlStr {
//    private static Configuration freemarkerCfg = null;

    /**
     * freemarker渲染html
     */
    public static String freeMarkerRender(Map<String, Object> data, String htmlTmp) {
        Configuration freemarkerCfg = new Configuration();
        freemarkerCfg.setEncoding(Locale.getDefault(), "UTF-8");
        freemarkerCfg.setDefaultEncoding("UTF-8");
        freemarkerCfg.setTemplateUpdateDelay(0);


        //freemarker的模板目录
        try {
            freemarkerCfg.setDirectoryForTemplateLoading(new File("C:\\JavaStudy\\src\\main\\java\\models\\PDF\\htmlTopdf"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        Writer out = new StringWriter();
        try {
            // 获取模板,并设置编码方式
            Template template = freemarkerCfg.getTemplate(htmlTmp);
            template.setEncoding("UTF-8");
            // 合并数据模型与模板
            template.process(data, out); //将合并后的数据和模板写入到流中，这里使用的字符流
            out.flush();
            return out.toString();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                out.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        return null;
    }
}
