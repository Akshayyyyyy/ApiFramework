package api.utilities;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.hssf.usermodel.HSSFWorkbookFactory;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class ExcelUtilities {
	
	FileInputStream fis;
	FileOutputStream fos;
	Workbook wb;
	Sheet sheet;
	Row row;
	Cell cell;
	CellStyle style;
	String path;
	
	ExcelUtilities(String path) {
		this.path = path;
	}
	
	
	public int getRowCount(String sheetName) throws IOException {
		fis = new FileInputStream(path);
		wb = WorkbookFactory.create(fis);
		sheet = wb.getSheet(sheetName);
		int rowCount = sheet.getLastRowNum();
		wb.close();
		fis.close();
		return rowCount;
		
	}
	
	
	public int getCellCount(String sheetName, int rowNum) throws IOException {
		fis = new FileInputStream(path);
		wb = WorkbookFactory.create(fis);
		sheet = wb.getSheet(sheetName);
		row = sheet.getRow(rowNum);
		int cellCount = row.getLastCellNum();
		wb.close();
		fis.close();
		return cellCount;
		
	}
	
	public String getCellData(String sheetName, int rowNum, int col) throws IOException {
		fis = new FileInputStream(path);
		wb = WorkbookFactory.create(fis);
		sheet = wb.getSheet(sheetName);
		row = sheet.getRow(rowNum);
		cell = row.getCell(col);
		
		DataFormatter df = new DataFormatter();
		String data;
		try {
			data = df.formatCellValue(cell);
		} catch (Exception e) {
			data = "";
		}
		
		wb.close();
		fis.close();
		return data;
		
	}
	
	
	public String getStringValueOfParticularCell(String fileName, String SheetName, int row, int cell) {
		try {
			FileInputStream fis = new FileInputStream("./ExcelFiles/" + fileName);
			Workbook wb = WorkbookFactory.create(fis);
			return wb.getSheet(SheetName).getRow(row).getCell(cell).getStringCellValue();
		} catch(Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	
}
