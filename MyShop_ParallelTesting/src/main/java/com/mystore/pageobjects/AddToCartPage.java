package com.mystore.pageobjects;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.mystore.actiondriver.Action;
import com.mystore.base.BaseClass;

public class AddToCartPage extends BaseClass
{
	@FindBy(xpath="//a[@id='color_8']")
	WebElement productColor;
	
	@FindBy(id="quantity_wanted")
	WebElement quantity;
	
	@FindBy(name="group_1")
	WebElement size;
	
	@FindBy(xpath="//span[contains(text(),'Add to cart')]")
	WebElement addToCartBtn;
	
	@FindBy(xpath="//h2[normalize-space()='Product successfully added to your shopping cart']")
	WebElement addToCartMessage;
	
	@FindBy(xpath="//span[normalize-space()='Proceed to checkout']")
	WebElement proceedToCheckOutBtn;
	
	
	public AddToCartPage()
	{
		PageFactory.initElements(getDriver(), this);
	}
	
	public void selectColor(String color) throws Throwable
	{
		Action.click(getDriver(), productColor);
		Action.type(productColor, color);
	}
	
	public void enterQuantity(String quantity1) throws Throwable
	{
		Action.type(quantity, quantity1);
	}
	
	public void selectSize(String size1) throws Throwable
	{
		Action.selectByVisibleText(size1, size);
	}
	
	public void clickOnAddToCart() throws Throwable
	{
		Action.click(getDriver(), addToCartBtn);
	}
	
	public boolean validateAddToCart() throws Throwable
	{
		
		return Action.isDisplayed(getDriver(), addToCartMessage);
	}
	
	public OrderPage clickOnCheckOut() throws Throwable
	{
		Action.fluentWait(getDriver(), proceedToCheckOutBtn, 10);
		Action.JSClick(getDriver(),proceedToCheckOutBtn);
		return new OrderPage();
	}
	
	

}
