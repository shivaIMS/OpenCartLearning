package com.qa.openkart.tests;

import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import com.qa.openkart.base.BaseTest;

public class ProducPagetInfoTest extends BaseTest {

	@BeforeClass
	public void infoPageSetup() {
		System.out.println("********* Inside the test infoPageSetup *********");
		accPage = loginPage.doLogin(prop.getProperty("username"), prop.getProperty("password"));
	}

	@Test
	public void productHeaderTest() {
		System.out.println("********* Inside the test productHeaderTest *********");
		searchResultPage = accPage.doSearch("macbook");
		producInfoPage = searchResultPage.selectProduct("MacBook Pro");
		Assert.assertEquals(producInfoPage.getProductHeader(), "MacBook Pro");
	}
	
	@DataProvider
	public Object[][] getProductImagesCount(){
		return new Object[][] {
			{"imac","iMac",3},
			{"macbook pro", "MacBook Pro", 4},
			{"Samsung","Samsung SyncMaster 941BW",1},
			{"Samsung","Samsung Galaxy Tab 10.1",7}
		};
	}

	@Test(dataProvider = "getProductImagesCount")
	public void productImagesCountTest(String searchKey, String productName, int imagesCount) {
		System.out.println("********* Inside the test productImagesCountTest *********");
		searchResultPage = accPage.doSearch(searchKey);
		producInfoPage = searchResultPage.selectProduct(productName);
		Assert.assertEquals(producInfoPage.getImagesCount(), imagesCount);
	}

	@Test
	public void productInfoTest() {
		System.out.println("********* Inside the test productInfoTest *********");
		searchResultPage = accPage.doSearch("macbook");
		producInfoPage = searchResultPage.selectProduct("MacBook Pro");
		Map<String, String> productAct = producInfoPage.getProductDetails();
		softAssert.assertEquals(productAct.get("Brand"), "Apple");
		softAssert.assertEquals(productAct.get("Product Code"), "Product 18");
		softAssert.assertEquals(productAct.get("Availability"), "In Stock");
		softAssert.assertEquals(productAct.get("productPrice"), "$2,000.00");
		softAssert.assertEquals(productAct.get("exPriceTax"), "$2,000.00");
		softAssert.assertAll();
	}

}
