package Utilities;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CommonUtils 
{

	public CommonUtils() 
	{
		
	}
	
	public void waitForPageToSettleByCSS(String ByLocator, WebDriver driver) 
	{   
		WebDriverWait wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(ByLocator)));
	}
	
	public void waitForPageToSettleByXpath(String ByLocator, WebDriver driver) 
	{   
		WebDriverWait wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(ByLocator)));
	}
	
}
