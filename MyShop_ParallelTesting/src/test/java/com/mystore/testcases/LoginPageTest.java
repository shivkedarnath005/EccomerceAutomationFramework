package com.mystore.testcases;

import org.testng.annotations.Test;
import com.mystore.base.BaseClass;
import com.mystore.dataprovider.DataProviders;
import com.mystore.pageobjects.HomePage;
import com.mystore.pageobjects.IndexPage;
import com.mystore.pageobjects.LoginPage;
import com.mystore.utility.Log;
import junit.framework.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.AfterMethod;

public class LoginPageTest extends BaseClass
{
	  IndexPage indexPage;
	  LoginPage loginPage;
	  HomePage homePage;
	  public static final Logger log = LogManager.getLogger(IndexPageTest.class.getName());
	  
	  @Parameters("Browser")
	  @BeforeMethod(groups={"Smoke","Sanity","Regression"})
	  public void setup(String browser) 
	  {
		 launchApp(browser); 
	  }
	  
	  @AfterMethod(groups={"Smoke","Sanity","Regression"})
	  public void tearDown() 
	  {
		  getDriver().quit();
	  }
	  
	  @Test(groups= {"smoke","sanity"},dataProvider="credentials", dataProviderClass=DataProviders.class)
	  public void loginTest(String uname, String pswd) throws Throwable 
	  {
		  log.info("Test Case loginTest Started");
		  indexPage=new IndexPage();
		  log.info("User is going to Click on Sign In");
		  loginPage=indexPage.clickOnSignIn();
		  log.info("Enter username and password");
		  //homePage=loginPage.loginToHome(prop.getProperty("Username"), prop.getProperty("Password"));
		  homePage=loginPage.loginToHome(uname,pswd);
		  String actualURL=homePage.getCurrURL();
		  String expectedURL="http://www.automationpractice.pl/index.php?controller=my-account";
		  log.info("Verifying whether user is able to login");
		  Assert.assertEquals(actualURL, expectedURL);
		  log.info("login successful");
		  log.info("Test Case loginTest Ended");
	  }
	 
}
