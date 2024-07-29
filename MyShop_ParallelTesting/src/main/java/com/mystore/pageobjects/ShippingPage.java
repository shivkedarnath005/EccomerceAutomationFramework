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
public class ShippingPage extends BaseClass
{
	
	@FindBy(id="cgv")
	WebElement serviceTerms;
	
	@FindBy(xpath="//button[@name='processCarrier']//span[contains(text(),'Proceed to checkout')]")
	WebElement proceedToCheckOutBtn;
	
	public ShippingPage()
	{
		PageFactory.initElements(getDriver(), this);
	}
	
	public void checkTheTerms()
	{
		Action.click(getDriver(),serviceTerms);
	}
	
	public PaymentPage clickOnProceedToCheckOut() throws Throwable
	{
		Action.click(getDriver(),proceedToCheckOutBtn);
		return new PaymentPage();
	}

}
