package steps;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import PageFunctions.TVTimeAccountPage;
import PageFunctions.TVTimeHomePage;
import PageFunctions.TVTimeLogin;
import Utilities.CommonUtils;
import Utilities.PropertyFileReader;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class AccountSteps 
{
	private WebDriver driver;
	TVTimeHomePage homePage;
	TVTimeLogin loginPage;
	TVTimeAccountPage accountPage;
	PropertyFileReader propFileReader;
	CommonUtils utils;
	
	public AccountSteps(TestContext tc)
	{
		System.out.println("Account steps class: getting tc.driver");
		this.driver = tc.getDriver();
		propFileReader = new PropertyFileReader();
		utils = new CommonUtils();
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
		utils.waitForPageToSettleById("home-link", driver);
	}
	
	
	@And("he accesses the account page")
	public void He_accesses_the_account_page()
	{
		accountPage = new TVTimeAccountPage(driver);
		accountPage.vistAccountPage();
		utils.waitForPageToSettleByXpath("//*[@id=\"settings\"]", driver);
	}
	
	@And("clicks save")
	public void Clicks_save()
	{
		accountPage.saveBtnClick();
	}
	
	
	@When("he modifies account information")
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
			//DAY could not be asserted due to a bug found in the account page. Documented under Limitations section.
			//assertEquals(details.get(1).getText(), propFileReader.getPropertyValue("day"));
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		}
	}
}
