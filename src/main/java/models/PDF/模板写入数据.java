package models.PDF;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.*;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

/**
 * @author aijiao
 * @date 2018/08/01
 */
public class 模板写入数据 {

    public static void fillTemplate() {
        File directory = new File("");
        Path p = Paths.get(directory.getAbsolutePath(),"src","main","java","models","PDF","tplpdf.pdf");
        // 模板路径
        String templatePath = p.toString();
        // 生成的新文件路径
        String newPDFPath = "C:\\JavaStudy\\src\\main\\java\\models\\PDF\\模板写入数据.pdf";
        PdfReader reader;
        FileOutputStream out;
        ByteArrayOutputStream bos;
        PdfStamper stamper;
        try {
            out = new FileOutputStream(newPDFPath);
            reader = new PdfReader(templatePath);
            bos = new ByteArrayOutputStream();
            stamper = new PdfStamper(reader, bos);
            AcroFields form = stamper.getAcroFields();
            Map<String, String> map = new HashMap<>();
            map.put("kehuname", "中国工商银行");
            map.put("month", "中国农业银行");
//            map.put("test3", "中国建设银行");
//            int i = 0;
            java.util.Iterator<String> it = form.getFields().keySet().iterator();
            while (it.hasNext()) {

                String name = it.next();
                form.setField(name, map.get(name));
            }
            //true代表生成的PDF文件不可编辑
            stamper.setFormFlattening(true);
            stamper.close();



            Document document = new Document();
            PdfCopy copy = new PdfCopy(document, out);
            document.open();
            PdfImportedPage importPage = copy.getImportedPage(new PdfReader(bos.toByteArray()), 1);
            copy.addPage(importPage);
            document.close();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (DocumentException e) {
            e.printStackTrace();
        }

    }

    public static void main(String[] args) {
        fillTemplate();
    }

}