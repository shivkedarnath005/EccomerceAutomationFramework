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
public class PaymentPage extends BaseClass
{
	
	@FindBy(xpath="//a[@title='Pay by bank wire']")
	WebElement payBankWireMethod;
	
	@FindBy(xpath="//a[@title='Pay by check.']")
	WebElement payByCheckMethod;
	
	public PaymentPage()
	{
		PageFactory.initElements(getDriver(), this);
	}
	
	public OrderSummary clickOnPaymentMethod() throws Throwable
	{
		Action.click(getDriver(),payBankWireMethod);
		return new OrderSummary();
	}

}
