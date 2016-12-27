package com.cerner.pctorion.chart;


import java.util.List;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

/*
 * @uthor: rv042687 / roshanv
 * 
 * */

public class VisitListReviewPage {

/*
	VisitListReviewPage(WebDriver driver)
	{
		String headerTxt = driver.findElement(By.cssSelector("div.terraVM-ContentPanel--header > header > h1")).getText();

		if(!headerTxt.equals("Visit List"))
			throw new IllegalStateException("This is not Visit List Page of logged in user, current page" +
					"is: " + driver.getCurrentUrl());

	}*/

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
	
	//Constant values to identify encounters on profile/review screen 
	public enum Encounter{ONE, TWO, THREE};

	//method to select previous encounters
	public void selectPrevious(Encounter encNo)
	{
		switch(encNo)
		{
		case ONE:	 prevRowBtn.get(2).click();    break; // Encounter 1
		case TWO:    prevRowBtn.get(1).click();    break; // Encounter 2
		case THREE:  prevRowBtn.get(0).click();	   break; // Encounter 3	
		}

	}

	//method to select future encounters
	public void selectFuture(Encounter encNo)
	{
		switch(encNo)
		{
		case ONE:    futRowBtn.get(0).click();   break; // Encounter 1
		case TWO:    futRowBtn.get(1).click();   break; // Encounter 2
		case THREE:	 futRowBtn.get(2).click();   break; // Encounter 3
		}

	}


}
