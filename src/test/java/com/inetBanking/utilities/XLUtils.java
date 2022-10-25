package com.inetBanking.utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;;

public class XLUtils {
	
	public static FileInputStream fis;
	public static FileOutputStream fos;
	public static XSSFWorkbook wb;
	public static XSSFSheet ws;
	public static XSSFRow row;
	public static XSSFCell cell;
	
	
	public XLUtils(String xlfile) throws Exception {
		
		try {
			File s = new File(xlfile);
			fis = new FileInputStream(s);
			wb = new XSSFWorkbook(fis);
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	public  int getRowCount(String xlsheet) throws IOException {
		
		int rowCount = wb.getSheet(xlsheet).getLastRowNum();
		//rowCount = rowCount + 1;
		wb.close();
		fis.close();
		return rowCount;
	}
	
	public  int getCellCount(String xlsheet, int rownum) throws IOException {
		
		ws = wb.getSheet(xlsheet);
		row = ws.getRow(0);
		int cellCount = row.getLastCellNum();
		wb.close();
		fis.close();
		return cellCount;
		
	}
	
	public String getCellData(String xlsheet, int rownum, int colnum) throws IOException {
		
		/*fis = new FileInputStream(xlfile);
		wb = new XSSFWorkbook(fis);
		ws = wb.getSheet(xlsheet);*/
		row = ws.getRow(rownum);
		cell = row.getCell(colnum);
		String data = ws.getRow(rownum).getCell(colnum).getStringCellValue();
		
		/*try {
			DataFormatter formatter = new DataFormatter();
			String cellData = formatter.formatCellValue(cell);
			return cellData;
		} catch (Exception e) {
			data = "";
		}*/
		wb.close();
		fis.close();
		return data;
		
	}
	
	public  void setCellData(String xlfile, String xlsheet, int rownum, int colnum, String data) throws IOException{
		
		fis = new FileInputStream(xlfile);
		wb = new XSSFWorkbook(fis);
		ws = wb.getSheet(xlsheet);
		row = ws.getRow(rownum);
		cell=row.createCell(colnum);
		cell.setCellValue(data);
		fos = new FileOutputStream(xlfile);
		wb.write(fos);
		wb.close();
		fis.close();
		fos.close();
	}

	
	/*public static Object[][] getTableArray(String xlfile, String xlsheet, int currentRow) throws Exception{
		
		String[][] tabArray = null;
		
		
		fis = new FileInputStream(xlfile);
		wb = new XSSFWorkbook(fis);
		ws = wb.getSheet(xlsheet);
		
		int rownum = XLUtils.getRowCount(xlsheet);
		int colcount = XLUtils.getCellCount(xlfile, xlsheet, currentRow);
		tabArray = new String[rownum][colcount];
		
		for(int i =1;i<=rownum;i++) {
			
			for(int j=0;j<colcount;j++) {
				
				tabArray[i-1][j]=XLUtils.getCellData(xlfile, xlsheet, i, j);
			}
		}

		
		
		return(tabArray);
		
	}*/
}
