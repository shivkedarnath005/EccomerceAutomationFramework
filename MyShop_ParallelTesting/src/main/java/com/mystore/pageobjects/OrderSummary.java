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
public class OrderSummary extends BaseClass
{
	@FindBy(xpath="//span[normalize-space()='I confirm my order']")
	WebElement confirmOrderBtn;
	
	@FindBy(xpath="//a[@title='Pay by check.']")
	WebElement payByCheckMethod;
	
	public OrderSummary()
	{
		PageFactory.initElements(getDriver(), this);
	}
	
	public OrderConfirmationPage clickOnConfirmOrder() throws Throwable
	{
		Action.click(getDriver(),confirmOrderBtn);
        return new OrderConfirmationPage();
	}

}
