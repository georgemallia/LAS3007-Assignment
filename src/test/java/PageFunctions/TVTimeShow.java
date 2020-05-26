package PageFunctions;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import Utilities.CommonUtils;
import Utilities.PropertyFileReader;

public class TVTimeShow
{
	protected WebDriver driver;
	PropertyFileReader propFileReader;
	CommonUtils utils;
	
	@FindBy(css=".not-watched-label")
	public WebElement watchedBtn;
		
	
	public TVTimeShow(WebDriver driver)
	{
		this.driver = driver;
		PageFactory.initElements(driver, this);
		propFileReader = new PropertyFileReader();
		utils = new CommonUtils();
	}
	
	//This method opens the show's next episode to watch
	public void openShow(String showName)
	{
		WebElement showPoster;
		WebElement showList = driver.findElement(By.xpath("//*[@id=\"to-watch\"]/ul")); 
		List<WebElement> li_All = showList.findElements(By.tagName("li"));
	    System.out.println(li_All.size());
	    
	    for(int i = 1; i <= li_All.size(); i++)
	    {
	    	try 
	    	{
				WebElement show = driver.findElement(By.xpath("/html/body/div[3]/div[3]/div/div[2]/div/div/section/ul/li[" + i + "]/div[3]/a"));
				System.out.println("Element Text: " + show.getText().toLowerCase().trim());
				System.out.println("Search Text: " + showName.toLowerCase().trim());
				String showCmp = show.getText().toLowerCase().trim();
				if(showCmp.equalsIgnoreCase(showName))
				{
					showPoster = driver.findElement(By.xpath("/html/body/div[3]/div[3]/div/div[2]/div/div/section/ul/li[" + i + "]/div[2]"));
					showPoster.click();
					break;
				}
			} 
	    	catch (NoSuchElementException e) 
	    	{
	    		continue;
			}
	    }
	}
	
	public String clickWatched()
	{
		System.out.println("Watch BTN: "  + watchedBtn.getAttribute("class"));
		if(watchedBtn.getAttribute("class").equals("not-watched-label"))
		{
			watchedBtn.click();
			return "Show episode marked as watched";
		}
		else if(watchedBtn.getAttribute("class").equals("watched-label"))
		{
			return "Show episode already marked as watched";
		}
		else
		{
			//default
			return "";
		}
	}
	
	public String clickUnwatched()
	{
		if(watchedBtn.getAttribute("class").trim().equals("cta-btn watched-btn watched"))
		{
			watchedBtn.click();
			return "Show episode marked as unwatched";
		}
		else if(watchedBtn.getAttribute("class").trim().equals("cta-btn watched-btn"))
		{
			return "Show episode already marked as unwatched";
		}
		else
		{
			//default
			return "";
		}
	}
}
