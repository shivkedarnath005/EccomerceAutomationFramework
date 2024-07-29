package com.mystore.testcases;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import com.mystore.base.BaseClass;
import com.mystore.dataprovider.DataProviders;
import com.mystore.pageobjects.IndexPage;
import com.mystore.pageobjects.SearchResultPage;
import junit.framework.Assert;

public class SearchResultPageTest extends BaseClass 
{
	  IndexPage indexPage;
	  SearchResultPage searchPage;
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

	  @Test(groups="Smoke",dataProvider="getProduct", dataProviderClass=DataProviders.class)
	  public void productAvailabilityTest(String product) throws Throwable 
	  {
		  log.info("Test Case productAvailabilityTest Started");
		  indexPage=new IndexPage();
		  searchPage=indexPage.searchProduct(product);
		  boolean result=searchPage.isProductAvailable();
		  Assert.assertTrue(result);
		  log.info("Test Case productAvailabilityTest Ended");
		  
	  }
	  
	 
}
