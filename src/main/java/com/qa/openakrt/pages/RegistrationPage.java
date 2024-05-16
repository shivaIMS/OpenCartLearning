package com.qa.openakrt.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.openkart.constants.AppCostants;
import com.qa.openkart.utils.ElementUtil;

public class RegistrationPage {

	private WebDriver driver;
	private ElementUtil eleUtil;

	private By firstName = By.id("input-firstname");
	private By lastName = By.id("input-lastname");
	private By email = By.id("input-email");
	private By telephone = By.id("input-telephone");
	private By password = By.id("input-password");
	private By confirm = By.id("input-confirm");
	private By subscribeYes = By.xpath("//label[@class='radio-inline']/input[@value=1]");
	private By subscribeNo = By.xpath("//label[@class='radio-inline']/input[@value=0]");
	private By checkBox = By.xpath("//input[@type='checkbox']");
	private By continueBtn = By.xpath("//input[@type='submit']");
	private By logoutLink = By.linkText("Logout");
	private By registerLink = By.linkText("Register");
	private By successMsg = By.cssSelector("div#content h1");
	
	private By logout = By.linkText("Register");
	
	//private By successMsg = By.cssSelector("div#content h1");
	

	public RegistrationPage(WebDriver driver) {
		this.driver = driver;
		eleUtil = new ElementUtil(driver);
	}
	
	public boolean userRegister(String firstName, String lastName, String email, String telephone, String password, String subscribe) {
		
		eleUtil.waitForElementVisible(this.firstName, 10).sendKeys(firstName);
		eleUtil.doSendKeys(this.lastName, lastName);
		eleUtil.doSendKeys(this.email, email);
		eleUtil.doSendKeys(this.telephone, telephone);
		eleUtil.doSendKeys(this.password, password);
		eleUtil.doSendKeys(this.confirm, password);
		if(subscribe.equalsIgnoreCase("Yes")) {
			eleUtil.doClick(subscribeYes);
		} else {
			eleUtil.doClick(subscribeNo);
		}
		eleUtil.doClick(checkBox);
		eleUtil.doClick(continueBtn);
		
		String sucessMessage = eleUtil.waitForElementVisible(this.successMsg, 10).getText();
		System.out.println("sucessMessage is : " + sucessMessage);
		if (sucessMessage.equals(AppCostants.USER_REG_SUCCESS_MSG)) {
			eleUtil.doClick(logoutLink);
			eleUtil.doClick(registerLink);
			return true;
		}
		return false;
		
	}

}
