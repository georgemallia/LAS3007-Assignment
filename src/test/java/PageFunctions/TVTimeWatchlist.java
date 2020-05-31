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
		watchlistBtn.click();
		try
		{
			utils.waitForPageToSettleByCSS("#to-watch", driver);
		} 
		catch (Exception e) 
		{
			utils.waitForPageToSettleByXpath("//*[@id=\"container\"]/div[3]/div[3]/div/div[2]/div/div", driver);
			
			//WebElement noShowsBanner = driver.findElement(By.xpath("//*[@id=\"container\"]/div[3]/div[3]/div/div[2]/div/div/div/h2"));
		//	if(noShowsBanner.getText().equalsIgnoreCase("What shows are you watching?"))
			//{
			//	return "No shows found";
			//}
		}
		return "";
	}
	
	
	public List<String> getShowList()
	{
		List<String> myShows = new ArrayList<String>();
		
		//WebElement showList = driver.findElement(By.xpath("//*[@id=\"to-watch\"]/ul")); 
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
					System.out.println(show.getText().toLowerCase().trim());

					myShows.add(show.getText().toLowerCase().trim());
				} 
		    	catch (NoSuchElementException e) 
		    	{
		    		continue;
				}
		    }
		}
	   
	    return myShows;	
	}	
	
	//to confirm that the show is under watch next tab
	public String checkShowsUnderWatchNext(String showName)
	{
		WebElement showList = driver.findElement(By.xpath("/html/body/div[3]/div[3]/div/div[2]/div/div/section[1]/ul")); 
		List<WebElement> li_All = showList.findElements(By.tagName("li"));
		
		for(int i = 1; i <= li_All.size(); i++)
	    {
	    	try 
	    	{
				WebElement show = driver.findElement(By.xpath("/html/body/div[3]/div[3]/div/div[2]/div/div/section[1]/ul/li[" + i + "]/div[3]/a"));
				System.out.println(show.getText().toLowerCase().trim());
				
				String showCmp = show.getText().toLowerCase().trim();
				if(showCmp.equalsIgnoreCase(showName.trim()))
				{
					return "Show is under Watch Next";
				}
				
			} 
	    	catch (NoSuchElementException e) 
	    	{
	    		continue;
			}
	    }
		
		//default
		return "";
	}
	
}
