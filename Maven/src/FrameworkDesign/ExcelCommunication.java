package FrameworkDesign;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

import java.util.HashMap;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;


public class ExcelCommunication {

	public HashMap<Integer, ArrayList<String>>  readExcel () throws IOException{
		File file = new File("/Users/AnmolParida/Eclipse_Java/TestData/xls_TestFile.xls");

		FileInputStream stream = new FileInputStream(file); 

		HSSFWorkbook workbook = new HSSFWorkbook(stream);
		HSSFSheet sheet = workbook.getSheet("MercuryTours");

		HashMap<Integer, ArrayList<String>> map = new HashMap<Integer, ArrayList<String>>();

		for (int i = 0 ;  i <= sheet.getLastRowNum() ; i++){
			HSSFRow row = sheet.getRow(i);

			ArrayList <String>  list = new ArrayList<String>();
			for (int j = 0; j < row.getLastCellNum() ; j++){

				HSSFCell cell = row.getCell(j);
				list.add(cell.getStringCellValue());
			}
			map.put(i,list);
		}
		return map;
	}
	
	public void writeExcel(int rownum, String result, String sheetName) throws  Exception{


		File file = new File("/Users/AnmolParida/Eclipse_Java/TestData/xls_TestFile.xls");

		FileInputStream stream = new FileInputStream(file); 

		HSSFWorkbook workbook = new HSSFWorkbook(stream);
		HSSFSheet sheet = workbook.getSheet("MercuryTours");
		HSSFRow row;
		HSSFCell cell;

		row = sheet.getRow(rownum);
		cell = row.createCell(7);
		cell.setCellValue(result);

		FileOutputStream outstream = new FileOutputStream(file);
		workbook.write(outstream);

	}
	
	public ArrayList<String> readIndex() throws IOException{
		File file = new File("/Users/AnmolParida/Eclipse_Java/TestData/xls_TestFile.xls");

		FileInputStream stream = new FileInputStream(file); 

		HSSFWorkbook workbook = new HSSFWorkbook(stream);
		HSSFSheet sheet = workbook.getSheet("IndexSheet");
		HSSFRow row;
		HSSFCell cell;

		ArrayList<String> list = new ArrayList<String>();
		
		for(int i = 0; i < sheet.getLastRowNum(); i ++){
			row = sheet.getRow(i);
			cell = row.getCell(0);
			list.add(cell.getStringCellValue());
		}
		return list;
	}
}
