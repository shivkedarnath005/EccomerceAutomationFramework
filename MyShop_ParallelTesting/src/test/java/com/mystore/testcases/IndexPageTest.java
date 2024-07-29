package com.mystore.testcases;

import org.testng.annotations.Test;
import com.mystore.base.BaseClass;
import com.mystore.pageobjects.IndexPage;
import junit.framework.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.AfterMethod;

public class IndexPageTest extends BaseClass 
{

	public static final Logger log = LogManager.getLogger(IndexPageTest.class.getName());
    IndexPage indexPage;
    
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

    @Test(groups="Smoke")
    public void verifyLogo() throws Throwable 
    {
    	log.info("Test Case verifyLogo started");
	    indexPage=new IndexPage();
	    boolean result=indexPage.validateLogo();
	    Assert.assertTrue(result);
	    log.info("Test Case verifyLogo Ended");
    }
  
    @Test(groups="Smoke")
    public void verifyStoreTitle() throws Throwable 
    {
    	log.info("Test Case verifyStoreTitle started");
	    String actTitle=indexPage.getMyStoreTitle();
	    Assert.assertEquals(actTitle,"My Shop");
	    log.info("Test Case verifyStoreTitle Ended");
    }
    
}
