package com.qa.openkart.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import com.qa.openkart.base.BaseTest;

public class SearchResultsTest extends BaseTest {

	@BeforeClass
	public void accSetup() {
		accPage = loginPage.doLogin(prop.getProperty("username"), prop.getProperty("password"));
	}

	@Test(dataProvider="getProductDataCount")
	public void searchResultsTest(String searchKey, int productCount) {
		searchResultPage = accPage.doSearch(searchKey);
		Assert.assertEquals(searchResultPage.getSearchProductCounts(), productCount);
	}
	
	@DataProvider
	public Object[][] getProductDataCount() {
		return new Object[][] {
			{"macbook",3},
			{"imac",1},
			{"Samsung", 2}
		};
	}
}
