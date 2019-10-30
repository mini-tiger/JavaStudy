package models.PDF.htmlTopdf;

import freemarker.template.Configuration;
import freemarker.template.Template;

import java.io.File;
import java.io.FileWriter;
import java.io.Writer;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class s {
    public static void main(String[] args) throws Exception {
        // 第一步：创建一个Configuration对象，直接new一个对象。构造方法的参数就是freemarker对于的版本号。
        Configuration configuration = new Configuration();

        // 第二步：设置模板文件所在的路径。
        configuration.setDirectoryForTemplateLoading(new File("D:\\JavaStudy\\src\\main\\java\\models\\PDF\\htmlTopdf"));

        // 第三步：设置模板文件使用的字符集。一般就是utf-8.
        configuration.setDefaultEncoding("utf-8");

        // 第四步：加载一个模板，创建一个模板对象。 Template template = configuration.getTemplate("hello.ftl");

        // 第五步：创建一个模板使用的数据集，可以是pojo也可以是map。一般是Map。
        Map dataModel = new HashMap();
        // 向数据集中添加数据
        dataModel.put("key", "this is my first freemarker test葵花药业.");

        HashMap<String, String> test1 = new LinkedHashMap<String, String>();

        test1.put("one", "1");
        test1.put("two", "2");
        test1.put("three", "3");
        dataModel.put("hello", test1);


        // 第六步：创建一个Writer对象，一般创建一FileWriter对象，指定生成的文件名。
        Writer out = new FileWriter(new File("D:\\JavaStudy\\src\\main\\java\\models\\PDF\\htmlTopdf\\hello.html"));

        Template template = configuration.getTemplate("hello.ftl");
        // 第七步：调用模板对象的process方法输出文件。
        template.process(dataModel, out);

        // 第八步：关闭流。
        out.close();
    }
}
