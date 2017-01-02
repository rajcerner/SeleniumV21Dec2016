package com.cerner.pctorion.platform;


import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.ElementNotVisibleException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;


public class PatientSearchPage {  

	
	  public WebDriver driver;

      
      //Initialize page driver
	  public PatientSearchPage(WebDriver driver) {
		  this.driver = driver;
		  PageFactory.initElements(this.driver, this);
  	  }
	
	  //Locate the 'patient search text box'
      @FindBy(id="ion-patient-search-text-box")
	  public  WebElement PatientSearchTextBox;

      //Locate the "x" button
      @FindBy(css="button.icon-dismiss")
      public WebElement XButton;
	  
	  //Method to search for patient
	  public PatientSearchPage enterPatientsearchString(String searchstring, ExtentTest test) throws InterruptedException {
	    try{
		  PatientSearchTextBox.clear();
		  PatientSearchTextBox.sendKeys(searchstring);
		  Thread.sleep(5000);
	    }
	    catch(NoSuchElementException e) {
	    	test.log(LogStatus.FATAL, "Unable to find patient search box");
	    }
		return this;
	  }
	  

	  //Used to validate text in Patient Search box
	  public String patientSearchText() {
		  return PatientSearchTextBox.getText();
	  }
	  	  
	  
	  //Clears the patient search text area
	  public PatientSearchPage clearTestArea() {
		  PatientSearchTextBox.clear();
		  return this;
	  }
		  
	  //Method to click on the "x" button
	  public void clickXButton(ExtentTest test)  {
		  try{
			  XButton.click();
		  }
		  catch(ElementNotVisibleException e) {
		  		test.log(LogStatus.WARNING, "X button does not exist or no text entered into search area") ;
		  } 
	  }
		   	  
	  //Find name in initial list of patient search results.
	  public Boolean findPatient(String expectedFullName) {
		  List<WebElement> allPatientElements = driver.findElements(By.cssSelector(".ion-patient-search-result-detail > h5")); 
			Boolean nameFound = false;
	      for (WebElement element: allPatientElements) {
	        if (element.getText().equals(expectedFullName)) { 
	        	nameFound = true ;
	        }
	      }
		return nameFound;
	  }
	  
	  //Click_Select patient 
	  public void selectPatient(String selectName, ExtentTest test) throws InterruptedException {
		  
		List<WebElement> allPatientElements = driver.findElements(By.cssSelector(".ion-patient-search-result-detail > h5")); 
		Boolean nameFound = false;
	      for (WebElement element: allPatientElements) {
	        if (element.getText().equals(selectName)) {
	        	element.click();
				Thread.sleep(5000);
	        	nameFound = true ;
	        	break ;
	        }
	      }
	      if(nameFound != true) {
	    	  test.log(LogStatus.WARNING, "Unable to find or select patient " + selectName);
	      }
	  }
	  
  }