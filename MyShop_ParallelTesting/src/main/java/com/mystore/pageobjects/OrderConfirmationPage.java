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
public class OrderConfirmationPage extends BaseClass
{
	@FindBy(xpath="//p[contains(text(),'Your order on My Shop is complete.')]")
	WebElement confirmMessage;
	
	@FindBy(xpath="//a[@title='Pay by check.']")
	WebElement payByCheckMethod;
	
	public OrderConfirmationPage()
	{
		PageFactory.initElements(getDriver(), this);
	}
	
	public String validateConfirmMessage() throws Throwable
	{
		String confirmMsg=confirmMessage.getText();       
		return confirmMsg;
	}

}
