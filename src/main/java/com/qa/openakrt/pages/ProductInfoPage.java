package com.qa.openakrt.pages;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.openkart.utils.ElementUtil;

public class ProductInfoPage {

	private WebDriver driver;
	private ElementUtil util;

	/***
	 * Follows random insertion order
	 */
	private Map<String, String> productMap = new HashMap<String, String>();
	
	/***
	 * Follows insertion order
	 */
	// private Map<String, String> productMap = new TreeMap<String, String>();

	/***
	 * Follows Alphabetical order
	 */
	// private Map<String, String> productMap = new TreeMap<String, String>();

	private By images = By.cssSelector("ul.thumbnails img");
	private By productHeader = By.tagName("h1");
	private By productMetaData = By.xpath("(//div[@id='content']//ul[@class='list-unstyled'])[1]/li");
	private By productPriceData = By.xpath("(//div[@id='content']//ul[@class='list-unstyled'])[2]/li");

	public ProductInfoPage(WebDriver driver) {
		this.driver = driver;
		util = new ElementUtil(driver);
	}

	public String getProductHeader() {
		String header = util.doGetElementText(productHeader);
		System.out.println(header);
		return header;
	}

	public int getImagesCount() {
		int totalImages = util.waitForAllElementsVisible(images, 10).size();
		System.out.println("images count for" + getProductHeader() + " : " + totalImages);
		return totalImages;
	}

//	Brand: Apple
//	Product Code: Product 18
//	Reward Points: 800
//	Availability: In Stock
	private void getProductMetaData() {
		List<WebElement> metaList = util.getElements(productMetaData);
		for (WebElement e : metaList) {
			String text = e.getText();
			String metaKey = text.split(":")[0].trim();
			String metaValue = text.split(":")[1].trim();
			productMap.put(metaKey, metaValue);
		}

	}

	private void getProductPriceData() {
		List<WebElement> priceList = util.getElements(productPriceData);
		String price = priceList.get(0).getText();
		String exTaxPrice = priceList.get(1).getText().split(":")[1].trim();
		productMap.put("productPrice", price);
		productMap.put("exPriceTax", exTaxPrice);
	}

	public Map<String, String> getProductDetails() {
		productMap.put("header", getProductHeader());
		productMap.put("imagesCount", String.valueOf(getImagesCount()));
		getProductMetaData();
		getProductPriceData();
		System.out.println("product Details: \n" + productMap);
		return productMap;
	}

}
