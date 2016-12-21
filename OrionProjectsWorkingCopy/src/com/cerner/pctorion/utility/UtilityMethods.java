package com.cerner.pctorion.utility;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;



import org.apache.commons.io.FileUtils;
import org.omg.CORBA.OBJECT_NOT_EXIST;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class UtilityMethods extends Settings 
{

	public static Boolean verticalScrollBarExist(WebDriver driver) throws InterruptedException //Verifies as vertical scroll bar exist if you have a long list to scroll through.
	{

		Boolean VertScrollExist = (Boolean) ((JavascriptExecutor)driver).executeScript("return document.documentElement.scrollHeight>document.documentElement.clientHeight;");
		System.out.println(VertScrollExist);	
		((JavascriptExecutor)driver).executeScript("scroll(0,400)");
		Thread.sleep(5000);
		return VertScrollExist;
	}

	public static String systemDate()
	{
		Date date = new Date() ;
		SimpleDateFormat dateFormat = new SimpleDateFormat("MMddyyyy hhmm");
		String systemDate= dateFormat.format(date);
		return systemDate;
	}

	public static void captureScreenshot(WebDriver driver, String testName,  String imageName, String browser)
	{
		String imagePath = projectFolderPath+File.separator+"Reports"+File.separator+testName+" "+browser+File.separator+imageName ;
		TakesScreenshot ts=(TakesScreenshot)driver;
		File oSS = ts.getScreenshotAs(OutputType.FILE);
		File oDest = new File(imagePath+".png");

		try{
			FileUtils.copyFile(oSS, oDest);
		}
		catch (IOException e){
			System.out.println("Exception while taking screenshot "+e.getMessage());
		}	
	}

	public static ExtentReports Instance(String testName, String browser)
	{
		String reportPath = projectFolderPath+File.separator+"Reports"+File.separator+testName+" "+browser+File.separator+testName+".html";
		ExtentReports report = new ExtentReports(reportPath, true) ;
		return report;

	}


	/* 
 	Deciding not to purge at this time - goes with below method
	public void cleanup (){
		String CleandirecPath=projectFolderPath+"\\Reports\\";
		cleanDirectory(CleandirecPath);
	}

	public static void cleanDirectory(String cleandirecPath) 
	{
		File file = new File(cleandirecPath);
		File[] files = file.listFiles(); 
		for (File f:files) {
			if (f.isFile() && f.exists()) 
			{ 
				f.delete();
				System.out.println("successfully deleted");
			}else{
				System.out.println("cant delete a file due to open or error");
			} 
		} 
	}	
	 */

	/**
	 * Mehtod to click on any clickable item
	 * @param driver
	 * @param Case
	 * @param byValue
	 * @author jk048034
	 */
	//public void clickOnAnyButton(String Case, WebElement element,String byValue){
	public void clickOnAnyButton(WebElement element,ExtentTest test){

		/*try{
			switch(Case){
			case "id": 
				driver.findElement(By.id(byValue)).click();
				break;

			case "cssSelector": 	
				driver.findElement(By.cssSelector(byValue)).click();
				break;

			case "className": 	
				driver.findElement(By.className(byValue)).click();
				break;

			case "linkText": 	
				driver.findElement(By.linkText(byValue)).click();
				break;

			case "name": 	
				driver.findElement(By.name(byValue)).click();
				break;

			case "partialLinkText": 	
				driver.findElement(By.partialLinkText(byValue)).click();
				break;

			case "tagName": 	
				driver.findElement(By.tagName(byValue)).click();
				break;

			case "xpath": 	
				driver.findElement(By.xpath(byValue)).click();
				break;

			default :
				throw new OBJECT_NOT_EXIST();

			}
		}catch (Exception e) {
			System.out.println(e);
			// TODO: handle exception
		}*/


		/*
			try{
				switch(Case){
				case "id": 
					element.click();
					break;

				case "cssSelector": 	
					element.click();
					break;

				case "className": 	
					element.click();
					break;

				case "linkText": 	
					element.click();
					break;

				case "name": 	
					element.click();
					break;

				case "partialLinkText": 	
					element.click();
					break;

				case "tagName": 	
					element.click();
					break;

				case "xpath": 	
					element.click();
					break;

				default :
					throw new OBJECT_NOT_EXIST();

				}
			}catch (Exception e) {
				System.out.println(e);
				// TODO: handle exception
			}*/

		try{
			element.click();
		}
		catch(NoSuchElementException e){
			test.log(LogStatus.FATAL, "not able to click on button");
			throw new OBJECT_NOT_EXIST();

		}
	}

	/**
	 * Mehtod to enter data in any text field
	 * @param driver
	 * @param Case
	 * @param byValue
	 * @param text
	 * @author jk048034
	 * @throws InterruptedException 
	 */
	//public void enterDataInAnyField(String Case, WebElement element,String text){
	public void enterDataInAnyField(WebElement element,String text,ExtentTest test) throws InterruptedException{

		
		/*try{
			switch(Case){
			case "id": 
				element.sendKeys(text);
				break;

			case "cssSelector": 	
				element.sendKeys(text);
				break;

			case "className": 	
				element.sendKeys(text);
				break;

			case "linkText": 	
				element.sendKeys(text);
				break;

			case "name": 	
				element.sendKeys(text);
				break;

			case "partialLinkText": 	
				element.sendKeys(text);
				break;

			case "tagName": 	
				element.sendKeys(text);
				break;

			case "xpath": 	
				element.sendKeys(text);
				break;

			default :
				throw new OBJECT_NOT_EXIST();

			}
		}catch (Exception e) {
			System.out.println(e);
			// TODO: handle exception
		}*/
		
		try{
			System.out.println("element "+element);
			
			element.clear();
			element.sendKeys(text);
			 Thread.sleep(5000);
		}
		catch(NoSuchElementException e){
			test.log(LogStatus.FATAL, "Not able to Enter data in text field");
			throw new OBJECT_NOT_EXIST();

		}
	}
}