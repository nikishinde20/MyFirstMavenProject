package com.qa.utility;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelUtils {

	private static String filePath="testdata/testdata.xlsx";
	private static Workbook workbook;
	
	//Read data
	public static String getCellData(String sheetname, int row, int col) throws IOException
	{
		FileInputStream fis =  new FileInputStream(filePath);
		workbook = new XSSFWorkbook(fis);
		Sheet sheet = workbook.getSheet(sheetname);
		String data = sheet.getRow(row).getCell(col).toString();
		workbook.close();
		fis.close();
		return data;
	}
	
	//Write data
	public static void setCellData(String sheetname, int row, int col, String value) throws Exception
	{
		FileInputStream fis =  new FileInputStream(filePath);
		workbook = new XSSFWorkbook(fis);
		Sheet sheet = workbook.getSheet(sheetname);
		
		Row r = sheet.getRow(row);
		if(r==null)
			r = sheet.createRow(row);
		
		Cell c = r.getCell(col);
		if(c==null)
			c = r.createCell(col);
		
		c.setCellValue(value);
		
		FileOutputStream fos = new FileOutputStream(filePath);
		workbook.write(fos);
		workbook.close();
		fis.close();
		fos.close();
	}
}
