package PageFunctions;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import Utilities.PropertyFileReader;


public class TvTimeAccountPage
{
	protected WebDriver driver;
	PropertyFileReader propFileReader;
	
	
	@FindBy(css=".account span")
	private WebElement settingsBtn;
	
	private Select monthDropDown = new Select(driver.findElement(By.name("birth[month]")));
	private Select dayDropDown = new Select(driver.findElement(By.name("birth[day]")));
	private Select yearDropDown = new Select(driver.findElement(By.name("birth[year]")));

	@FindBy(css=".btn-tvst:nth-child(5)")
	private WebElement saveBtn;
	
	@FindBy(id="settings")
	private WebElement settingsPage;
	
	public TvTimeAccountPage(WebDriver driver) 
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
		
		saveBtn.click();
	}
	
	public void verifyDetails()
	{
		//refreshing page
		driver.navigate().refresh();
		
		WebElement monthOption = monthDropDown.getFirstSelectedOption();
		WebElement dayOption = dayDropDown.getFirstSelectedOption();
		WebElement yearOption = yearDropDown.getFirstSelectedOption();
		
	}

}
