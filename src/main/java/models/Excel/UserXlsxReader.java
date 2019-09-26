package models.Excel;


import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.hssf.util.CellReference;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.xwpf.usermodel.ICell;


public class UserXlsxReader {

    public List<User> read(File file) throws InvalidFormatException, IOException {
        Workbook workbook = new XSSFWorkbook(file);
        Sheet sheet = workbook.getSheetAt(0);

        List<User> result = new ArrayList<User>();

        CellReference cr = new CellReference("A1");
        int row1 = cr.getRow();
        int cell1 = cr.getCol();
        System.out.printf("A1 的坐标：%d,%d\n",row1,cell1);

        CellReference cr1 = new CellReference(0,0);
        String rc = cr.formatAsString();
//        int cell2 = cr.getCol();
        System.out.printf("0,0 的坐标：%s\n",rc);


        int rowStart = sheet.getFirstRowNum()+1;
        int rowEnd = sheet.getLastRowNum();


        for (int i = rowStart; i <= rowEnd; i++) {
            Row row = sheet.getRow(i);
            User user = this.getUserFromRow(row);
            if (user != null) result.add(user);
        }
        workbook.close();
        return result;
    }

    protected User getUserFromRow(Row row) {
        if (row == null) return null;
        int current = row.getFirstCellNum();
        Cell cell = row.getCell(current);

        if (null != cell) {
            User user = new User();

            System.out.println(cell.getCellFormula());
            current++;
            System.out.println(cell.getAddress());

            cell = row.getCell(current);
            user.setUsername(cell.getStringCellValue());
            current++;

            cell = row.getCell(current);
            user.setPassword(cell.getStringCellValue());
            current++;

            cell = row.getCell(current);
            user.setNickname(cell.getStringCellValue());

            return user;
        }
        return null;
    }
}

