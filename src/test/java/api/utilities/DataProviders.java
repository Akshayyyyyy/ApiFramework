package api.utilities;

import java.io.IOException;

import org.testng.annotations.DataProvider;

public class DataProviders {

	@DataProvider()
	public String[][] getAllData() throws IOException{
		
		String path = "./ExcelFiles/Userdata.xlsx";
		ExcelUtilities xl = new ExcelUtilities(path);
		
		int rowNum = xl.getRowCount("Sheet1");
		int colNum = xl.getCellCount("Sheet1", 1);
		
		String[][] apiData = new String[rowNum][colNum];
		
		for(int i=1; i<=rowNum; i++) {
			for(int j=0; j<colNum; j++) {
				apiData[i-1][j] = xl.getCellData("Sheet1", i, j);
			}
		}
		
		return apiData;
		
	}
	
	
	@DataProvider()
	public String[] getUserNames() throws IOException{
		
		String path = System.getProperty("user.dir") + "//ExcelFiles//Userdata.xlsx";
		ExcelUtilities xl = new ExcelUtilities(path);
		
		int rowNum = xl.getRowCount("Sheet1");
		
		String apiData[] = new String[rowNum];
		
		for(int i = 1; i<=rowNum; i++) {
			apiData[i-1] = xl.getCellData("Sheet1", i, 1);
		}
		
		return apiData;
		
	}
	
	
}
