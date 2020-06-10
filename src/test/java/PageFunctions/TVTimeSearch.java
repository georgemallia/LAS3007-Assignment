package PageFunctions;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import Utilities.CommonUtils;
import Utilities.PropertyFileReader;

public class TVTimeSearch
{
	protected WebDriver driver;
	
	PropertyFileReader propFileReader;
	CommonUtils utils;

	@FindBy(id="global-search-input")
	private WebElement searchTxtbx;
	
	@FindBy(xpath="//section[@id='shows-results']/h1")
	public WebElement resultsTitle;
	
	@FindBy(xpath="//div[@id='search-results-container']/div")
	public WebElement noresultsTab;
	
	@FindBy(xpath="//h2/a")
	private WebElement displayTitleTxt;
	
	@FindBy(css=".follow-btn")
	private WebElement addShowBtn;
	
	private WebElement showBtn;
	
	
	public TVTimeSearch(WebDriver driver) 
	{
		this.driver = driver;
		PageFactory.initElements(driver, this);
		propFileReader = new PropertyFileReader();
		utils = new CommonUtils();
		
	}
	
	public void searchItem(String searchInput)
	{
		searchTxtbx.sendKeys(searchInput);
		//searchTxtbx.sendKeys(Keys.ENTER);
		//driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	}

	public int getNumberOfResults()
	{	
		int results = driver.findElements(By.xpath("//section[@id='shows-results']/ul")).size();
		
		return results;
	}
	
	public void addShow(String searchInput)
	{			 
		WebElement showList = driver.findElement(By.xpath("//*[@id=\"shows-results\"]/ul")); 
		List<WebElement> li_All = showList.findElements(By.tagName("li"));
		System.out.println(li_All.size());
		for(int i = 1; i <= li_All.size(); i++)
	    {
	    	try 
	    	{
	    		System.out.println("List Element: " + driver.findElement(By.xpath("/html/body/div[3]/div[3]/div/div[1]/div/section[1]/ul/li["+ i +"]/div[3]/h2/a")).getText().toLowerCase().trim());
	    		System.out.println("Search TXT: " + searchInput.toLowerCase().trim());
	    		
	    		showBtn = driver.findElement(By.xpath("/html/body/div[3]/div[3]/div/div[1]/div/section[1]/ul/li["+ i +"]/div[3]/h2/a"));
	    		String strCmp = showBtn.getText().toLowerCase().trim();
	    			    		
	    		if(strCmp.equalsIgnoreCase(searchInput))
	    		{
	    			utils.waitForElementToBeClickableByXpath("/html/body/div[3]/div[3]/div/div[1]/div/section[1]/ul/li["+ i +"]/div[3]/h2/a", driver);
	    			showBtn.click();
	    			break;
	    		}
	    	}	    	
	    	catch (NoSuchElementException e) 
	    	{
	    		continue;
			}
	    }
		
		driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
		utils.waitForPageToSettleByCSS(".info-zone", driver);
		System.out.println("Attempting to click add btn");
		utils.waitForElementToBeClickableByCss(".follow-btn", driver);
		addShowBtn.click();
		System.out.println("Add btn clicked");
	}
	
	public String checkIfAdded(String searchInput)
	{
		WebElement showList = driver.findElement(By.xpath("//*[@id=\"shows-results\"]/ul")); 
		List<WebElement> li_All = showList.findElements(By.tagName("li"));
		
		System.out.println(li_All.size());
		for(int i = 1; i <= li_All.size(); i++)
	    {
	    	try 
	    	{
	    		System.out.println("List Element: " + driver.findElement(By.xpath("/html/body/div[3]/div[3]/div/div[1]/div/section[1]/ul/li["+ i +"]/div[3]/h2/a")).getText().toLowerCase().trim());
	    		System.out.println("Search TXT: " + searchInput.toLowerCase().trim());
	    		String strCmp = driver.findElement(By.xpath("/html/body/div[3]/div[3]/div/div[1]/div/section[1]/ul/li["+ i +"]/div[3]/h2/a")).getText().toLowerCase().trim();
	    		if(strCmp.equalsIgnoreCase(searchInput))
	    		{
	    			WebElement addedBtn = driver.findElement(By.xpath("/html/body/div[3]/div[3]/div/div[1]/div/section[1]/ul/li["+ i +"]/div[1]/a"));
	    			System.out.print(addedBtn.getClass());
	    			
	    			if(addedBtn.getAttribute("class").equals("follow-btn followed"))
	    			{
	    				return "Show Is Already Added";
	    			}
	    		}
			} 
	    	catch (NoSuchElementException e) 
	    	{
	    		continue;
			}
	    }

		return "Show Not Added";
	}
	
	
}
