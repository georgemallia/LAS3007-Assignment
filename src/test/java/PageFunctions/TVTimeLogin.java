package PageFunctions;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.By.ByName;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import Utilities.PropertyFileReader;

public class TVTimeLogin 
{
	protected WebDriver driver;
	private static final String URL = "https://www.tvtime.com/";

	PropertyFileReader propFileReader;
				   
	@FindBy(xpath="//*[@id=\"gatsby-focus-wrapper\"]/div/nav/div[3]/div[2]/button")
	private WebElement signinBtn;
	
	@FindBy(xpath="//*[@id=\"gatsby-focus-wrapper\"]/div/nav/div[3]/div[1]/li/a")
	private WebElement loginBtn;
	
	@FindBy(name="username")
	private WebElement usernameFld;
	@FindBy(name="email")
	private WebElement emailFld;
	@FindBy(name="password")
	private WebElement passwordFld;

	@FindBy(xpath="//*[@id=\"gatsby-focus-wrapper\"]/div/nav/div[3]/div[2]/div/div/div/form/div[3]/input")
	private WebElement finalSigninBtn;
	
	@FindBy(xpath="//*[@id=\"gatsby-focus-wrapper\"]/div/nav/div[3]/div[1]/div/div/div/form/div[3]/input")
	private WebElement finalLoginBtn;
	

	
	public TVTimeLogin(WebDriver driver) 
	{
		this.driver = driver;
		PageFactory.initElements(driver, this);
		propFileReader = new PropertyFileReader();
	}

	public void visitPage() {
		driver.get(URL);
	}

	
	public void loginProcess(String username, String password)
	{
		try 
		{
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
		finalLoginBtn.click();
		
		return new TVTimeHomePage(driver);
	}
	
	public TVTimeHomePage signinBtnClick()
	{
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
