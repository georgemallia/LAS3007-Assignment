package PageFunctions;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import Utilities.CommonUtils;
import Utilities.PropertyFileReader;

public class TVTimeWatchlist 
{
	protected WebDriver driver;
	PropertyFileReader propFileReader;
	CommonUtils utils;
	
	@FindBy(xpath="//section[@id='to-watch']/ul/li")
	public WebElement addedShow;
	
	@FindBy(xpath="//span[contains(.,'Watchlist')]")
	private WebElement watchlistBtn;
	
	@FindBy(xpath="//*[@id=\"to-watch\"]/ul")
	private WebElement toWatchList;
	
	public TVTimeWatchlist(WebDriver driver) 
	{
		this.driver = driver;
		PageFactory.initElements(driver, this);
		propFileReader = new PropertyFileReader();
		utils = new CommonUtils();
	}

	public boolean visitWatchListPage(String showToFind)
	{
		boolean found = false;
		watchlistBtn.click();
		
		utils.waitForPageToSettleByCSS("#to-watch", driver);
		
		
		List<WebElement> showList = driver.findElements(By.xpath("//*[@id=\\\"to-watch\\\"]/ul")); 

		for (WebElement show: showList) 
		{
		      System.out.println(show.getText());
		      if(show.getText().toLowerCase().trim() == showToFind.toLowerCase().trim())
		      {
		    	  found = true;
		    	  return found;
		      }
		}
		
		return found;
	}
	
	
	
}
