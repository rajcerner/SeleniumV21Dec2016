/*
https://jazz.cerner.com:9443/jts/dashboards/310Test Name: ORN_VR_PatientSearch
Purpose: Verify the Patient Search and Encounter View components in Orion Test Harness
Requirements: 807349, 753169, 753186, 753187
Change Control:
JW027642 Nov,11th 2016 Initial Creation
*/	
package com.cerner.pctorion.platformTests;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.openqa.selenium.support.PageFactory;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import java.io.IOException;


import com.cerner.pctorion.platform.*;
import com.cerner.pctorion.utility.DataTable;
import com.cerner.pctorion.utility.UtilityMethods;
import com.cerner.pctorion.utility.Settings;

public class ORN_VR_PatientSearch extends Settings {
	
	@Test
	@SuppressWarnings("unused")
	public void VRPatientSearch() throws InterruptedException, IOException
	  {

      //Initialize web elements from the pages
		LandingPage lnp = PageFactory.initElements(driver, LandingPage.class); 
		LoginPage lgp = PageFactory.initElements(driver, LoginPage.class);     
		PatientSearchPage ps  = PageFactory.initElements(driver, PatientSearchPage.class); 
		EncounterViewPage ev  = PageFactory.initElements(driver, EncounterViewPage.class);
		UtilityMethods utilitymethods = new UtilityMethods();
	

	  //Input xlxs file and choose worksheet and test to use for variable data
		String testName = "ORN_VR_PatientSearch";
		String sheet = "PatientSearch";
		DataTable datatable = new DataTable(sheet,testName);
		
	  //Declare variables
	  	String baseUrl = datatable.getValue("URL");
	  	String username = datatable.getValue("Username");
	  	String password = datatable.getValue("Password");
		String systemDate= UtilityMethods.systemDate();

	  //Use Extent Reports
		report = UtilityMethods.Instance(testName,browser) ;
		test = report.startTest(testName);
	    String imageName = ""; //For Screenshots
		
	  //Step 1	
		  //Open Test Harness (Patient Search) 
		  	driver.get(baseUrl);	
			//lnp.clickLoginButton();
			utilitymethods.clickButton(lnp.Login,test);
		  	lgp.enterUsernamePassword(username, password);
		  	//lgp.clickLoginButton();	
		  	utilitymethods.clickButton(lgp.Login,test);

		
	   //Step 2 -- Testing X button
		  	String searchstring = "abc123";
		  	//ps.enterPatientsearchString(searchstring, test); , ps.patientSearchText().contains(searchstring)
		  	utilitymethods.enterText(ps.PatientSearchTextBox, searchstring, test);
		  	//ps.clickXButton(test);
		  	System.out.println("asdf");
		  	utilitymethods.clickButton(ps.XButton,test);
		  	
		  	
			if(ps.patientSearchText().contains(searchstring)) {
				test.log(LogStatus.FAIL, "X button not clearing search as expect") ;
			}
			else {
				test.log(LogStatus.PASS, "X button clearing search as expected") ;
			}


		//Step 3 -- Search by Name 	
			searchstring = datatable.getValue("PatientSearchString1");
			String searchType = datatable.getValue("SearchStringType1");
			String expectedFullName = datatable.getValue("ExpectedFullName1");
			
			//ps.enterPatientsearchString(searchstring, test);
			utilitymethods.enterText(ps.PatientSearchTextBox, searchstring, test);
			imageName = "SearchResultbyName";
			UtilityMethods.captureScreenshot(driver, testName, imageName, browser);
		
			Boolean nameFound = ps.findPatient(expectedFullName);
			validateName(nameFound,searchType,searchstring,expectedFullName,imageName,test);
		
		//Step 4 -- Search by FIN 	
			searchstring = datatable.getValue("PatientSearchString2");
			searchType = datatable.getValue("SearchStringType2");
			expectedFullName = datatable.getValue("ExpectedFullName2");
			
			//ps.enterPatientsearchString(searchstring, test);
			utilitymethods.enterText(ps.PatientSearchTextBox, searchstring, test);
			imageName = "SearchResultbyFIN";
			UtilityMethods.captureScreenshot(driver, testName, imageName, browser);
		
			nameFound = ps.findPatient(expectedFullName);
			validateName(nameFound,searchType,searchstring,expectedFullName,imageName,test);

		//Step 5 -- Search by MRN 	
			searchstring = datatable.getValue("PatientSearchString3");
			searchType = datatable.getValue("SearchStringType3");
			expectedFullName = datatable.getValue("ExpectedFullName3");
			
			//ps.enterPatientsearchString(searchstring, test);
			utilitymethods.enterText(ps.PatientSearchTextBox, searchstring, test);
			imageName = "SearchResultbyMRN";
			UtilityMethods.captureScreenshot(driver, testName, imageName, browser);
		
			nameFound = ps.findPatient(expectedFullName);
			validateName(nameFound,searchType,searchstring,expectedFullName,imageName,test);
					  
		//Step 6 -- Select last patient searched for - validate encounter using name in demographics bar 	   
			ps.selectPatient(expectedFullName, test);
			String demoName = ev.getDemoBarName(test);

			
			
			try {	
				Assert.assertEquals(demoName,expectedFullName) ;
				test.log(LogStatus.PASS, demoName + " was found in encounter view demographics bar");
			}
			catch (AssertionError e) {
				test.log(LogStatus.FAIL, "Incorrect Demographics found on Encounter page.  Name expected was " + expectedFullName + " name found is " + demoName);
			}

		//Select to view all encounters
			ev.selectAll(test);
			
		//Step 7 -- Validate Encounter found
			imageName = "EncounterResult";
			UtilityMethods.captureScreenshot(driver, testName, imageName, browser);
			String expectedEncounter = datatable.getValue("ExpectedEncounterFIN3");
			
			Boolean encounterFound = ev.findEncounter(expectedEncounter);
			
			
			if(encounterFound != true) {
				test.log(LogStatus.FAIL, "Encounter " + expectedEncounter + " not found for patient " + expectedFullName + test.addScreenCapture(imageName+".png"));
			}
			else {
				test.log(LogStatus.PASS, "Encounter " + expectedEncounter + " found for patient " + expectedFullName + test.addScreenCapture(imageName+".png"));
			}

		//Step 8 - Navigate back to patient search
			ev.selectBackButton(test);

			
		//Step 9 - Search for name to return a lot of results -- testing scrolling not working currently
		  	searchstring = "Smith";
			imageName = "ScrollBar";
			//ps.enterPatientsearchString(searchstring,test);
			utilitymethods.enterText(ps.PatientSearchTextBox, searchstring, test);
			UtilityMethods.captureScreenshot(driver, testName, imageName,  browser);
			Boolean vertScrollBarExist = UtilityMethods.verticalScrollBarExist(driver);
			

			
			
			if(vertScrollBarExist != true) {
				test.log(LogStatus.FAIL, "No vertical scroll bar with searchstring of " + searchstring + test.addScreenCapture(imageName+".png")) ;		
			}
			else {
				test.log(LogStatus.PASS, "Vertical scroll bar exist as expected for searchstring of " + searchstring + test.addScreenCapture(imageName+".png")) ;	
			}
			
	    }

	
		//Common log message for validating patient search used in Steps 3, 4, and 5
	  		private void validateName(Boolean nameFound,String searchType,String searchstring,String expectedFullName,String imageName, ExtentTest test) {
	  			if(nameFound != true) {
					test.log(LogStatus.FAIL, searchType + " search of " + searchstring + " did not return expect Patient name of " + expectedFullName + test.addScreenCapture(imageName+".png"));
				}
				else {
					test.log(LogStatus.PASS, searchType + " search of " + searchstring + " returned a Patient name of " + expectedFullName + " as expected" + test.addScreenCapture(imageName+".png"));
				}
		
	}

}
