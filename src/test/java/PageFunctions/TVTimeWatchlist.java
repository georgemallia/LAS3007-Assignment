package PageFunctions;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
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

	public List<String> visitWatchListPage(String showToFind)
	{
		List<String> myShows = new ArrayList<String>();
		watchlistBtn.click();
		
		utils.waitForPageToSettleByCSS("#to-watch", driver);
		
		WebElement showList = driver.findElement(By.xpath("//*[@id=\"to-watch\"]/ul")); 
		List<WebElement> li_All = showList.findElements(By.tagName("li"));
	    System.out.println(li_All.size());
	    
	    for(int i = 1; i <= li_All.size(); i++)
	    {
	    	try 
	    	{
				WebElement show = driver.findElement(By.xpath("/html/body/div[3]/div[3]/div/div[2]/div/div/section/ul/li[" + i + "]/div[3]/a"));
				System.out.println(show.getText().toLowerCase().trim());

				myShows.add(show.getText().toLowerCase().trim());
			} 
	    	catch (NoSuchElementException e) 
	    	{
	    		continue;
			}
	    }
	    
	    return myShows;
	    /*
		for (WebElement show: showList) 
		{
		      System.out.println(show.getText());
		      if(show.getText().toLowerCase().trim() == showToFind.toLowerCase().trim())
		      {
		    	  
		    	  found = true;
		    	  return found;
		      }
		}*/
		
	}
	
	
	
}
