package com.cerner.pctorion.chart;

import com.cerner.pctorion.utility.DataTable;
/*
 * @uthor: rv042687 / roshan
 */
public class VisitListData {

	public static final String visitLstTxt="Visit List";
	public static final String futTxt ="Future";
	public static final String prevTxt="Previous";
	
	public static final String noFutTxt = "No future visits for this patient";
	public static final String noPrevTxt = "No previous visits for this patient";
	
	
	
	/*
	 * Test Case Data for: ORN_VR_VISITLIST
	 * 
	 * */
	
	public static final String testName = "ORN_VR_VisitList";
	public static final String sheet = "Chart";
	public static DataTable dataTable = new DataTable(sheet, testName);
	
	
	public static final String URL = dataTable.getValue("URL");	
	public static String UserName = dataTable.getValue("UserName");
	public static String Password = dataTable.getValue("Password");
	
	
	
	public static final String PatientA = dataTable.getValue("PatientA");
	public static final String FIN_A = dataTable.getValue("FINA");
	public static final String PatientB = dataTable.getValue("PatientB");
	public static final String FIN_B = dataTable.getValue("FINB");
}



