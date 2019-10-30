package models.PDF;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;
import org.apache.commons.io.FileUtils;

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
 * 功能描述   1.通过 pdf模板写入 模板变量数据，然后追加个表格
 */
public class release {

    public static void fillTemplate(String templatePath, String newPDFPath, Map<String, String> map) { // 通过map写入模板数据

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

            out.write(bos.toByteArray());
            out.flush();
            out.close();
            bos.close();

//            Document document = new Document();
//            PdfCopy copy = new PdfCopy(document, out);
//            document.open();
//            PdfImportedPage importPage = copy.getImportedPage(new PdfReader(bos.toByteArray()), 1);
//            copy.addPage(importPage);
//            document.close();

        } catch (IOException e) {
            e.printStackTrace();
        } catch (DocumentException e) {
            e.printStackTrace();
        }

    }

    /**
     * 生成最终版本的pdf
     *
     * @param newPath      已写入数据的pdf模板路径
     * @param finalPath    最终版本的pdf生成路径
     * @param baselineData
     * @throws Exception
     */
    public static void generateFinalPdf(String newPath, String finalPath, String baselineType, Object[][] baselineData) throws Exception {
        FileOutputStream outputStream = new FileOutputStream(finalPath);
        PdfReader reader = new PdfReader(newPath);// 读取pdf,已经是加载过 模板变量的PDF
        Rectangle pageInstance = reader.getPageSize(1); // 获取第1页的对象
        Document document = new Document(pageInstance);
        PdfWriter writer = PdfWriter.getInstance(document, outputStream); //PDF可写的对象
        document.setPageSize(PageSize.A4); // 设置为A4大小
        document.open();
        PdfContentByte cb = writer.getDirectContentUnder(); // 页面的对象，画布
        PdfImportedPage pageTemplate = writer.getImportedPage(reader, 1); // 读取PDF为一页数据

        cb.addTemplate(pageTemplate, 0, 0); // 将之前的PDF 添加到0,0 位置

        BaseFont bf = BaseFont.createFont(BaseFont.HELVETICA, BaseFont.CP1252, BaseFont.NOT_EMBEDDED);
        cb.beginText();
        cb.setFontAndSize(bf, 12);
//        cb.setTextMatrix(100, 100); // 在距离左侧100，距离下面100的位置
//        System.out.println(document.leftMargin());
//        System.out.println(document.topMargin());
        cb.setTextMatrix(100 + document.leftMargin(), PageSize.A4.getHeight() - 200 - document.topMargin());

        cb.showText("Text at position " + String.valueOf(100 + document.leftMargin()) + "," + String.valueOf(PageSize.A4.getHeight() - 200 - document.topMargin()));
        cb.endText();

//        document.newPage();//新创建一页来存放后面生成的表格

        if ("create".equals(baselineType)) {
            PdfPTable table = generatePdfATATable(baselineData);//todo 此处为生成的表格及内容方法

            /**
             * rowStart         0   起始行
             * rowEnd           -1  表示全部行
             * xPos             表格横坐标- 从左向右开始计算
             * yPos             表格纵坐标- 从下向上开始计算
             * canvas           画布
             */
            table.writeSelectedRows(0, -1, 60, 500, cb);

//            document.add(table);

        }
        document.close();
        reader.close();

    }

    public static PdfPTable generatePdfATATable(Object[][] datas) throws Exception {
        float[] widths = {144, 113, 191}; // 每列的宽度
        PdfPTable table = new PdfPTable(widths);
        table.setLockedWidth(true);
        table.setTotalWidth(458); // 表格总共有宽度
        table.setHorizontalAlignment(Element.ALIGN_LEFT);

//        BaseFont bfChinese = BaseFont.createFont("STSong-Light", "UniGB-UCS2-H", BaseFont.NOT_EMBEDDED);
//        Font fontChinese = new Font(bfChinese, 10.5F, Font.NORMAL);// 五号
//        Paragraph ret = new Paragraph("附表1： 基线按ATA章节分类情况统计表", fontChinese);

        //        Object[][] datas = {{"区域产品销售额"},{"区域", "总销售额(万元)", "总利润(万元)简单的表格"}, {"江苏省" , 9045,  2256}, {"广东省", 3000, 690}};
        for (int i = 0; i < datas.length; i++) {
            for (int j = 0; j < datas[i].length; j++) {
                PdfPCell pdfCell = new PdfPCell();
                pdfCell.setMinimumHeight(30);

                //设置单元格样式
                pdfCell.setHorizontalAlignment(Element.ALIGN_CENTER);
                pdfCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                pdfCell.setBackgroundColor(new BaseColor(0xdd7e6b));

                pdfCell.setBorder(0);
                pdfCell.setBorderWidthTop(0.1f);
                pdfCell.setBorderWidthBottom(0.1f);
                pdfCell.setBorderWidthLeft(0.1f);
                pdfCell.setBorderWidthRight(0.1f);
                pdfCell.setBorderColorBottom(new BaseColor(0x674ea7));
                pdfCell.setBorderColorLeft(new BaseColor(0x674ea7));
                pdfCell.setBorderColorRight(new BaseColor(0x674ea7));
                pdfCell.setBorderColorTop(new BaseColor(0x674ea7));

                //设置单元格文本字体样式
                Font font = getPdfChineseFont();
                if (i == datas.length - 1 && j == 3 - 1) {
                    font.setColor(new BaseColor(0xff0000));
                    font.setSize(16);
                    font.setStyle("bold");
                    font.setStyle("italic");
                    font.setStyle("underline");
                }

                //合并单元格
                if (i == 0 && j == 0) {
                    pdfCell.setRowspan(1);
                    pdfCell.setColspan(3);
                }
//                System.out.println(datas[i][j]);
                Paragraph paragraph = new Paragraph(String.valueOf(datas[i][j]), font);

                pdfCell.setPhrase(paragraph);

                table.addCell(pdfCell);
            }
        }
        //单元格插入图片
        byte[] bt = FileUtils.readFileToByteArray(new File("C:\\JavaStudy\\src\\main\\java\\models\\PDF\\2.png"));
        PdfPCell pdfCell = new PdfPCell();
        pdfCell.setImage(Image.getInstance(bt));
        table.addCell(pdfCell);

        //插入子表格
        pdfCell = new PdfPCell();
        pdfCell.setRowspan(1);
        pdfCell.setColspan(2);

        PdfPTable suTtable = new PdfPTable(new float[]{100, 100});
        PdfPCell subPdfCell = new PdfPCell();
        subPdfCell.setPhrase(new Paragraph("sub1", getPdfChineseFont()));
        suTtable.addCell(subPdfCell);
        subPdfCell = new PdfPCell();
        subPdfCell.setPhrase(new Paragraph("sub2", getPdfChineseFont()));
        suTtable.addCell(subPdfCell);

        pdfCell.addElement(suTtable);
        table.addCell(pdfCell);
//        ret.add(table);
        return table;
    }

    public static Font getPdfChineseFont() throws Exception {
        BaseFont bfChinese = BaseFont.createFont("STSongStd-Light", "UniGB-UCS2-H",
                BaseFont.NOT_EMBEDDED);
        Font fontChinese = new Font(bfChinese, 12, Font.NORMAL);
        return fontChinese;
    }

    public static void main(String[] args) throws Exception {
        File directory = new File("");
        Path p = Paths.get(directory.getAbsolutePath(), "src", "main", "java", "models", "PDF", "tplpdf.pdf");
        // 模板路径
        String templatePath = p.toString();
        // 模板填充后的文件路径
        String newPDFPath = p.toString().replace("tplpdf.pdf", "release.pdf");
        Map<String, String> map = new HashMap<>();
        map.put("kehuname", "中国工商银行");
        map.put("month", "中国农业银行");

        // 追加表格后的路径
        String finalPath = p.toString().replace("tplpdf.pdf", "release123.pdf");
        fillTemplate(templatePath, newPDFPath, map); // 通过模板生成数据
        Object[][] datas = {{"区域产品销售额"}, {"区域", "总销售额(万元)", "总利润(万元)简单的表格"}, {"江苏省", 9045, 2256}, {"广东省", 3000, 690}};
        generateFinalPdf(newPDFPath, finalPath, "create", datas);

    }

}