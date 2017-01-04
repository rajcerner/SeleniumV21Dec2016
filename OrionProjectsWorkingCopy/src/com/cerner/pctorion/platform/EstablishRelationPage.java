package com.cerner.pctorion.platform;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

/*
 * @author: rv042687 / roshan
 */

public class EstablishRelationPage {
	
		@FindBy(how = How.CSS, using="li.ion-ppr-establish-relationship-list-item")
		public List<WebElement> relTypLst;
		
		@FindBy(how = How.CSS, using="button.btn.btn-primary.ion-ppr-continue-button")
		public WebElement contBtn;
		
		
		//@uthor : rv042687 / roshan
		public void select(String relationType)
		{
			for(WebElement rel : relTypLst)
			{
				if(rel.getText().equals(relationType))
					rel.click();
			}
			
		}

}
