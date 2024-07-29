package com.mystore.testcases;

import java.util.HashMap;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import com.mystore.base.BaseClass;
import com.mystore.dataprovider.DataProviders;
import com.mystore.pageobjects.AccountCreationPage;
import com.mystore.pageobjects.HomePage;
import com.mystore.pageobjects.IndexPage;
import com.mystore.pageobjects.LoginPage;
import junit.framework.Assert;

public class AccountCreationPageTest extends BaseClass
{
	private IndexPage indexPage;
	private LoginPage loginPage;
	private AccountCreationPage accountCreationPage;
	private HomePage homePage;
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
	  
	  @Test(groups="Sanity",dataProvider="email", dataProviderClass=DataProviders.class)
	  public void verifyCreateAccountPageTest(String email) throws Throwable
	  {
		 log.info("Test Case verifyCreateAccountPageTest Started");
		indexPage=new IndexPage();
		loginPage=indexPage.clickOnSignIn();
		accountCreationPage=loginPage.createNewAccount(email);
		boolean result=accountCreationPage.validateAccountCreatePage();
		Assert.assertTrue(result);
		log.info("Test Case verifyCreateAccountPageTest Ended");
		
	  }
	  
	  @Test(groups = "Regression",dataProvider = "newAccountDetailsData",dataProviderClass = DataProviders.class)
		public void createAccountTest(HashMap<String,String> hashMapValue) throws Throwable 
	  {
		    log.info("Test Case createAccountTest Started");
			indexPage= new IndexPage();
			loginPage=indexPage.clickOnSignIn();
			accountCreationPage=loginPage.createNewAccount(hashMapValue.get("Email"));
			accountCreationPage.createAccount(
					hashMapValue.get("Gender"),
					hashMapValue.get("FirstName"),
					hashMapValue.get("LastName"),
					hashMapValue.get("SetPassword"),
					hashMapValue.get("Day"),
					hashMapValue.get("Month"),
					hashMapValue.get("Year"),
					hashMapValue.get("Company"),
					hashMapValue.get("Address"),
					hashMapValue.get("City"),
					hashMapValue.get("State"),
					hashMapValue.get("Zipcode"),
					hashMapValue.get("Country"),
					hashMapValue.get("MobilePhone"));
			homePage=accountCreationPage.validateRegistration();
			Assert.assertEquals("http://automationpractice.com/index.php?controller=my-account", homePage.getCurrURL());
			log.info("Test Case createAccountTest Ended");
		}

}
