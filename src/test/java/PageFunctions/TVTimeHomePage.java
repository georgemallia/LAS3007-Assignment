package PageFunctions;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class TVTimeHomePage
{
	protected WebDriver driver;
	private static final String URL = "https://www.tripadvisor.com/";
	
	@FindBy(xpath="//*[@id=\"home-link\"]/img")
	private WebElement homeBtn;
		
	@FindBy(xpath="//*[@id=\"container\"]/div[3]/div[2]/div/div/div[2]/section[4]/a")
	private WebElement signOutBtn;
	
	public TVTimeHomePage(WebDriver driver)
	{
		this.driver = driver;
		PageFactory.initElements(driver, this);
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
		signOutBtn.click();
	}

	public void waitForPageToSettle(String ByLocator) 
	{   
		WebDriverWait wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(ByLocator)));
	}
	
	
}
