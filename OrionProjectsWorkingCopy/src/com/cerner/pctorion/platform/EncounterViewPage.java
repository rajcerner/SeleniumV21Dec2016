package com.cerner.pctorion.platform;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class EncounterViewPage {  

	 public WebDriver driver;
	
    //Initialize page driver
	  public EncounterViewPage(WebDriver driver) {
		  this.driver = driver;
	  }
	  
      //Locate name in Demographics bar
      @FindBy(css=".demographics-row > h1")
	  WebElement DemographicsName;
      
     //Locate the "Active" button on the segmented control
     @FindBy(css="button.ion-encounters-active-btn")
     WebElement Active;
     
     //Locate the "Discharged" button on the segmented control
     @FindBy(css="button.ion-encounters-discharged-btn")
     WebElement Discharged;
     
     //Locate the "Future" button on the segmented control
     @FindBy(css="button.ion-encounters-future-btn")
     WebElement Future;
     
     //Locate the "All" button on the segmented control
     @FindBy(css="button.ion-encounters-all-btn")
     WebElement All;
     
     //Locate the back arrow (<--) button
     @FindBy(css="button.icon-arrow-left")
     WebElement BackButton;
     
     //Method to select "Active" button on the segmented control
     public void selectActive(ExtentTest test) throws InterruptedException {
    	 try{
    		 Active.click();
 			Thread.sleep(5000);
		}
		catch(NoSuchElementException e) {
			test.log(LogStatus.WARNING, "Option to show Active encounter failed or is not found");
		}
     }
     
     //Method to select "Discharged" button on the segmented control
     public void selectDischarged(ExtentTest test) throws InterruptedException {
    	 try{
    		 Discharged.click();
 			Thread.sleep(5000);
		}
		catch(NoSuchElementException e) {
			test.log(LogStatus.WARNING, "Option to show Discharged encounter failed or is not found");
		}
     }
     
     //Method to select "Future" button on the segmented control
     public void selectFuture(ExtentTest test) throws InterruptedException {
    	 try{
		      Future.click();
		      Thread.sleep(5000);
		}
		catch(NoSuchElementException e) {
			test.log(LogStatus.WARNING, "Option to show Future encounter failed or is not found");
		}
     }
     
     //Method to select "All" button on the segmented control
     public void selectAll(ExtentTest test) throws InterruptedException {
    	 try{
		      All.click();
		      Thread.sleep(5000);
		}
		catch(NoSuchElementException e) {
			test.log(LogStatus.WARNING, "Option to show All encounter failed or is not found");
		}
     }
     
     //Method to select the back arrow (<--) button
     public void selectBackButton(ExtentTest test) throws InterruptedException {
    	 try{
    		 BackButton.click();
		      Thread.sleep(5000);
		}
		catch(NoSuchElementException e) {
			test.log(LogStatus.WARNING, "Back button in encounter screen failed or is not found");
		}
     }
	 //Method to get name in Demographics bar
	  public String getDemoBarName(ExtentTest test) {
		  try{
			  return DemographicsName.getText(); 
		  }
		  catch(NoSuchElementException e) {
		    	test.log(LogStatus.WARNING, "Unable to locate demographics name on Encounter results page");	  
		  }
		return null;
	  }
 
	  
	  //Find encounter in list of encounter results
	  public Boolean findEncounter(String expectedEncounter) {
		  List<WebElement> allEncounterElements = driver.findElements(By.cssSelector(".ion-encounter-detail > h6")); 
			Boolean encounterFound = false;
	      for (WebElement element: allEncounterElements) {
	        if (element.getText().contains(expectedEncounter)) { 
	        	encounterFound = true ;
	        }
	      }
		return encounterFound;
	  }
	  
	  //Click_Select Encounter 
	  public void selectEncounter(String selectEncounter, ExtentTest test) throws InterruptedException {
		List<WebElement> allEncounterElements = driver.findElements(By.cssSelector(".ion-encounter-detail > h6")); 
		Boolean encounterFound = false;
	      for (WebElement element: allEncounterElements) {
	        if (element.getText().contains(selectEncounter)) {
	        	element.click();
				Thread.sleep(5000);
				encounterFound = true ;
	        	break ;
	        }
	      }
	      if(encounterFound != true) {
	    	  test.log(LogStatus.WARNING, "Unable to find or select encouner " + selectEncounter);
	      }
	  }

  }
