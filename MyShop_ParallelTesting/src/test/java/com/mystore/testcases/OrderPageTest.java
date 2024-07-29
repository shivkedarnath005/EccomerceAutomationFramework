package com.mystore.testcases;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import com.mystore.base.BaseClass;
import com.mystore.dataprovider.DataProviders;
import com.mystore.pageobjects.AddToCartPage;
import com.mystore.pageobjects.IndexPage;
import com.mystore.pageobjects.OrderPage;
import com.mystore.pageobjects.SearchResultPage;

public class OrderPageTest extends BaseClass 
{
	  public static final Logger log = LogManager.getLogger(IndexPageTest.class.getName());
	  IndexPage indexPage;
	  SearchResultPage searchPage;
	  AddToCartPage addtoCartPage;
	  OrderPage orderPage;
	  
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
	  public void verifyOrderTotalPrice(String product, String qty, String size, String color) throws Throwable 
	  {
		  log.info("Test Case verifyOrderTotalPrice Started");
		  indexPage=new IndexPage();
		  searchPage=indexPage.searchProduct(product);
		  addtoCartPage=searchPage.clickOnProduct();
		  addtoCartPage.selectSize(size);
		  addtoCartPage.selectColor(color);
		  addtoCartPage.enterQuantity(qty);
		  addtoCartPage.clickOnAddToCart();
		  orderPage=addtoCartPage.clickOnCheckOut();
		  Double unitPrice=orderPage.getUnitPrice();
		  Double totalPrice=orderPage.getTotalPrice();
		  Double totalExpectedPrice=(unitPrice*(Double.parseDouble(qty)))+7;
		  Assert.assertEquals(totalPrice, totalExpectedPrice);
		  log.info("Test Case verifyOrderTotalPrice Ended");
		  
	  }
	  
}
