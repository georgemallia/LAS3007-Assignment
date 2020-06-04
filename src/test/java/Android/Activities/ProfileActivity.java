package Android.Activities;

import java.util.List;

import org.openqa.selenium.By;

import Utilities.CommonUtils;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;

public class ProfileActivity 
{
	private AppiumDriver<MobileElement> mobileDriver;
	
	CommonUtils utils = new CommonUtils();
	
	public ProfileActivity(AppiumDriver<MobileElement> mobileDriver) 
	{
		this.mobileDriver = mobileDriver;
	}

	public void navigateToProfile()
	{
		MobileElement profileTabBtn = (MobileElement) mobileDriver.findElementByAccessibilityId("Profile");
		profileTabBtn.click();
	}
	
	public void openShows()
	{
		MobileElement seeShowsBtn = (MobileElement) mobileDriver.findElementById("com.tozelabs.tvshowtime:id/btSeeAllEntities");
		seeShowsBtn.click();
	}
	
	public List<MobileElement> getShows()
	{
		List<MobileElement> rtnShows;
		
		//elemet that contains all shows 
		MobileElement showsList = (MobileElement) mobileDriver.findElementById("com.tozelabs.tvshowtime:id/ptrLayout");
		
		
		rtnShows = showsList.findElements(By.id("com.tozelabs.tvshowtime:id/entityName"));
		
		return rtnShows;
	}
	
}
