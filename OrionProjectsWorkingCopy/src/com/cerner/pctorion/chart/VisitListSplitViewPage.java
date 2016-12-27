package com.cerner.pctorion.chart;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

/*
 * @uthor: rv042687 / roshanv
 * 
 * 
 * */
public class VisitListSplitViewPage {
	
	
	
	//previous encounters button
	@FindBy(how = How.CSS, using = "#ion-visit-list-previous-button")
	public WebElement prevBtn;
	
	//future encounter button
	@FindBy(how = How.CSS, using="ion-visit-list-future-button")
	public WebElement futBtn;
	
	//back button on action tool bar
	@FindBy(how = How.CSS, using ="div.terraVM-ActionToolbar-leftButtons > button")
	public WebElement bckBtn;
	
	//title for action tool nar
	@FindBy(how = How.CSS, using= "div.terraVM-ActionToolbar-title > h1")
	public WebElement titleTlBr;
	
	//encounter Row Buttons for both previous & future encounters
	@FindBy(how = How.CSS, using = "div.terraVM-CompactCard-text")
	public List<WebElement> vistLstRowsBtn;
	


	
	//method to select visit list row based on encounter reason
	public void selectEncWith(String encReason)
	{
		for(WebElement encRow: vistLstRowsBtn)
		{
			if(encRow.getText().equals(encReason) )
				encRow.click();
		}
	}
	
}
