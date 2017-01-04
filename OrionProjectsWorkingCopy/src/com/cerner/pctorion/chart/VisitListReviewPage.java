package com.cerner.pctorion.chart;


import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/*
 * @uthor: rv042687 / roshanv
 * 
 * */

public class VisitListReviewPage {

	//WebElement pageElement = (new WebDriverWait(driver, 10))
      //      .until(ExpectedConditions.presenceOfElementLocated(By.id("#ion-visit-l

	@FindBy(how = How.CSS, using=".terraVM-ContentPanel--header > header > h1")
	public WebElement pageHead;
	
	//previous header label
	@FindBy(how = How.CSS, using="#ion-visit-list-previous-header")
	public WebElement prevHead;

	//future header label
	@FindBy(how = How.CSS, using="#ion-visit-list-future-header")
	public WebElement futHead;

	//View More... button
	@FindBy (how = How.CSS, using="#ion-visit-list-view-more")
	public WebElement vmBtn;

	//previous encounters rows/cell buttons
	@FindBy (how = How.XPATH, using="//h1[contains(text(), 'Previous')]/following::li/div")
	public List<WebElement> prevRowBtn;

	//future encounter row/cell buttons
	@FindBy (how = How.XPATH, using="//h1[contains(text(), 'Previous')]/preceding::li[@class='terraVM-u-chevron']/div")
	public List<WebElement> futRowBtn;
	
	@FindBy (how = How.XPATH, using="//p[contains(text(), 'No future visits for this patient')]")
	public WebElement noFutTxt;
	
	@FindBy (how = How.XPATH, using="//p[contains(text(), 'No previous visits for this patient')]")
	public WebElement noPrevTxt;
	
	//Constant values to identify encounters on profile/review screen 
	public enum EncNo{ONE, TWO, THREE};

	//method to select previous encounters
	public void selectPrevious(EncNo encNo)
	{
		switch(encNo)
		{
		case ONE:	 prevRowBtn.get(2).click();    break; // Encounter 1
		case TWO:    prevRowBtn.get(1).click();    break; // Encounter 2
		case THREE:  prevRowBtn.get(0).click();	   break; // Encounter 3	
		}

	}

	//method to select future encounters
	public void selectFuture(EncNo encNo)
	{
		switch(encNo)
		{
		case ONE:    futRowBtn.get(2).click();   break; // Encounter 1
		case TWO:    futRowBtn.get(1).click();   break; // Encounter 2
		case THREE:	 futRowBtn.get(0).click();   break; // Encounter 3
		}

	}	
	
	//method to verify previous encounters data in row
	//@param:encData = actual data
	Boolean status = null;
	public Boolean verifyPrevEncRows(String rowData)
	{		
		for(WebElement prevRowData : prevRowBtn)
		{
			if(prevRowData.getText().equals(rowData));
			status = true;		
		}
		return status;
	}
	
	//method to verify previous encounters data in row
	//@param:encData = actual data
	public Boolean verifyFutEncRows(String rowData)
	{
		
		for(WebElement futRowData : futRowBtn)
		{
			if(futRowData.getText().equals(rowData));
			 status =   true;
		}
		return status;
	}
	
	
	


}
