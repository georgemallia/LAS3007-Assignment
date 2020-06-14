package Utilities;

import java.time.Duration;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileDriver;
import io.appium.java_client.MobileElement;

public class CommonUtils 
{

	public CommonUtils() {}
	
	public void waitForElementToBeClickableByCss(String ByLocator, WebDriver driver)
	{
		WebDriverWait wait = new WebDriverWait(driver, 20);
		wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(ByLocator)));
	}
	
	public void waitForElementToBeClickableByXpath(String ByLocator, WebDriver driver)
	{
		WebDriverWait wait = new WebDriverWait(driver, 20);
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath(ByLocator)));
	}
	
	public void waitForElementToBeClickableByLinkText(String ByLocator, WebDriver driver)
	{
		WebDriverWait wait = new WebDriverWait(driver, 20);
		wait.until(ExpectedConditions.elementToBeClickable(By.linkText(ByLocator)));
	}
	
	
	
	public void waitForPageToSettleByCSS(String ByLocator, WebDriver driver) 
	{   
		WebDriverWait wait = new WebDriverWait(driver, 20);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(ByLocator)));
	}
	
	public void waitForPageToSettleByXpath(String ByLocator, WebDriver driver) 
	{   
		WebDriverWait wait = new WebDriverWait(driver, 20);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(ByLocator)));
	}
	
	public void waitForPageToSettleById(String ByLocator, WebDriver driver) 
	{   
		WebDriverWait wait = new WebDriverWait(driver, 20);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(ByLocator)));
	}
	
	public List<String> returnMultipleShowNames(String shows)
	{
		List<String> showList = Arrays.asList(shows.split(","))
				                .stream()
				                .map(s -> s.toLowerCase().trim())
				                .collect(Collectors.toList());
		return showList;
	}
	
	//Android Utils
	
	private Wait<AppiumDriver<MobileElement>> makeWait(int timeout,  AppiumDriver<MobileElement> driver)
	{
		return new FluentWait<>(driver)
				.withTimeout(Duration.ofSeconds(timeout))
				.pollingEvery(Duration.ofSeconds(1))
				.ignoring(NoSuchElementException.class);
	}
	
	public MobileElement waitForPresence(By elem,  AppiumDriver<MobileElement> driver) 
	{
		return makeWait(20, driver).until(new Function<MobileDriver<?>, MobileElement>() 
		{
			public MobileElement apply(MobileDriver<?> driver) 
			{
				return (MobileElement) driver.findElement(elem);
			}
		});
	}
}
