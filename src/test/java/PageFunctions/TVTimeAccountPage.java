package PageFunctions;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import Utilities.PropertyFileReader;


public class TVTimeAccountPage
{
	protected WebDriver driver;
	PropertyFileReader propFileReader;
	
	private Select monthDropDown;
	private Select dayDropDown;
	private Select yearDropDown;
	
	@FindBy(css=".account span")
	private WebElement settingsBtn;
	
	@FindBy(css=".btn-tvst:nth-child(5)")
	private WebElement saveBtn;
	
	@FindBy(id="settings")
	private WebElement settingsPage;
	
	public TVTimeAccountPage(WebDriver driver) 
	{
		this.driver = driver;
		PageFactory.initElements(driver, this);
		propFileReader = new PropertyFileReader();
	}
	
	
	public void vistAccountPage()
	{
		settingsBtn.click();
	}
	
	public void amendDetails() throws Exception
	{
		monthDropDown = new Select(driver.findElement(By.name("birth[month]")));
		dayDropDown = new Select(driver.findElement(By.name("birth[day]")));
		yearDropDown = new Select(driver.findElement(By.name("birth[year]")));
		
		try
		{
			monthDropDown.selectByVisibleText(propFileReader.getPropertyValue("month"));
			dayDropDown.selectByVisibleText(propFileReader.getPropertyValue("day"));
			yearDropDown.selectByVisibleText(propFileReader.getPropertyValue("year"));
		} 
		catch (Exception e) 
		{
			System.out.println("ERROR: An error occured while amending the profile details");
			throw e;
		}
	}
	
	public void saveBtnClick()
	{
		saveBtn.click();
	}
	
	
	public List<WebElement> verifyDetails()
	{
		waitForPageToSettle("//*[@id=\"settings\"]");
		
		//re-initialising the Select to avoid stale exception
		monthDropDown = new Select(driver.findElement(By.name("birth[month]")));
		dayDropDown = new Select(driver.findElement(By.name("birth[day]")));
		yearDropDown = new Select(driver.findElement(By.name("birth[year]")));
		
		
		WebElement monthOption = monthDropDown.getFirstSelectedOption();
		WebElement dayOption = dayDropDown.getFirstSelectedOption();
		WebElement yearOption = yearDropDown.getFirstSelectedOption();
		
		List<WebElement> details = new ArrayList<WebElement>();
		details.add(monthOption);
		details.add(dayOption);
		details.add(yearOption);
		
		return details;
	}
	
	public void waitForPageToSettle(String ByLocator) 
	{   
		WebDriverWait wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(ByLocator)));
	}

}
