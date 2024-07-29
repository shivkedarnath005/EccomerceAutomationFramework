/**
 * 
 */
package com.mystore.pageobjects;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import com.mystore.actiondriver.Action;
import com.mystore.base.BaseClass;

/**
 * 
 */
public class SearchResultPage extends BaseClass
{
	@FindBy(xpath="//li[@class='clearfix']//a[@class='product-name'][normalize-space()='Blouse']")
	WebElement productResult;
	
	public SearchResultPage()
	{
		PageFactory.initElements(getDriver(), this);
	}
	
	public boolean isProductAvailable() throws Throwable
	{
		return Action.isDisplayed(getDriver(), productResult);
	}
	
	public AddToCartPage clickOnProduct() throws Throwable
	{
		Action.click(getDriver(), productResult);
		return new AddToCartPage();
		//This will link SearchResultPage to AddToCartPage interlink
	}
	

}
