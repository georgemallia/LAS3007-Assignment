package Utilities;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertyFileReader
{
	String propFileName = "properties/config.properties";
	Properties property = new Properties();
	String result = "";
	
	public PropertyFileReader()
	{
		
	}
	
	public String getPropertyValue(String propName) throws IOException
	{
		try(InputStream inputStream = getClass().getClassLoader().getResourceAsStream(propFileName))
		{
			if (inputStream != null) 
			{
				property.load(inputStream);
			} 
			else 
			{
				throw new FileNotFoundException("property file '" + propFileName + "' not found in the classpath");
			}
			
			result = property.getProperty(propName);
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
		return result;
	}
	
}
