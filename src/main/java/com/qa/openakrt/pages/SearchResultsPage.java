package com.qa.openakrt.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.openkart.utils.ElementUtil;

public class SearchResultsPage {

	private WebDriver driver;
	private ElementUtil util;

	private By headers = By.cssSelector("div#content h2");
	private By logoutLink = By.xpath("(//a[text()='Logout'])[2]");
	private By MyAccountLink = By.linkText("My Account");
	private By search = By.name("search");
	private By searchIcon = By.cssSelector("div#search button");
	private By searchProducts = By.cssSelector("div.product-thumb");

	public SearchResultsPage(WebDriver driver) {
		this.driver = driver;
		util = new ElementUtil(driver);
	}
	
	public int getSearchProductCounts() {
		return util.waitForAllElementsVisible(searchProducts, 10).size();
	}

	public ProductInfoPage selectProduct(String productName) {
		System.out.println("Searching for product name : " + productName);
		util.waitForElementVisible(By.linkText(productName), 10).click();
		return new ProductInfoPage(driver);
	}
}
