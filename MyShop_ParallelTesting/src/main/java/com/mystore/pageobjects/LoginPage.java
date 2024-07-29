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
public class LoginPage extends BaseClass 
{
	@FindBy(id="email")
	WebElement userName;
	
	@FindBy(name="passwd")
	WebElement password;
	
	@FindBy(id="SubmitLogin")
	WebElement signInBtn;
	
	@FindBy(name="email_create")
	WebElement emailForNewAccount;
	
	@FindBy(name="SubmitCreate")
	WebElement createNewAccount;
	
	
	
	public LoginPage()
	{
		PageFactory.initElements(getDriver(), this);
	}
	
	public HomePage loginToHome(String uname, String pswd) throws Throwable
	{
		Action.fluentWait(getDriver(), signInBtn, 10);
		Action.type(userName, uname);
		Action.type(password, pswd);
		Action.click(getDriver(), signInBtn);
		return new HomePage();	
	}
	
	public AddressPage loginToAddress(String uname, String pswd) throws Throwable
	{
		Action.type(userName, uname);
		Action.type(password, pswd);
		Action.click(getDriver(), signInBtn);
		return new AddressPage();	
	}
	
	public AccountCreationPage createNewAccount(String newEmail) throws Throwable
	{
		Action.type(emailForNewAccount, newEmail);
		Action.click(getDriver(), createNewAccount);
		return new AccountCreationPage();
		//This will link LoginPage to AccountCreationPage interlink
	}
	

}
