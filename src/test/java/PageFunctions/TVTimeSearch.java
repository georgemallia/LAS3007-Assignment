package PageFunctions;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

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
	private WebElement showBtn;
	
	@FindBy(xpath="//h2/a")
	private WebElement displayTitleTxt;
	
	@FindBy(css=".follow-btn")
	private WebElement addShowBtn;
	
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
	}

	public int getNumberOfResults()
	{	
		int results = driver.findElements(By.xpath("//section[@id='shows-results']/ul")).size();
		
		return results;
	}
	
	public void addShow(String searchInput)
	{			
		showBtn.click();
		utils.waitForPageToSettleByCSS(".info-zone", driver);
		addShowBtn.click();
	}
	
	public String checkIfAdded(String searchInput)
	{
		WebElement showList = driver.findElement(By.xpath("//*[@id=\"shows-results\"]/ul")); 
		List<WebElement> li_All = showList.findElements(By.tagName("li"));
		
		for(int i = 1; i <= li_All.size(); i++)
	    {
	    	try 
	    	{
	    		if(driver.findElement(By.xpath("/html/body/div[4]/div[3]/div/div[1]/div/section[1]/ul/li["+ i +"]/div[3]/h2/a")).getText().toLowerCase().trim() == searchInput)
	    		{
	    			WebElement addedBtn = driver.findElement(By.xpath("/html/body/div[4]/div[3]/div/div[1]/div/section[1]/ul/li["+ i +"]/div[1]/a"));
	    			System.out.print(addedBtn.getClass());
	    			
	    			if(addedBtn.getClass().equals("follow-btn followed"))
	    			{
	    				return "Show Already Added";
	    			}
	    			
	    		}

			} 
	    	catch (NoSuchElementException e) 
	    	{
	    		continue;
			}
	    }
		
		
		return "Show Not Added";
		
		//button inidcating that it is followed
//		"/html/body/div[4]/div[3]/div/div[1]/div/section[1]/ul/li["+ i +"]/div[1]/a"
		
		//show name
//     "/html/body/div[4]/div[3]/div/div[1]/div/section[1]/ul/li["+ i +"]/div[3]/h2/a"
		
	}
	
	
}
