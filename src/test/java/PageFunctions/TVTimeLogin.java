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

public class TVTimeLogin 
{
	protected WebDriver driver;
	private static final String URL = "https://www.tvtime.com/";

	PropertyFileReader propFileReader;
	CommonUtils utils;
	
	//@FindBy(xpath="//*[@id=\"gatsby-focus-wrapper\"]/div/nav/div[3]/div[2]/button")
	@FindBy(css=".views__SquareButtonView-x7wsxy-0")
	private WebElement signinBtn;
	
	//@FindBy(xpath="//*[@id=\"gatsby-focus-wrapper\"]/div/nav/div[3]/div[1]/li/a")
	@FindBy(css=".views__HideOnMobileWrapper-sc-12vav2z-0 .views__NavLink-sc-1tygrp6-1")
	private WebElement loginBtn;
	
	@FindBy(name="username")
	private WebElement usernameFld;
	@FindBy(name="email")
	private WebElement emailFld;
	@FindBy(name="password")
	private WebElement passwordFld;

	//@FindBy(xpath="//*[@id=\"gatsby-focus-wrapper\"]/div/nav/div[3]/div[2]/div/div/div/form/div[3]/input")
	@FindBy(css=".views__SubmitButton-x7wsxy-7")
	private WebElement finalSigninBtn;
	
	//@FindBy(xpath="//*[@id=\"gatsby-focus-wrapper\"]/div/nav/div[3]/div[1]/div/div/div/form/div[3]/input")
	@FindBy(css=".views__SubmitButton-hnsgg9-7")
	private WebElement finalLoginBtn;
	

	
	public TVTimeLogin(WebDriver driver) 
	{
		this.driver = driver;
		PageFactory.initElements(driver, this);
		propFileReader = new PropertyFileReader();
		utils = new CommonUtils();
	}

	public void visitPage()
	{
		driver.get(URL);
	}

	
	public void loginProcess(String username, String password)
	{
		try 
		{
			utils.waitForElementToBeClickableByCss(".views__HideOnMobileWrapper-sc-12vav2z-0 .views__NavLink-sc-1tygrp6-1", driver);
			loginBtn.click();
			
	        //explicit Wait
			WebDriverWait wait = new WebDriverWait(driver,20);
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("username"))); 
			
			usernameFld.sendKeys(username);
			passwordFld.sendKeys(password);

		} 
		catch (Exception e) 
		{
			e.printStackTrace(); 
		}
	}
	
	
	public TVTimeHomePage loginBtnClick()
	{
		utils.waitForPageToSettleByCSS(".views__SubmitButton-hnsgg9-7", driver);
		utils.waitForElementToBeClickableByCss(".views__SubmitButton-hnsgg9-7", driver);
		finalLoginBtn.click();
		
		return new TVTimeHomePage(driver);
	}
	
	public TVTimeHomePage signinBtnClick()
	{
		utils.waitForPageToSettleByCSS(".views__SubmitButton-x7wsxy-7", driver);
		utils.waitForElementToBeClickableByCss(".views__SubmitButton-x7wsxy-7", driver);
		finalSigninBtn.click();
		
		return new TVTimeHomePage(driver);
	}
	
	public void signinProcess(String username, String email, String password)
	{
		try 
		{
			signinBtn.click();
			
	        //explicit Wait
			WebDriverWait wait = new WebDriverWait(driver,20);
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("username"))); 
			
			usernameFld.sendKeys(username);
			emailFld.sendKeys(email);
			passwordFld.sendKeys(password);
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
	}
	

}
