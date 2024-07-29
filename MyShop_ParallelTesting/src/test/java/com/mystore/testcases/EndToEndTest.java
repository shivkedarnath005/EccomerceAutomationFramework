package com.mystore.testcases;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import com.mystore.base.BaseClass;
import com.mystore.dataprovider.DataProviders;
import com.mystore.pageobjects.AddToCartPage;
import com.mystore.pageobjects.AddressPage;
import com.mystore.pageobjects.IndexPage;
import com.mystore.pageobjects.LoginPage;
import com.mystore.pageobjects.OrderConfirmationPage;
import com.mystore.pageobjects.OrderPage;
import com.mystore.pageobjects.OrderSummary;
import com.mystore.pageobjects.PaymentPage;
import com.mystore.pageobjects.SearchResultPage;
import com.mystore.pageobjects.ShippingPage;
import junit.framework.Assert;

public class EndToEndTest extends BaseClass 
{
	  IndexPage indexPage;
	  SearchResultPage searchPage;
	  AddToCartPage addtoCartPage;
	  OrderPage orderPage;
	  LoginPage loginPage;
	  AddressPage addressPage;
	  ShippingPage shippingPage;
	  PaymentPage paymentPage;
	  OrderSummary orderSummary;
	  OrderConfirmationPage orderConfirmPage;
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

	  @Test(groups="Regression",dataProvider="getProduct", dataProviderClass=DataProviders.class)
	  public void endToEndTest(String product, String qty, String size, String color) throws Throwable 
	  {
		  log.info("Test Case endToEndTest Started");
		  indexPage=new IndexPage();
		  searchPage=indexPage.searchProduct(product);
		  addtoCartPage=searchPage.clickOnProduct();
		  addtoCartPage.selectSize(size);
		  addtoCartPage.selectColor(color);
		  addtoCartPage.enterQuantity(product);
		  addtoCartPage.clickOnAddToCart();
		  orderPage=addtoCartPage.clickOnCheckOut();
		  loginPage=orderPage.clickOnCheckOut();
		  addressPage=loginPage.loginToAddress(prop.getProperty("Username"), prop.getProperty("Password"));
		  shippingPage=addressPage.clickOnCheckOut();
		  shippingPage.checkTheTerms();
		  paymentPage=shippingPage.clickOnProceedToCheckOut();
		  paymentPage.clickOnPaymentMethod();
		  orderConfirmPage=orderSummary.clickOnConfirmOrder();
		  String actualOrderMessage=orderConfirmPage.validateConfirmMessage();
		  String expectedOrderMsg="Your order on My Shop is complete.";
		  Assert.assertEquals(actualOrderMessage, expectedOrderMsg);
		  log.info("Test Case endToEndTest ended");
		    
	  }
	  
}
