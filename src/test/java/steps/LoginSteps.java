package steps;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.RepeatedTest;

import java.io.IOException;

import org.openqa.selenium.WebDriver;

import PageFunctions.TVTimeHomePage;
import PageFunctions.TVTimeLogin;
import Utilities.CommonUtils;
import Utilities.PropertyFileReader;

public class LoginSteps
{
	private WebDriver driver;
	TVTimeHomePage homePage;
	TVTimeLogin loginPage;
	PropertyFileReader propFileReader;
	CommonUtils utils;
	
	public LoginSteps(TestContext tc)
	{
		System.out.println("Login steps class: getting tc.driver");
		driver = tc.getDriver();
		propFileReader = new PropertyFileReader();
		utils = new CommonUtils();
	}
		
	@Given("I try to sign in")
	public void I_try_to_sign_in()
	{
		loginPage = new TVTimeLogin(driver);		
		loginPage.visitPage();
	}
	
	@Given("I try to log in")
	public void I_try_to_log_in()
	{
		loginPage = new TVTimeLogin(driver);		
		loginPage.visitPage();
	}
	
	
	
	@When("I enter my login details")
	public void I_enter_my_login_details()
	{
		try
		{
			loginPage.loginProcess(propFileReader.getPropertyValue("username"), propFileReader.getPropertyValue("password"));
		} 
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}
	
	@When("I enter the correct credentials")
	public void I_enter_the_correct_credentials()
	{
		try
		{
			loginPage.signinProcess(propFileReader.getPropertyValue("username"), propFileReader.getPropertyValue("email"), propFileReader.getPropertyValue("password"));
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		}
	}
	
	
	@When("I enter missing login details")
	public void I_enter_missing_login_details()
	{
		String username = "";
		try
		{
			loginPage.loginProcess(username, propFileReader.getPropertyValue("password"));
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		}
	}
	
	@When("I enter Incorrect login details")
	public void I_enter_incorrect_login_details()
	{
		try
		{
			loginPage.loginProcess(propFileReader.getPropertyValue("username"), "Log_me_1n");
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		}
	}
	
	
	
	@And("click the sign in button")
	public void click_the_sign_in_button()
	{
		homePage = loginPage.signinBtnClick();
	}
	
	@RepeatedTest(3)
	@And("click the log in button")
	public void click_the_log_in_button()
	{
		homePage = loginPage.loginBtnClick();
	}
	
	@And("when I click the log out button")
	public void when_i_click_the_log_out_button()
	{
		homePage.logoutProcess();
	}
	
	
	
	@Then("I should be logged in")
	public void i_shoulld_be_logged_in() 
	{
		utils.waitForPageToSettleByXpath("//*[@id=\"container\"]/div[3]/div[2]/div", driver);
		assertTrue(homePage.isLoggedIn());
	}
	
	@Then("I should not be logged in")
	public void I_Should_not_be_logged_in()
	{
		assertFalse(homePage.isLoggedIn());
	}
	
	@RepeatedTest(3)
	@Then("I should be logged out")
	public void I_should_be_logged_out()
	{
		//waiting for signout page nav to load
		utils.waitForPageToSettleByXpath("//*[@id=\"gatsby-focus-wrapper\"]/div/nav/div[1]", driver);
		assertFalse(homePage.isLoggedIn());
	}
}
