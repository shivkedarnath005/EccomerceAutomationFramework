package com.mystore.pageobjects;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.mystore.actiondriver.Action;
import com.mystore.base.BaseClass;

public class IndexPage extends BaseClass
{
	@FindBy(xpath="//a[contains(text(),'Sign in')]")
	WebElement signInBtn;
	
	@FindBy(xpath="//img[@class='logo img-responsive']")
	WebElement myStoreLogo;
	
	@FindBy(id="search_query_top")
	WebElement searchProductBox;
	
	@FindBy(name="submit_search")
	WebElement searchButton;
	
	public IndexPage()
	{
		PageFactory.initElements(getDriver(), this);
	}
	
	public LoginPage clickOnSignIn() throws Throwable
	{
		Action.fluentWait(getDriver(), signInBtn, 10);
		Action.click(getDriver(), signInBtn);
		return new LoginPage();
		//This will link IndexPage to LoginPage interlink
	}
	
	public boolean validateLogo() throws Throwable
	{
		return Action.isDisplayed(getDriver(), myStoreLogo);
	}
	
	public String getMyStoreTitle()
	{
		String myStoreTitle=getDriver().getTitle();
		return myStoreTitle;
	}
	
	public SearchResultPage searchProduct(String productName)
	{
		Action.type(searchProductBox,productName);
		Action.click(getDriver(),searchButton);
		return new SearchResultPage();
	}

}
