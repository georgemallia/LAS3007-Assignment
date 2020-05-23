package PageFunctions;

import org.openqa.selenium.By;
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
	
	@FindBy(xpath="//div[2]/a/img")
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
		if(displayTitleTxt.getText().toLowerCase().trim() == searchInput.toLowerCase().trim())
		{
			showBtn.click();
			
			utils.waitForPageToSettleByCSS(".info-zone", driver);
			
			addShowBtn.click();
		}
		
	}
}
