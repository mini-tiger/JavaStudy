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
import java.util.Map;

import static models.PDF.htmlTopdf.htmlStr.freeMarkerRender;

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

    private static final String CONTRACT = "D:\\JavaStudy\\src\\main\\java\\models\\PDF\\htmlTopdf";//文件存储路径
    private static final String TEMPLATE = "D:\\JavaStudy\\src\\main\\java\\models\\PDF\\htmlTopdf";//模板存储路径


    private static final String PDFNAME = "11";//pdf文件名
    private static final String HTMLNAME = "template.html";//html文件名


    public static void contractHandler(String templateName,
                                       HashMap paramMap) throws Exception {
        // 获取本地模板存储路径、文件存储路径
        String templatePath = TEMPLATE;
        String contractPath = CONTRACT;
        // 组装html和pdf合同的全路径URL
        String localHtmlUrl = contractPath + HTMLNAME + ".html";
        String localPdfPath = contractPath + "/";
        // 判断本地路径是否存在如果不存在则创建
        File localFile = new File(localPdfPath);
        if (!localFile.exists()) {
            localFile.mkdirs();
        }
        String localPdfUrl = localFile + "/" + PDFNAME + ".pdf";
//        templateName = templateName + ".ftl";
//        htmHandler(templatePath, templateName, localHtmlUrl, paramMap);// 生成html合同
        pdfHandler("D:\\JavaStudy\\src\\main\\java\\models\\PDF\\htmlTopdf\\hello.html", localPdfUrl);// 根据html合同生成pdf合同
        deleteFile(localHtmlUrl);// 删除html格式合同

        System.out.println("PDF生成成功");
    }

    private static void deleteFile(String fileUrl) {
        File file = new File(fileUrl);
        file.delete();
    }

    private static void pdfHandler(String htmUrl, String pdfUrl) throws IOException, DocumentException {
//        File htmFile = new File(htmUrl);
////        File pdfFile = new File(pdfUrl);
////        String url = htmFile.toURI().toURL().toString();
////        OutputStream os = new FileOutputStream(pdfFile);
////        ITextRenderer renderer = new ITextRenderer();
////        renderer.setDocument(url);
////        ITextFontResolver fontResolver = renderer.getFontResolver();
////        fontResolver.addFont(TEMPLATE +File.separator+ "simsun.ttc", BaseFont.IDENTITY_H, BaseFont.NOT_EMBEDDED);
////        renderer.layout();
////        renderer.createPDF(os);
////        os.close();
        try {
            InputStream htmlFileStream = new FileInputStream("D:\\JavaStudy\\src\\main\\java\\models\\PDF\\htmlTopdf\\hello.html");
            // 创建一个document对象实例
            Document document = new Document(PageSize.A4.rotate());
            // 为该Document创建一个Writer实例
            PdfWriter pdfwriter = PdfWriter.getInstance(document,
                    new FileOutputStream("D:\\JavaStudy\\src\\main\\java\\models\\PDF\\htmlTopdf\\11.pdf"));
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

    private static void htmHandler(String templatePath, String templateName, String hHtmlUrl, HashMap paramMap) throws IOException, TemplateException {
        Configuration cfg = new Configuration();
        cfg.setDefaultEncoding("UTF-8");
        cfg.setDirectoryForTemplateLoading(new File(templatePath));
        Template template = cfg.getTemplate(templateName);
        File outHtmFile = new File(hHtmlUrl);
        Writer writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(outHtmFile)));
        template.process(paramMap, writer);
        writer.close();
    }

    public static void main(String[] args) throws Exception {
        String templateName = "template.html";
        HashMap paramMap = new HashMap<>();
        paramMap.put("user", "nihao");
        paramMap.put("url", "192.168.1.2");
        paramMap.put("name", "生成成功");
        contractHandler(templateName, paramMap);
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
            fntname = "宋体";//windows下
            //fntname = "fontFile/simsun.ttf";//linux系统下
        }
        if (size == 0) {
            size = 4;
        }
        return super.getFont(fntname, encoding, size, style);
    }
}