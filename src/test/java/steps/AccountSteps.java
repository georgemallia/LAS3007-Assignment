package steps;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import PageFunctions.TVTimeAccountPage;
import PageFunctions.TVTimeHomePage;
import PageFunctions.TVTimeLogin;
import Utilities.PropertyFileReader;
import Utilities.WebDriverFactory;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class AccountSteps 
{
	private WebDriver driver = null;
	TVTimeHomePage homePage;
	TVTimeLogin loginPage;
	TVTimeAccountPage accountPage;
	PropertyFileReader propFileReader;
	
	@Before
	public void openBrowser() 
	{
		System.setProperty("browser", "firefox");	
		propFileReader = new PropertyFileReader();
		driver = WebDriverFactory.createWebDriver();
	}
	
	@After
	public void closeBrowser()
	{
		if (driver != null)
		{
			driver.close();
		}	
	}
	
	
	@Given("the user is logged in")
	public void The_user_is_logged_in()
	{
		loginPage = new TVTimeLogin(driver);		
		loginPage.visitPage();
		
		try
		{
			loginPage.loginProcess(propFileReader.getPropertyValue("username"), propFileReader.getPropertyValue("password"));
		} 
		catch (IOException e)
		{
			e.printStackTrace();
		}
		
		homePage = loginPage.loginBtnClick();
		homePage.waitForPageToSettle("//*[@id=\"container\"]/div[3]/div[2]/div");
	}
	
	
	@And("he accesses the account page")
	public void He_accesses_the_account_page()
	{
		accountPage = new TVTimeAccountPage(driver);
		accountPage.vistAccountPage();
		accountPage.waitForPageToSettle("//*[@id=\"settings\"]");
	}
	
	@And("clicks save")
	public void Clicks_save()
	{
		accountPage.saveBtnClick();
	}
	
	
	@When("he modfies account information")
	public void He_modifies_account_information()
	{
		try 
		{
			accountPage.amendDetails();
		} 
		catch (Exception e) 
		{
			
			e.printStackTrace();
		}
	}
	
	
	@Then("the changes should be saved")
	public void The_changes_should_be_saved()
	{
		accountPage = new TVTimeAccountPage(driver);
		List<WebElement> details = accountPage.verifyDetails();
	
		try
		{
			assertEquals(details.get(0).getText(), propFileReader.getPropertyValue("month"));
			assertEquals(details.get(2).getText(), propFileReader.getPropertyValue("year"));
			//assertEquals(details.get(1).getText(), propFileReader.getPropertyValue("day"));
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		}
		
	}
	
}
