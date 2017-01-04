package com.cerner.pctorion.chartTests;


import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Test;

import com.cerner.pctorion.chart.VisitListData;
import com.cerner.pctorion.chart.VisitListDetailsPage;
import com.cerner.pctorion.chart.VisitListDetailsPage.EncounterFields;
import com.cerner.pctorion.chart.VisitListReviewPage;
import com.cerner.pctorion.chart.VisitListReviewPage.EncNo;
import com.cerner.pctorion.chart.VisitListSplitViewPage;
import com.cerner.pctorion.platform.EncounterViewPage;
import com.cerner.pctorion.platform.EstablishRelationPage;
import com.cerner.pctorion.platform.LandingPage;
import com.cerner.pctorion.platform.LoginPage;
import com.cerner.pctorion.platform.PatientSearchPage;
import com.cerner.pctorion.utility.DataTable;
import com.cerner.pctorion.utility.Settings;
import com.cerner.pctorion.utility.UtilityMethods;
import com.relevantcodes.extentreports.LogStatus;


/*
 *@uthor: rv042687 / roshan 
 */
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
		EstablishRelationPage estPg = PageFactory.initElements(driver, EstablishRelationPage.class);
		UtilityMethods  utm = new UtilityMethods();
		
		
		
		String testName = "ORN_VR_VisitList";
		String sheet = "Chart";
		DataTable dataTable = new DataTable(sheet, testName);

		
		String baseUrl = dataTable.getValue("URL");
		
	  	String username = dataTable.getValue("UserName");
	  	String password = dataTable.getValue("Password");  	
		String systemDate= UtilityMethods.systemDate();	
		String patName = dataTable.getValue("PatientA");
		String fin = dataTable.getValue("FINA");
		
		
		report = UtilityMethods.Instance(testName, browser) ;
		
		
		test = report.startTest(VisitListData.testName);
	  	driver.get(VisitListData.URL);
	  
	  	/* Step2 */
	  	test.log(LogStatus.INFO, "Step-2",  "User Logins & Loads Patient");
	  	String imageName = "Step1_VisitList_Profile_View"; //For Screenshots	
	  	utm.clickButton(lndPage.Login,test);
	  	logPage.enterUsernamePassword(VisitListData.UserName, VisitListData.Password);
	  	utm.clickButton(logPage.Login,test);  	
	  	utm.enterText(patSrch.PatientSearchTextBox, VisitListData.PatientA, test);
	  	patSrch.selectPatient(VisitListData.PatientA, test);	
	  	encSelc.selectEncounter(VisitListData.FIN_A, test);
	    utm.verifyWithScreen(vstRev.vmBtn.getText(),"View More...", VisitListData.testName, imageName);	
	  	utm.verify(vstRev.pageHead.getText(), VisitListData.visitLstTxt);
	    utm.verify(vstRev.prevHead.getText(), VisitListData.prevTxt);
	    utm.verify(vstRev.futHead.getText(),  VisitListData.futTxt);
	  		
	  	
	    /* Step3 */
	  	test.log(LogStatus.INFO, "Step-3",  "Encounter-2 Details Page");
	  	imageName = "Screen_3_Encounter2";	
	  	vstRev.selectPrevious(EncNo.TWO);
	  	utm.verifyWithScreen(vstDet.titleTxt.getText(),"", VisitListData.testName, imageName);
	  	
	  	utm.verify(vstDet.verifyEncField(EncounterFields.ADMIT_DATE), "Admit Date");
	  	utm.verify(vstDet.verifyEncField(EncounterFields.ATTENDING_PHY), "Attending Phy");
	  	utm.verify(vstDet.verifyEncField(EncounterFields.DISCHARGE_DATE), "Discharge Date");
	  	utm.verify(vstDet.verifyEncField(EncounterFields.ENC_STATUS), "Encounter Status");
	  	utm.verify(vstDet.verifyEncField(EncounterFields.ENC_TYPE), "Encounter Type");
	  	utm.verify(vstDet.verifyEncField(EncounterFields.FIN), "Fin");
	  	utm.verify(vstDet.verifyEncField(EncounterFields.LOCATION), "Location");
	  	utm.verify(vstDet.verifyEncField(EncounterFields.REASON_FOR_VISIT), "Reason For Visit");
	  	utm.verify(vstDet.verifyEncField(EncounterFields.ROOM), "Room");
	  	utm.verify(vstDet.verifyEncField(EncounterFields.SERVICE), "Service");
	  	
	  	
	  	
	  	/* Step4 */
	  	test.log(LogStatus.INFO, "Step-4",  "Slide Over Gets Closed");
	  	vstDet.closeBtn.click();
	  	utm.verifyWithScreen(true, "Slide Over", VisitListData.testName, imageName);
	  	utm.verify(vstPg.sidePnl.isEnabled(), "Details Slide Panel");
	  	
	  	
	  	/* Step5 */
	  	test.log(LogStatus.INFO, "Step-5",  "View More... View");
	  	vstRev.vmBtn.click();
	  	utm.verify(vstPg.titleTxt.getText(), VisitListData.visitLstTxt);
	  	utm.verify(vstPg.prevBtn.getText(), VisitListData.prevTxt);
	  	utm.verify(vstPg.futBtn.getText(), VisitListData.futTxt);
	  	utm.verify(vstPg.prevBtn.isSelected(), "Previous Button Selected by Default");
	  	utm.verify(vstPg.bckBtn.isEnabled(), "Back Button");  	
	  	utm.verify(vstPg.prevHead.getText(), VisitListData.prevTxt);
	  	utm.verify(vstPg.futHead.getText(), VisitListData.futTxt);  	
	  	utm.verify(vstPg.verifyEncData(dataTable.getValue("Enc1_Time")),  " Step Description");		
	  	
	  	/* Step6 */
	  	test.log(LogStatus.INFO, "Step-6",  "Close Details Page");
	  	vstPg.selectEncWith("Preadmit");
	  	utm.verify(vstPg.sidePnl.isEnabled(), "Details Slide Panel");
	  	
	  	
	  	/* Step7 */
	  	test.log(LogStatus.INFO, "Step-7",  "Close Details Page");
	  	vstDet.closeBtn.click();
	  	utm.verify(vstPg.sidePnl.isEnabled(), "Details Slide Panel");
	  	
	  	/* Step8 */
	  	Thread.sleep(5000);
	  	test.log(LogStatus.INFO, "Step-8",  "Close Details Page");
	  	vstPg.futBtn.click();
		utm.verify(vstPg.futHead.getText(), VisitListData.prevTxt);
	  	
		/* Step9 */
	  	test.log(LogStatus.INFO, "Step-9",  "Close Details Page");
	  	vstPg.selectEncWith("Recurring");
	  	utm.verify(vstPg.sidePnl.isEnabled(), "Details Slide Panel");
	  	
	  	
	  	Thread.sleep(5000);
	  	
	  	/* Step10 */
	  	test.log(LogStatus.INFO, "Step-10",  "Close Details Page");
	  	vstDet.closeBtn.click();
	  	utm.verify(vstPg.sidePnl.isEnabled(), "Details Slide Panel");
	  	
	  	
	  	/* Step11 */
	  	test.log(LogStatus.INFO, "Step-11",  "Verify No Previous and Future Visits for Patient");
	  	imageName = "NoFutPrev";
	  	driver.get(baseUrl);
	  	utm.enterText(patSrch.PatientSearchTextBox,"Kheang, NoEncntr2", test);
	  	patSrch.selectPatient(dataTable.getValue(VisitListData.PatientB), test);	
	  	encSelc.selectEncounter(dataTable.getValue(VisitListData.FIN_B), test);
	  	estPg.select(dataTable.getValue("RelationType"));
	  	estPg.contBtn.click();
	  	utm.verifyWithScreen(vstRev.noFutTxt.getText(),VisitListData.noFutTxt, VisitListData.testName, imageName);
	  	utm.verify(vstRev.noPrevTxt.getText(), VisitListData.noPrevTxt);  	
		
	}

}
