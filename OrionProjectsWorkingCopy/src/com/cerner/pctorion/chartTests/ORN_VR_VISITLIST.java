package com.cerner.pctorion.chartTests;


import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Test;

import com.cerner.pctorion.chart.VisitListDetailsPage;
import com.cerner.pctorion.chart.VisitListDetailsPage.EncounterFields;
import com.cerner.pctorion.chart.VisitListReviewPage;
import com.cerner.pctorion.chart.VisitListReviewPage.EncNo;
import com.cerner.pctorion.chart.VisitListSplitViewPage;
import com.cerner.pctorion.platform.EncounterViewPage;
import com.cerner.pctorion.platform.LandingPage;
import com.cerner.pctorion.platform.LoginPage;
import com.cerner.pctorion.platform.PatientSearchPage;
import com.cerner.pctorion.utility.DataTable;
import com.cerner.pctorion.utility.Settings;
import com.cerner.pctorion.utility.UtilityMethods;
import com.relevantcodes.extentreports.LogStatus;


public class ORN_VR_VISITLIST extends Settings{


	@Test
	public void ornVrVisitList() throws InterruptedException
	{
		
		LandingPage lndPage = PageFactory.initElements(driver, LandingPage.class); 
		LoginPage logPage = PageFactory.initElements(driver, LoginPage.class);     
		PatientSearchPage patSrch  = PageFactory.initElements(driver, PatientSearchPage.class); 
		EncounterViewPage encSelc  = PageFactory.initElements(driver, EncounterViewPage.class);
		
		VisitListReviewPage vstRev = PageFactory.initElements(driver, VisitListReviewPage.class);		
		VisitListDetailsPage vstDet = PageFactory.initElements(driver, VisitListDetailsPage.class);
		VisitListSplitViewPage vstPg = PageFactory.initElements(driver, VisitListSplitViewPage.class);
		
		
		UtilityMethods  utm = new UtilityMethods();
		String testName = "ORN_VR_VisitList";
		String sheet = "Chart";
		DataTable dataTable = new DataTable(sheet, testName);

		
		String baseUrl = dataTable.getValue("URL");
	  	String username = dataTable.getValue("UserName");
	  	String password = dataTable.getValue("Password");
		String systemDate= UtilityMethods.systemDate();
		String patName = dataTable.getValue("PatientA");
		String fin = dataTable.getValue("FIN");
		
		report = UtilityMethods.Instance(testName, browser) ;
		test = report.startTest(testName);
	  
	    
		
	  	driver.get(baseUrl);
	  
	  	
	  	test.log(LogStatus.INFO, "Step-1",  "User Logins & Loads Patient");
	  	utm.clickButton(lndPage.Login,test);
	  	logPage.enterUsernamePassword(username, password);
	  	utm.clickButton(logPage.Login,test);  	
	  	utm.enterText(patSrch.PatientSearchTextBox, patName, test);
	  	patSrch.selectPatient(patName, test);	
	  	encSelc.selectEncounter(fin, test);
	  
	  	
	  	/*
	  	 * Step-2
	  	 * */
	    String imageName = "Screen_1"; //For Screenshots
	  	test.log(LogStatus.INFO, "Step-2",  "Visit List Profile Screen");
	  	utm.verify(vstRev.pageHead.getText(), "");
	    utm.verify(vstRev.prevHead.getText(), "");
	    utm.verify(vstRev.futHead.getText(), "");
	  	utm.verifyWithScreen(vstRev.vmBtn.getText(),"View More...", testName, imageName);		
	  	
	  	
	  	/*
	  	 * Step-3
	  	 */
	  	imageName = "Screen_2";
	  	test.log(LogStatus.INFO, "Step-3",  "Encounter Details Page");
	  	Thread.sleep(50000);
	  	vstRev.selectPrevious(EncNo.TWO);
	  	Thread.sleep(50000);
	  	utm.verifyWithScreen(vstDet.titleTxt.getText(),"", testName, imageName);
	  	utm.verify(vstDet.verifyEncField(EncounterFields.ADMIT_DATE), "Step-3 Admit Date");
	  	utm.verify(vstDet.verifyEncField(EncounterFields.ATTENDING_PHY), "Step-3 Attending Phy");
	  	utm.verify(vstDet.verifyEncField(EncounterFields.DISCHARGE_DATE), "Step-3 Discharge Date");
	  	utm.verify(vstDet.verifyEncField(EncounterFields.ENC_STATUS), "Step-3 Enc Status");
	  	utm.verify(vstDet.verifyEncField(EncounterFields.ENC_TYPE), "Step-3 Enc Type");
	  	utm.verify(vstDet.verifyEncField(EncounterFields.FIN), "Step-3 Fin");
	  	utm.verify(vstDet.verifyEncField(EncounterFields.LOCATION), "Step-3 Location");
	  	utm.verify(vstDet.verifyEncField(EncounterFields.REASON_FOR_VISIT), "Step-3 Reason For Visit");
	  	utm.verify(vstDet.verifyEncField(EncounterFields.ROOM), "Step-3 Room");
	  	utm.verify(vstDet.verifyEncField(EncounterFields.SERVICE), "Step-3 Service");
	  	
	  	
	  	test.log(LogStatus.INFO, "Step-4",  "Close Details Page");
	  	vstDet.closeBtn.click();
	  	
	  	Thread.sleep(50000);
	  	test.log(LogStatus.INFO, "Step-5",  "Close Details Page");
	  	vstRev.vmBtn.click();
	  	Thread.sleep(50000);
	  	utm.verify(vstPg.bckBtn.isEnabled(), "Back Button");
	  	utm.verify(vstPg.sidePnl.isEnabled(), "Side Panel");
	  	utm.verify(vstPg.prevBtn.isSelected(), "Previous Button Selected by Default");
	  	utm.verify(vstPg.prevBtn.isSelected(), "Previous Button Selected");
	  	
	  	utm.verify(vstPg.prevBtn.getText(), "");
	  	utm.verify(vstPg.futBtn.getText(), "");
	  	utm.verify(vstPg.prevHead.getText(), "");
	  	utm.verify(vstPg.prevHead.getText(), "");
	  	utm.verify(vstPg.titleTxt.getText(), "");
	  	
	  	utm.verify(vstPg.verifyEncData(dataTable.getValue("Enc1_Time")),  " Step Description");		
		
	}


}
