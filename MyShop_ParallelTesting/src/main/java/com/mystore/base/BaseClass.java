package com.mystore.base;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.core.LoggerContext;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import com.mystore.utility.ExtentManager;
import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseClass 
{
	
	public static Properties prop;
	public static ThreadLocal<RemoteWebDriver> driver = new ThreadLocal<>();
	public static final Logger log = LogManager.getLogger(BaseClass.class.getName());
	
	@BeforeSuite(groups={"Smoke","Sanity","Regression"})
	public void loadConfig() throws IOException
	{
		ExtentManager.setExtent();
		File file = new File("log4j2.xml");
		LoggerContext context = (LoggerContext) LogManager.getContext(false);
		context.setConfigLocation(file.toURI());
		try 
		{
		   prop=new Properties();
		   System.out.println("super constructor invoked");
		   FileInputStream ip;
		
			ip = new FileInputStream(
					System.getProperty("user.dir")+"\\Configuration\\Config.properties");
		
		   prop.load(ip);
		} 
		catch (FileNotFoundException e) 
		{
			e.printStackTrace();
		}
		catch (IOException e) 
		{
			e.printStackTrace();
		}	
	}
	
	public static WebDriver getDriver() 
	{
		// Get Driver from threadLocalmap
		return driver.get();
	}
	
	
	
	@SuppressWarnings("deprecation")
	public static void launchApp(String browserName)
	{

		//String browserName=prop.getProperty("Browser");
		
		if(browserName.equalsIgnoreCase("Chrome"))
		{
			WebDriverManager.chromedriver().setup();
			driver.set(new ChromeDriver());
			
		}
		else if(browserName.equalsIgnoreCase("FireFox"))
		     {
			   WebDriverManager.firefoxdriver().setup();
			   driver.set(new FirefoxDriver());
			   
		     }
		     else if(browserName.equalsIgnoreCase("IE"))
	             {
		    	     WebDriverManager.iedriver().setup();
		    	     driver.set(new InternetExplorerDriver());
		         
	             }
		      //Maximize the screen
		getDriver().manage().window().maximize();
				//Delete all the cookies
		getDriver().manage().deleteAllCookies();
				//Implicit TimeOuts
		getDriver().manage().timeouts().implicitlyWait
		(Integer.parseInt(prop.getProperty("implicitWait")),TimeUnit.SECONDS);
		//PageLoad TimeOuts
		getDriver().manage().timeouts().pageLoadTimeout
		(Integer.parseInt(prop.getProperty("pageLoadTimeOut")),TimeUnit.SECONDS);
				//Launching the URL
		getDriver().get(prop.getProperty("Url"));
		
	}
	
	@AfterSuite
	public void afterSuite()
	{
		ExtentManager.endReport();
	}

}
