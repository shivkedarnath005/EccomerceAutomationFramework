package com.mystore.testcases;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import com.mystore.base.BaseClass;
import com.mystore.dataprovider.DataProviders;
import com.mystore.pageobjects.HomePage;
import com.mystore.pageobjects.IndexPage;
import com.mystore.pageobjects.LoginPage;
import junit.framework.Assert;

public class HomePageTest extends BaseClass 
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
	  
		/*
		 * @Test(groups="Smoke",dataProvider="credentials",
		 * dataProviderClass=DataProviders.class) public void wishListTest(String uname,
		 * String pswd) throws Throwable { log.info("Test Case wishListTest Started");
		 * indexPage=new IndexPage(); loginPage=indexPage.clickOnSignIn();
		 * homePage=loginPage.loginToHome(prop.getProperty(uname),
		 * prop.getProperty(pswd)); boolean result=homePage.validateMyWishList();
		 * Assert.assertTrue(result); log.info("Test Case wishListTest Ended"); }
		 */
	  
	  @Test(groups="Smoke",dataProvider="credentials", dataProviderClass=DataProviders.class)
	  public void orderHistoryandDetailsTest(String uname, String pswd) throws Throwable 
	  {
		  log.info("Test Case orderHistoryandDetailsTest Started");
		  indexPage=new IndexPage();
		  loginPage=indexPage.clickOnSignIn();
		  homePage=loginPage.loginToHome(prop.getProperty(uname), prop.getProperty(pswd));
		  boolean result=homePage.validateOrderHistory();
		  Assert.assertTrue(result);
		  log.info("Test Case orderHistoryandDetailsTest Ended");
	  }
	 
}
