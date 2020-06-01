
/*
package TestRunners;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.io.IOException;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import PageFunctions.TVTimeHomePage;
import PageFunctions.TVTimeLogin;
import PageFunctions.TVTimeSearch;
import Utilities.PropertyFileReader;
import Utilities.WebDriverFactory;

@TestInstance(Lifecycle.PER_CLASS)
public class unitTestsRunner 
{
	private WebDriver driver;
	PropertyFileReader propFileReader;
	
	@FindBy(xpath="//*[@id=\"container\"]/div[3]/div[2]/div")
	public WebElement nav;
	
	//@FindBy(xpath="//*[@id=\"gatsby-focus-wrapper\"]/div/nav/div[3]/div[1]/div/div/div/form/div[2]/fieldset/div[2]/div[2]")
	@FindBy(css=".views__ErrorSpan-hnsgg9-6:nth-child(5)")
	public WebElement errorLogin;
	
	
	@BeforeAll
	public void setup() throws Exception
	{
		System.setProperty("browser", "firefox");	
	}
	
	@BeforeEach
	public void open() throws Exception
	{
		driver = WebDriverFactory.createWebDriver();
		propFileReader = new PropertyFileReader();
	}
	
	@AfterEach
	public void closeBrowser() throws Exception
	{
		if (driver != null)
			driver.close();
	}

	//Part 1: Login 
	@Test
	public void signInTest()
	{
		TVTimeLogin loginPage = new TVTimeLogin(driver);
		TVTimeHomePage homePage;
		
		System.out.println("Starting Signin Test");
		
		try 
		{
			loginPage.visitPage();
			loginPage.signinProcess(propFileReader.getPropertyValue("username"), propFileReader.getPropertyValue("email"), propFileReader.getPropertyValue("password"));
			homePage =loginPage.signinBtnClick();
			
			waitForPageToSettle("//*[@id=\"container\"]/div[3]/div[2]/div");
			
			System.out.println("homePage: " + homePage.isLoggedIn());
			assertTrue(homePage.isLoggedIn());
			
			System.out.println("Finishing Signin Test");
		}
		catch (IOException e) 
		{
			e.printStackTrace();
		}
	}

	
	@Test
	public void loginCorrectCredentialsTest()
	{
		TVTimeLogin loginPage = new TVTimeLogin(driver);
		TVTimeHomePage homePage;

		try 
		{
			System.out.println("Starting loginCorrectCredentials Test");

			loginPage.visitPage();
			loginPage.loginProcess(propFileReader.getPropertyValue("username"), propFileReader.getPropertyValue("password"));
			homePage = loginPage.loginBtnClick();
			
			waitForPageToSettle("//*[@id=\"container\"]/div[3]/div[2]/div");
			
			System.out.println("homePage: " + homePage.isLoggedIn());
			assertTrue(homePage.isLoggedIn());
			System.out.println("Finishing loginCorrectCredentials Test");
		}
		catch (IOException e) 
		{ 
			e.printStackTrace();
		}
	}
		
	@Test
	public void loginMissingCredentialsTest()
	{
		TVTimeLogin loginPage = new TVTimeLogin(driver);
		TVTimeHomePage homePage;
		String username = "";
		
		try 
		{
			System.out.println("Starting loginMissingCredentials Test");
			loginPage.visitPage();
			loginPage.loginProcess(username, propFileReader.getPropertyValue("password"));
			homePage = loginPage.loginBtnClick();
			
			System.out.println("homePage: " + homePage.isLoggedIn());
			assertFalse(homePage.isLoggedIn());
			System.out.println("Finishing loginMissingCredentials Test");
		}
		catch (IOException e) 
		{
			e.printStackTrace();
		}
	}
	
	@Test
	public void loginIncorrectCredentialsTest()
	{
		TVTimeLogin loginPage = new TVTimeLogin(driver);
		TVTimeHomePage homePage;
		
		try 
		{
			System.out.println("Startinging loginIncorrectCredentials Test");

			loginPage.visitPage();
			loginPage.loginProcess(propFileReader.getPropertyValue("username"), "Log_me_1n");
			homePage = loginPage.loginBtnClick();
			
			System.out.println("homePage: " + homePage.isLoggedIn());
			assertFalse(homePage.isLoggedIn());
			System.out.println("Finishing loginIncorrectCredentials Test");
		}
		catch (Exception e) 
		{ 
			e.printStackTrace();
		}
	}
	
	@Test
	public void LogoutTest()
	{
		TVTimeLogin loginPage = new TVTimeLogin(driver);
		TVTimeHomePage homePage;

		try 
		{
			System.out.println("Starting Logout Test");
			loginPage.visitPage();
			loginPage.loginProcess(propFileReader.getPropertyValue("username"), propFileReader.getPropertyValue("password"));
			homePage = loginPage.loginBtnClick();
			
			//wait for homepage nav to load
			waitForPageToSettle("//*[@id=\"container\"]/div[3]/div[2]/div");
			
			homePage.logoutProcess();
			
			//waiting for signout page nav to load
			waitForPageToSettle("//*[@id=\"gatsby-focus-wrapper\"]/div/nav/div[1]");
			
			System.out.println("homePage: " + homePage.isLoggedIn());
			assertFalse(homePage.isLoggedIn());
			System.out.println("Finishing Logout Test");
		}
		catch (IOException e) 
		{ 
			e.printStackTrace();
		}
	}
	
	
	//Part 2: Search
	@Test
	public void searchTest()
	{
		TVTimeLogin loginPage = new TVTimeLogin(driver);
		TVTimeSearch searchPage = new TVTimeSearch(driver);
		TVTimeHomePage homePage;

		try 
		{
			System.out.println("Starting Search Test");

			loginPage.visitPage();
			loginPage.loginProcess(propFileReader.getPropertyValue("username"), propFileReader.getPropertyValue("password"));
			homePage = loginPage.loginBtnClick();
			
			waitForPageToSettle("//*[@id=\"container\"]/div[3]/div[2]/div");
			
			searchPage.searchItem(propFileReader.getPropertyValue("searchInput"));
			
			waitForPageToSettle("//section[@id='shows-results']/h1");
			
			assertTrue(searchPage.resultsTitle.isDisplayed());
			assertTrue(searchPage.getNumberOfResults() >= 0);

			System.out.println("Finishing search Test");
		}
		catch (IOException e) 
		{ 
			e.printStackTrace();
		}
	}
	
	@Test
	public void advancedSearchTest()
	{
		TVTimeLogin loginPage = new TVTimeLogin(driver);
		TVTimeSearch searchPage = new TVTimeSearch(driver);
		TVTimeHomePage homePage;

		String search = "";
		
		try 
		{
			System.out.println("Starting Advanced Search Test");

			loginPage.visitPage();
			loginPage.loginProcess(propFileReader.getPropertyValue("username"), propFileReader.getPropertyValue("password"));
			homePage = loginPage.loginBtnClick();
			
			waitForPageToSettle("//*[@id=\"container\"]/div[3]/div[2]/div");
			
			search = propFileReader.getPropertyValue("searchInput");
			searchPage.searchItem(search.substring(0, search.length()/2));
			
			waitForPageToSettle("//section[@id='shows-results']/h1");
			
			assertTrue(searchPage.resultsTitle.isDisplayed());
			assertTrue(searchPage.getNumberOfResults() >= 0);

			System.out.println("Finishing Advanced Search Test");
		}
		catch (IOException e) 
		{ 
			e.printStackTrace();
		}
	}
	
	@Test
	public void searchIncorrectInputTest()
	{
		TVTimeLogin loginPage = new TVTimeLogin(driver);
		TVTimeSearch searchPage = new TVTimeSearch(driver);
		TVTimeHomePage homePage;

		try 
		{
			System.out.println("Starting Search Incorrect Input Test");

			loginPage.visitPage();
			loginPage.loginProcess(propFileReader.getPropertyValue("username"), propFileReader.getPropertyValue("password"));
			homePage = loginPage.loginBtnClick();
			
			waitForPageToSettle("//*[@id=\"container\"]/div[3]/div[2]/div");
			
			searchPage.searchItem("ab8444333441245678321");
			
			waitForPageToSettle("//div[@id='search-results-container']/div");
			
			assertTrue(searchPage.noresultsTab.isDisplayed());
			assertTrue(searchPage.getNumberOfResults() == 0);

			System.out.println("Finishing Search Incorrect Input Test");
		}
		catch (IOException e) 
		{ 
			e.printStackTrace();
		}
	}
	
	
	//General Methods
	protected void waitForPageToSettle(String ByLocator) 
	{   
		WebDriverWait wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(ByLocator)));
	}
		
	

}
*/