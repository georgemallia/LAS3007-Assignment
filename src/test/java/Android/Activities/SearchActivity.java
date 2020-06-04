package Android.Activities;

import org.openqa.selenium.By;

import Utilities.CommonUtils;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;

public class SearchActivity
{
	private AppiumDriver<MobileElement> mobileDriver;
	
	private By searchinitBy = By.id("com.tozelabs.tvshowtime:id/search");
	private By searchfldBy = By.id("android:id/search_src_text");
	private By searchedShowBy = By.id("com.tozelabs.tvshowtime:id/entityName");
	private By showPageBy = By.id("com.tozelabs.tvshowtime:id/btAction");

	CommonUtils utils = new CommonUtils();
	
	public SearchActivity(AppiumDriver<MobileElement> mobileDriver) 
	{
		this.mobileDriver = mobileDriver;
	}
	
	public void searchShow(String showName)
	{
		//going to search tab
		MobileElement searchTab = (MobileElement) mobileDriver.findElementByXPath("//android.widget.FrameLayout[@content-desc=\"Discover\"]/android.widget.ImageView");
		searchTab.click();

		utils.waitForPresence(searchinitBy, mobileDriver);
		
		//click the search bar
		MobileElement searchBar = (MobileElement) mobileDriver.findElementById("com.tozelabs.tvshowtime:id/search");
		searchBar.click();

		utils.waitForPresence(searchfldBy, mobileDriver);
		
		//sending show name and automatically searches 
		MobileElement searchText = (MobileElement) mobileDriver.findElementById("android:id/search_src_text");
		searchText.sendKeys(showName);
		
		utils.waitForPresence(searchedShowBy, mobileDriver);
	}
	
	public void openShow()
	{
		//click the show to open it
		MobileElement show = (MobileElement) mobileDriver.findElementById("com.tozelabs.tvshowtime:id/entityName");
		show.click();
		
		utils.waitForPresence(showPageBy, mobileDriver);
		
	}
	
}
