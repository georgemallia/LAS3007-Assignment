package Android.Activities;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;

public class SearchActivity
{
	private AppiumDriver<MobileElement> mobileDriver;
	
	public SearchActivity(AppiumDriver<MobileElement> mobileDriver) 
	{
		this.mobileDriver = mobileDriver;
	}
	
	public void searchShow(String showName)
	{
		//going to search tab
		MobileElement searchTab = (MobileElement) mobileDriver.findElementByXPath("//android.widget.FrameLayout[@content-desc=\"Discover\"]/android.widget.ImageView");
		searchTab.click();

		//click the search bar
		MobileElement searchBar = (MobileElement) mobileDriver.findElementById("com.tozelabs.tvshowtime:id/search");
		searchBar.click();

		//sending show name and automatically searches 
		MobileElement searchText = (MobileElement) mobileDriver.findElementById("android:id/search_src_text");
		searchText.sendKeys(showName);
	}
	
	public void openShow()
	{
		//click the show to open it
		MobileElement show = (MobileElement) mobileDriver.findElementById("com.tozelabs.tvshowtime:id/entityName");
		show.click();
	}
	
}
