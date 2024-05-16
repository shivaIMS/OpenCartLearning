package com.qa.openakrt.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import com.qa.openkart.utils.ElementUtil;
import com.qa.openkart.utils.TimeUtil;

public class LoginPage {
	private WebDriver driver;
	private ElementUtil eleUtil;

	public LoginPage(WebDriver driver) {
		this.driver = driver;
		eleUtil = new ElementUtil(driver);
	}

	private By emailId = By.xpath("//input[@id='input-email']");
	private By passwordId = By.xpath("//input[@id='input-password']");
	private By loginButton = By.xpath("//input[@value='Login']");
	private By forgotPwdLink = By.xpath("//div[@class='form-group']//a[normalize-space()='Forgotten Password']");
	private By registerLink = By.linkText("Register");

	public String getLoginTitleTest() {
		String title = eleUtil.waitForTitleIs("Account Login", TimeUtil.DEFAULT_MEDIUM_TIME);
		System.out.println("Login Page title is : " + title);
		return title;
	}

	public String getLoginCurrentURL() {
		String url = eleUtil.waitForURLContains("route=account/account", TimeUtil.DEFAULT_MEDIUM_TIME);
		System.out.println("Login Page currentURL is : " + url);
		return url;
	}

	public boolean isForgtPwdLinkExist() {
		return eleUtil.isElementDisplayed(forgotPwdLink);
	}

	public AccountPage doLogin(String username, String password) {
		System.out.println("User creds are : " + username + " : " + password);

		eleUtil.waitForElementVisible(emailId, TimeUtil.DEFAULT_LONG_TIME).sendKeys(username);
		eleUtil.doSendKeys(passwordId, password);
		eleUtil.doClick(loginButton, TimeUtil.DEFAULT_MEDIUM_TIME);
		return new AccountPage(driver);
	}

	public RegistrationPage navigateToRegistrationPage() {
		eleUtil.waitForElementVisible(registerLink, TimeUtil.DEFAULT_LONG_TIME);
		eleUtil.doClick(registerLink);
		return new RegistrationPage(driver);

	}
}
