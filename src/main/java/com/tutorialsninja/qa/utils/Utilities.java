package com.tutorialsninja.qa.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Date;

import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.io.FileHandler;

public class Utilities {

	public static final int implicit_wait_time = 10;
	public static final int page_load_time = 10;

	public static String emailTimeStamp() {
		Date date = new Date();
		String timestamp = date.toString().replace(" ", "").replace(":", "");
		return "reddy" + timestamp + "@gmail.com";

	}

	public static Object[][] getDataFromExcel(String sheetName) {
		File file = new File(System.getProperty("user.dir")
				+ "//src//main//java//com//tutorialsninja//qa//testdata//TutorialsNinjaTestData.xlsx");
		XSSFWorkbook workbook = null;
		try {
			FileInputStream fis = new FileInputStream(file);

			workbook = new XSSFWorkbook(fis);
		} catch (IOException e) {
			e.printStackTrace();
		}

		XSSFSheet sheet = workbook.getSheet(sheetName);
		int rowcount = sheet.getLastRowNum();
		int columncount = sheet.getRow(0).getLastCellNum();
		

		Object objectdata[][] = new Object[rowcount][columncount];

		for (int i = 0; i < rowcount; i++) {
			XSSFRow row = sheet.getRow(i+1);
			for (int j = 0; j < columncount; j++) {

				XSSFCell cell = row.getCell(j);

				CellType cellType = cell.getCellType();

				switch (cellType) {

				case STRING:
					objectdata[i][j] = cell.getStringCellValue();
					break;
				case NUMERIC:
					objectdata[i][j] = Integer.toString((int) cell.getNumericCellValue());

					break;

				case BOOLEAN:
					objectdata[i][j] = cell.getBooleanCellValue();
					break;

				}
			}
		}
		return objectdata;

	}
	
	public static String captureScreenshot(WebDriver driver, String testname) {
		
		File sourcefile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		String destinationScreenshotPath = System.getProperty("user.dir")+"//screenshots//"+testname+".png";
		try {
			FileHandler.copy(sourcefile, new File(destinationScreenshotPath));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return destinationScreenshotPath;
	}

}
