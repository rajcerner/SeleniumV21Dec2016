package com.cerner.pctorion.platform;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
 
public class LoginPage {   
      
	  //Locate the "Username" field
      @FindBy(id = "j_username")
	  public WebElement Username;
     
      //Locate the "Password" field
      @FindBy(id = "j_password")
      public WebElement Password;
     
      //Locate the "Log In" button
      @FindBy(id = "loginButton")
      public WebElement Login;
      
	  //Method to enter username and password in the corresponding test box
	  public LoginPage enterUsernamePassword(String username, String password) {
		Username.sendKeys(username);
		Password.sendKeys(password);
		return this;
	}
	
	  //Method to click on the "Log In" button
	  public void clickLoginButton() {
		Login.click();
	}

  }