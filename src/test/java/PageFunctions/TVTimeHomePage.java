package PageFunctions;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import Utilities.CommonUtils;

public class TVTimeHomePage
{
	protected WebDriver driver;
	
	CommonUtils utils = new CommonUtils();
	
	@FindBy(xpath="//*[@id=\"home-link\"]/img")
	private WebElement homeBtn;
		
	@FindBy(css=".signout-link")
	private WebElement signOutBtn;
	
	public TVTimeHomePage(WebDriver driver)
	{
		PageFactory.initElements(driver, this);
		this.driver = driver;
	}
				
	public boolean isLoggedIn()
	{
		try 
		{
			if(homeBtn.isDisplayed())
			{
				return true;
			} 
		}
		catch (Exception e)
		{ 
			System.out.println("TVTimeHomePage: homeBtn was not found, returning false");
			return false;
		}
		return false;
	}
		
	public void logoutProcess()
	{		
		WebElement element = driver.findElement(By.xpath("/html/body"));
		element.sendKeys(Keys.PAGE_DOWN);
		
		utils.waitForElementToBeClickableByCss(".signout-link", driver);
		
		signOutBtn.click();
	}	
}
