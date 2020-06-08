package PageFunctions;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import Utilities.CommonUtils;
import Utilities.PropertyFileReader;

public class TVTimeShow
{
	protected WebDriver driver;
	PropertyFileReader propFileReader;
	CommonUtils utils;
	
	@FindBy(css=".not-watched-label")
	public WebElement watchedBtn;
	
	@FindBy(css=".watched-label")
	public WebElement unwatchBtn;
	
	@FindBy(css=".previous")
	private WebElement previousBtn;

	@FindBy(linkText="Mark season as watched")
	private WebElement watchSeasonBtn;
	
	@FindBy(xpath="(//a[contains(@href, '#')])[18]")
	//@FindBy(css=".watched")
	private WebElement unWatchSeasonBtn;
	
		
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
	
	
	public void openShowDescription(String showName)
	{
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
					show.click();
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
		System.out.println("Watch BTN: "  + unwatchBtn.getAttribute("class"));
		if(unwatchBtn.getAttribute("class").trim().equals("watched-label"))
		{
			unwatchBtn.click();
			return "Show episode marked as unwatched";
		}
		else if(unwatchBtn.getAttribute("class").trim().equals("not-watched-label"))
		{
			return "Show episode already marked as unwatched";
		}
		else
		{
			//default
			return "";
		}
	}
	
	public void getPreviousEpisode()
	{
		utils.waitForElementToBeClickableByCss(".previous", driver);
		previousBtn.click();
	}
	
	
	public void clickWatchSeasons()
	{
		watchSeasonBtn.click();
	}
	
	public void unWatchSeason()
	{
		utils.waitForElementToBeClickableByCss(".watched", driver);
		
		Actions actions = new Actions(driver);
		actions.moveToElement(unWatchSeasonBtn).click().perform();
		//unWatchSeasonBtn.click();
	}
	
	public String checkSeasonWatched()
	{
		WebElement showWatched = driver.findElement(By.cssSelector(".watched"));
		
		System.out.println(showWatched.getAttribute("class"));
		
		//watched
		if(showWatched.getAttribute("class").equals("season-watched-btn watched-btn btn  watched"))
		{
			return "Season has been marked as watched";
		}
		else
		{
			//default
			return "";
		}	
	}
	
	
	
	public String checkSeasonUnWatched()
	{
		System.out.println(watchSeasonBtn.getAttribute("class"));
		//unwatched
		if(watchSeasonBtn.getAttribute("class").equals("season-watched-btn watched-btn btn"))
		{
			return "Season is not watched";
		}
		//default
		else
		{
			return "";
		}
	}
	
	
	
	public String selectPreviousSeason()
	{
		WebElement currentSelectedSeason = driver.findElement(By.id("dSeasons"));
		currentSelectedSeason.click();
		
		WebElement seasonList = driver.findElement(By.cssSelector(".open > .dropdown-menu"));
		List<WebElement> li_All = seasonList.findElements(By.tagName("li"));
	    System.out.println(li_All.size());
		
	    /*
	    if(li_All.size() == 1)
	    {
	    	return "Show only contains 1 season";
	    }*/
	    
	    for(WebElement li : li_All)
	    {
	    	
	    	System.out.println("Current LI Text: " + li.getText());
	    	System.out.println("currentselectedSeason Text: " +  currentSelectedSeason.getText());
	    		    	
	    	if(li.getText().equals(currentSelectedSeason.getText()))
	    	{
	    		int pos = li_All.indexOf(li);
	    		
	    		if(pos != 0)
	    		{
	    			//currentSelectedSeason.click();
	    			li_All.get(pos-1).click();
	    			return "Previous Season Selected";
	    		}
	    		else
	    		{
	    			return "Could Not Select Previous Season. First Season Currently Selected";
	    		}
	    	}
	    }
		
		//default
	    return "";
		
		
		/*
		
		
		seasonDropDown = new Select(driver.findElement(By.id("dSeasons")));
		
		List<WebElement> seasons = seasonDropDown.getOptions();
		WebElement currentSelectedSeason = seasonDropDown.getFirstSelectedOption();
		
		int pos = seasons.lastIndexOf(currentSelectedSeason);
		
		if(pos == 0)
		{
			return "Could Not Select Previous Season. First Season Currently Selected";
		}
		else
		{
			WebElement previousSeason = seasons.get(pos-1);
			previousSeason.click();
			
		}*/
	}
	
}
