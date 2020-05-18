package PageFunctions;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import Utilities.PropertyFileReader;

public class TVTimeSearch
{
	protected WebDriver driver;
	
	PropertyFileReader propFileReader;

	@FindBy(id="global-search-input")
	private WebElement searchTxtbx;
	
	@FindBy(xpath="//section[@id='shows-results']/h1")
	public WebElement resultsTitle;
	
	@FindBy(xpath="//div[@id='search-results-container']/div")
	public WebElement noresultsTab;
	
	public TVTimeSearch(WebDriver driver) 
	{
		this.driver = driver;
		PageFactory.initElements(driver, this);
		propFileReader = new PropertyFileReader();
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
}
