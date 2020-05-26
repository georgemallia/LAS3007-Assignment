package PageFunctions;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class TVTimeHomePage
{
	protected WebDriver driver;
	
	@FindBy(xpath="//*[@id=\"home-link\"]/img")
	private WebElement homeBtn;
		
	@FindBy(xpath="//*[@id=\"container\"]/div[3]/div[2]/div/div/div[2]/section[4]/a")
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
		signOutBtn.click();
	}	
}
