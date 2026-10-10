package ecom.swaglabs.utility;

import java.io.FileInputStream;
import java.io.IOException;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class TestData {

    private Workbook workbook;
    private DataFormatter formatter;

    public TestData() throws IOException {
        FileInputStream fis = new FileInputStream("./src/test/resources/TestData_Sprint.xlsx");
        workbook = WorkbookFactory.create(fis);
        formatter = new DataFormatter();
    }

    public String getData(String sheetName, int rowNum, int columnNum) {
        Sheet sheet = workbook.getSheet(sheetName);
        Row row = sheet.getRow(rowNum);
        return formatter.formatCellValue(row.getCell(columnNum));
    }

    public void closeWorkbook() throws IOException {
        workbook.close();
    }
}