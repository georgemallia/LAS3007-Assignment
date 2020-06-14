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
 
	public String visitWatchListPage()
	{
		System.out.println("Opening watchlist");
		watchlistBtn.click();
		try
		{
			utils.waitForPageToSettleByCSS("#to-watch", driver);
		} 
		catch (Exception e) 
		{
			utils.waitForPageToSettleByXpath("//*[@id=\"container\"]/div[3]/div[3]/div/div[2]/div/div", driver);
		}
		return "";
	}
	
	
	public List<String> getShowList()
	{
		System.out.println("Attempting to get Show List");
		List<String> myShows = new ArrayList<String>();
		
		List<WebElement> ui_All = driver.findElements(By.xpath("//*[@id=\"to-watch\"]/ul")); 
		
		for(int j = 0; j < ui_All.size(); j++)
		{
			List<WebElement> li_All = ui_All.get(j).findElements(By.tagName("li"));
		    System.out.println(li_All.size());
		    
		    for(int i = 1; i <= li_All.size(); i++)
		    {
		    	try 
		    	{
					WebElement show = driver.findElement(By.xpath("/html/body/div[3]/div[3]/div/div[2]/div/div/section[" + j+1 + "]/ul/li[" + i + "]/div[3]/a"));
					System.out.println("Show from Watchlist: " + show.getText().toLowerCase().trim());

					myShows.add(show.getText().toLowerCase().trim());
				} 
		    	catch (NoSuchElementException e) 
		    	{
		    		break;
				}
		    }
		}
		System.out.println("Returning Show List");
	    return myShows;	
	}	
	
	//to confirm that the show is under watch next tab
	public String checkShowsUnderWatchNext(String showName)
	{
		utils.waitForPageToSettleByXpath("/html/body/div[3]/div[3]/div/div[2]/div/div/section[1]/ul", driver);
		
		WebElement showList = driver.findElement(By.xpath("/html/body/div[3]/div[3]/div/div[2]/div/div/section[1]/ul")); 
		List<WebElement> li_All = showList.findElements(By.tagName("li"));
		
		for(int i = 1; i <= li_All.size(); i++)
	    {
	    	try 
	    	{
				WebElement show = driver.findElement(By.xpath("/html/body/div[3]/div[3]/div/div[2]/div/div/section[1]/ul/li[" + i + "]/div[3]/a"));
				System.out.println(show.getText().toLowerCase().trim());
				System.out.println(showName.toLowerCase().trim());
				
				String showCmp = show.getText().toLowerCase().trim();
				if(showCmp.equalsIgnoreCase(showName.trim()))
				{
					System.out.println("Returning show is under watch next");
					return "Show is under Watch Next";
				}
			} 
	    	catch (NoSuchElementException e) 
	    	{
	    		break;
			}
	    }
		//default
		return "";
	}
}
