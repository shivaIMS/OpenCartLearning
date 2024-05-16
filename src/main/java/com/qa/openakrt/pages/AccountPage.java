package com.qa.openakrt.pages;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import com.qa.openkart.constants.AppCostants;
import com.qa.openkart.utils.ElementUtil;

public class AccountPage {

	private WebDriver driver;
	private ElementUtil util;
	private By headers = By.cssSelector("div#content h2");
	private By logoutLink = By.xpath("(//a[text()='Logout'])[2]");
	private By MyAccountLink = By.linkText("My Account");
	private By search = By.name("search");
	private By searchIcon = By.cssSelector("div#search button");

	public AccountPage(WebDriver driver) {
		this.driver = driver;
		util = new ElementUtil(driver);
	}

	public String getAccPageTitle() {
		String title = util.waitForTitleIs(AppCostants.ACCOUNTS_PAGE_TITLE, 10);
		System.out.println("Account title is : " + title);
		
		return title;
	}

	public boolean checkPresenceOfLogOutLink() {
		return util.waitForElementVisible(logoutLink, 10).isDisplayed();
	}

	public String checkAccPageURL() {
		String accountPageUrl = util.waitForURLContains(AppCostants.ACC_PAGE_URL_FRACTION, 10);
		System.out.println("Account Page url is : " + accountPageUrl);
		return accountPageUrl;
	}

	public boolean myAccountLinkExists() {
		return util.waitForElementVisible(MyAccountLink, 10).isDisplayed();
	}

	public boolean isLogoutPresent() {
		return util.waitForElementVisible(logoutLink, 10).isDisplayed();
	}

	public List<String> getAccountPageHeader() {
		List<WebElement> headersELeList = util.getElements(headers);
		List<String> headerList = new ArrayList<String>();
		for (WebElement ele : headersELeList) {
			String header = ele.getText();
			headerList.add(header);
		}
		return headerList;
	}

	public SearchResultsPage doSearch(String searchKey) {
		System.out.println("Searching for a Key : " + searchKey);
		util.doSendKeys(search, searchKey);
		util.doClick(searchIcon);
		return new SearchResultsPage(driver);
	}

}
