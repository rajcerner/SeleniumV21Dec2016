package com.cerner.pctorion.chartTests;


import java.util.Set;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
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
import com.cerner.pctorion.utility.UtilityMethods;
import com.relevantcodes.extentreports.ExtentTest;


public class ORN_VR_VISITLIST{




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
		VisitListReviewPage obj = PageFactory.initElements(driver,VisitListReviewPage.class );
		VisitListDetailsPage vd = PageFactory.initElements(driver,VisitListDetailsPage.class);



		lnp.clickLoginButton();
		lgp.enterUsernamePassword("jw027642", "asdf");
		utilitymethods.clickOnAnyButton(lgp.Login, test);
		utilitymethods.enterDataInAnyField(ps.PatientSearchTextBox,"DODDS, BRIAN", test);
		ps.selectPatient("DODDS, BRIAN", test);
		ev.selectEncounter("1234567", test);
		//obj.selectFuture(Encounter.ONE);
		obj.selectPrevious(Encounter.THREE);
			
		//driver.close();
		//driver.quit();
	}



}
