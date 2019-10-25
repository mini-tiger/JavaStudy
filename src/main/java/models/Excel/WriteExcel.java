package models.Excel;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Collection;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;



public class WriteExcel {
    protected File file;
    protected OutputStream os;
    protected Workbook book = null;

    public  WriteExcel() {
        super();
    }

    public  WriteExcel(File file) throws IOException, InvalidFormatException {
        super();
        this.file = file;
        if(!file.exists()) {
            file.createNewFile();
        }
        os = new FileOutputStream(file);
        XSSFWorkbook Xbook = new XSSFWorkbook();

        // todo 大数据写入使用SXSSFWorkbook
        book = new SXSSFWorkbook(Xbook,100,true);
        Sheet sheet = book.createSheet("user");

        String[] title = {"用户名", "密码", "昵称"};
        Row titleRow = sheet.createRow(0);
        for(int i = 0; i < title.length; i++) {
            Cell cell = titleRow.createCell(i + 1);
            cell.setCellValue(title[i]);
        }
    }

    public void Write(User user) throws IOException {
        Sheet sheet = book.getSheet("user");
        int lastRowNum = sheet.getLastRowNum();
        Row currentRow = sheet.createRow(lastRowNum + 1);
        currentRow.createCell(0).setCellFormula("ROW() - 1"); // todo 函数
        currentRow.createCell(1).setCellValue(user.getUsername());
        currentRow.createCell(2).setCellValue(user.getPassword());
        currentRow.createCell(3).setCellValue(user.getNickname());
    }

    public void Write(Collection<User> users) throws IOException {
        for(User u : users) {
            this.Write(u);
        }
    }

    public void Write(User... users) throws IOException {
        for(User u : users) {
            this.Write(u);
        }
    }

    public void Extract() throws IOException {
        book.write(os);
        book.close();
    }
}

