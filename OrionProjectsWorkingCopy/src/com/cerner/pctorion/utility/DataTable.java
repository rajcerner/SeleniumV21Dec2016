package com.cerner.pctorion.utility;


import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Hashtable;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import com.cerner.pctorion.utility.Settings;
@SuppressWarnings("unused")
/**
 * Datatable class for fetching value from the excel sheet
 * @author jk048034
 */

public class DataTable extends Settings {
	protected Hashtable<String, String> testData;
	private String testName;
	DataFormatter formatter = new DataFormatter();
	
	public DataTable(String sheet, String testName) {
		this.testName=testName;
		loadTestData(sheet);
	}

	private void loadTestData(String sheet) {
		Workbook workbook = getWorkBook(domain,component);	
		Sheet dataSheet = getSheet(workbook, sheet);
		ArrayList<String> columNames= getColumnNames(dataSheet);
		Row testDataRow= getTestDataRowForTestCase(dataSheet);
		if(testDataRow==null){
			throw new RuntimeException("Unable to find testdata row for " + testName);
		}
		testData=prepareTestDataRowHashTable(columNames,testDataRow);		
	}
	
	public String getValue(String key){
		return testData.get(key);
	}
	
	private Workbook getWorkBook(String domain,String component) {
		FileInputStream fileInputStream=null;
		Workbook workbook = null;
		try {
			String workBookPath=projectFolderPath+File.separator+"DataFiles"+File.separator+domain+File.separator+component+".XLSX"; 
			fileInputStream = new FileInputStream(workBookPath);
			workbook=new XSSFWorkbook(fileInputStream);		
			fileInputStream.close();
			return workbook;
		} catch (IOException e) {
			e.printStackTrace();
			throw new RuntimeException(e.getMessage());
		}
	}
	
	private Sheet getSheet(Workbook workbook, String sheet) {
		Sheet worksheet = workbook.getSheet(sheet);
		return worksheet;
	}

	private Hashtable<String, String> prepareTestDataRowHashTable(ArrayList<String> columNames, Row testDataRow) 
	{
		Hashtable<String, String> testDataRowHashTable= new Hashtable<String,String>();	
		testDataRowHashTable.clear();
		for (Cell cell : testDataRow) {
			String columnName=columNames.get(cell.getColumnIndex());
			String columnValue=formatter.formatCellValue(cell);
			testDataRowHashTable.put(columnName, columnValue);
		}
		return testDataRowHashTable;

	}
	
	private ArrayList<String> getColumnNames(Sheet testDataSheet) {
		ArrayList<String> columnNameList= new ArrayList<String>();
		Row row = testDataSheet.getRow(0);
		for (Cell cell : row) {
			columnNameList.add(cell.getStringCellValue());
		}
		return columnNameList;
	}

	private Row getTestDataRowForTestCase(Sheet testDataSheet) {
		for (Row row : testDataSheet) {
			if (IsRequiredTestCaseRow(row,this.testName)) {
				return row;
			} 
		}		
		return null;
	}

	private boolean IsRequiredTestCaseRow(Row row, String testCaseName) {
		Cell testCaseIdCell= row.getCell(0);
		String testCaseId=testCaseIdCell.getStringCellValue().toString();
		if (testCaseId.equals(testCaseName)) {
			return true;
		}
		return false;
	}

	public static String[] getMultipleCellValues(String cellValue, int numberofvalues){
		System.out.println("cellValue "+cellValue);
		String[] multipleCellValues = cellValue.split("\\|");
		if(multipleCellValues.length!=numberofvalues){
			System.out.println(cellValue +" have incorrect value");
		}else{
			for(int i=0; i<numberofvalues; i++){
				System.out.println(multipleCellValues[i]);
			}
		}
		return multipleCellValues;
	}
	
}
