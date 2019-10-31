package models.PDF.htmlTopdf;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.tool.xml.XMLWorkerFontProvider;
import com.itextpdf.tool.xml.XMLWorkerHelper;


import freemarker.template.Configuration;
import freemarker.template.Template;

import javax.print.attribute.standard.PagesPerMinute;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import java.nio.charset.Charset;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by lujianing on 2017/5/7.
 */
import com.lowagie.text.DocumentException;
import com.lowagie.text.pdf.BaseFont;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import org.xhtmlrenderer.pdf.ITextFontResolver;
import org.xhtmlrenderer.pdf.ITextRenderer;

import java.io.*;
import java.util.HashMap;

/**
 * Created by admin on 2017/7/5.
 */
public class first {

//    private static final String CONTRACT = "D:\\JavaStudy\\src\\main\\java\\models\\PDF\\htmlTopdf";//文件存储路径
//    private static final String TEMPLATE = "D:\\JavaStudy\\src\\main\\java\\models\\PDF\\htmlTopdf";//模板存储路径


    private static final String PDFNAME = "hello.pdf";//最后生成的pdf文件名
    private static final String HTMLNAME = "hello.html";//中间使用TTL模板生成的html文件名
    private static final String FTL = "hello.ftl";//FTL模板文件名

    public static void contractHandler() throws Exception {
        File directory = new File("");
        Path BasePath = Paths.get(directory.getAbsolutePath(), "src", "main", "java", "models", "PDF", "htmlTopdf");

        // 获取本地模板存储路径、文件存储路径
        String templateDirPath = BasePath.toString();
        String contractPath = BasePath.toString();

        // 组装html和pdf合同的全路径URL
        String ftlPath = contractPath +File.separator+ FTL;
        String localHtmlUrl = contractPath +File.separator+ HTMLNAME ;
        String localPdfPath =  contractPath +File.separator+ PDFNAME ;

        // 判断本地路径是否存在如果不存在则创建
//        File localFile = new File(localPdfPath);
//        if (!localFile.exists()) {
//            localFile.mkdirs();
//        }

//        templateName = templateName + ".ftl";
        htmHandler(BasePath.toString(), FTL, localHtmlUrl);// 通过 FTL模板 生成html

        pdfHandler(localHtmlUrl, localPdfPath);// 根据生成好的html生成pdf合同
//        deleteFile(localHtmlUrl);// 删除html格式合同

        System.out.println("PDF生成成功");
    }

    private static void deleteFile(String fileUrl) {
        File file = new File(fileUrl);
        file.delete();
    }

    private static void pdfHandler(String htmUrl, String pdfUrl) throws IOException, DocumentException {
        try {
            InputStream htmlFileStream = new FileInputStream(htmUrl);
            // 创建一个document对象实例
            Document document = new Document(PageSize.A4.rotate()); // todo 横向
            // 为该Document创建一个Writer实例
            PdfWriter pdfwriter = PdfWriter.getInstance(document,
                    new FileOutputStream(pdfUrl));
            pdfwriter.setViewerPreferences(PdfWriter.HideToolbar);
            // 打开当前的document
            document.open();
            InputStreamReader isr = new InputStreamReader(htmlFileStream, "UTF-8");
            XMLWorkerHelper.getInstance().parseXHtml(pdfwriter, document,htmlFileStream,null,null,new MyFontsProvider());
            //XMLWorkerHelper.getInstance().p
            document.close();

        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    private static void htmHandler(String templateDirPath, String templateName, String hHtmlUrl) throws IOException, TemplateException {
        // 第一步：创建一个Configuration对象，直接new一个对象。构造方法的参数就是freemarker对于的版本号。
        Configuration configuration = new Configuration();

        // 第二步：设置模板文件所在的路径。
        configuration.setDirectoryForTemplateLoading(new File(templateDirPath));

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
        for (int i=0;i<1000;i++){
            test1.put(String.valueOf(i),String.valueOf(i*i));
        }
        dataModel.put("hello", test1);


        // 第六步：创建一个Writer对象，一般创建一FileWriter对象，指定生成的文件名。
        Writer out = new FileWriter(new File(hHtmlUrl));

        Template template = configuration.getTemplate(templateName);
        // 第七步：调用模板对象的process方法输出文件。
        template.process(dataModel, out);

        // 第八步：关闭流。
        out.close();
    }

    public static void main(String[] args) throws Exception {


        contractHandler();
    }
}
class MyFontsProvider extends XMLWorkerFontProvider {

    public MyFontsProvider(){
        super(null, null);
    }

    @Override
    public Font getFont(final String fontname, String encoding, float size, final int style) {
        String fntname = fontname;
        if (fntname == null) {
            fntname = "宋体";//windows下 //linux系统下，需要fc-cache, simsun.ttf
            //fntname = "fontFile/simsun.ttf";
        }
        if (size == 0) {
            size = 4;
        }
        return super.getFont(fntname, encoding, size, style);
    }
}