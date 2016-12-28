package com.cerner.pctorion.chartTests;


import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Test;

import com.cerner.pctorion.chart.VisitListDetailsPage;
import com.cerner.pctorion.chart.VisitListDetailsPage.EncounterFields;
import com.cerner.pctorion.chart.VisitListReviewPage;
import com.cerner.pctorion.chart.VisitListReviewPage.Encounter;
import com.cerner.pctorion.platform.EncounterViewPage;
import com.cerner.pctorion.platform.LandingPage;
import com.cerner.pctorion.platform.LoginPage;
import com.cerner.pctorion.platform.PatientSearchPage;
import com.cerner.pctorion.utility.DataTable;
import com.cerner.pctorion.utility.Settings;
import com.cerner.pctorion.utility.UtilityMethods;
import com.relevantcodes.extentreports.ExtentTest;


public class ORN_VR_VISITLIST extends Settings{


	@Test
	public void ornVrVisitList() throws InterruptedException
	{
		LandingPage lndPage = PageFactory.initElements(driver, LandingPage.class); 
		LoginPage logPage = PageFactory.initElements(driver, LoginPage.class);     
		PatientSearchPage patSrch  = PageFactory.initElements(driver, PatientSearchPage.class); 
		EncounterViewPage encSelc  = PageFactory.initElements(driver, EncounterViewPage.class);
		UtilityMethods  utm = new UtilityMethods();
		
		
		String testName = "ORN_VR_VISITLIST";
		String sheet = "chart";
		DataTable dataTable = new DataTable(testName, sheet);
		
		String baseUrl = dataTable.getValue("URL");
	  	String username = dataTable.getValue("Username");
	  	String password = dataTable.getValue("Password");
		String systemDate= UtilityMethods.systemDate();
		String patName = dataTable.getValue("Patient-A");
		
		report = UtilityMethods.Instance(testName,browser) ;
		ExtentTest test = report.startTest(testName);
	    String imageName = ""; //For Screenshots
	    
	    
	  	driver.get(baseUrl);
	  	
	  	
	  	utm.clickButton(lndPage.Login,test);
	  	logPage.enterUsernamePassword(username, password);
	  	utm.clickButton(logPage.Login,test);  	
	  	utm.enterText(patSrch.PatientSearchTextBox, "DODDS, BRAIN", test);
	  	patSrch.selectPatient("DODDS, BRAIN", test);
	  	
	  	
	  	encSelc.selectEncounter("1234567", test);
	  	
		
		
	}
	
	
	
	
	
	/*
	@Test
	public void test() throws InterruptedException
	{
		System.setProperty("webdriver.chrome.driver", "/Users/rv042687/Desktop/rv/browserDrivers/chromedriver");
		WebDriver driver = new  ChromeDriver();

		//System.setProperty("webdriver.gecko.driver", "/Users/rv042687/Desktop/rv/browserDrivers/geckodriver");
		//WebDriver driver = new  FirefoxDriver();
		driver.manage().window().maximize();

		Set<Cookie> allCookies = driver.manage().getCookies();
		for(Cookie cookie : allCookies)
		{
			driver.manage().addCookie(cookie);
		}
		driver.get("https://ion-visit-list.test.devcernerpowerchart.com/search/patients");

		ExtentTest test = null;
		LandingPage lnp = PageFactory.initElements(driver, LandingPage.class); 
		LoginPage lgp = PageFactory.initElements(driver, LoginPage.class);     
		PatientSearchPage ps  = PageFactory.initElements(driver, PatientSearchPage.class); 
		EncounterViewPage ev  = PageFactory.initElements(driver, EncounterViewPage.class);
		UtilityMethods utilitymethods = new UtilityMethods();
		VisitListReviewPage vr = PageFactory.initElements(driver,VisitListReviewPage.class );
		VisitListDetailsPage vd = PageFactory.initElements(driver,VisitListDetailsPage.class);



		lnp.clickLoginButton();
		lgp.enterUsernamePassword("jw027642", "asdf");
		utilitymethods.clickOnAnyButton(lgp.Login, test);
		utilitymethods.enterDataInAnyField(ps.PatientSearchTextBox,"DODDS, BRIAN", test);
		ps.selectPatient("DODDS, BRIAN", test);
		ev.selectEncounter("1234567", test);
		//obj.selectFuture(Encounter.ONE);
	//	vr.selectPrevious(Encounter.THREE);
		Boolean status = vr.verifyPrevEncRows("Recurring");
		utilitymethods.verify(false, "data", test);
		utilitymethods.verify(status, "data", test);
		
		
		//System.out.println("value = " + status);
			
		//driver.close();
		driver.quit();
	}
*/


}
