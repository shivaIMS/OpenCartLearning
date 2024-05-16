package com.qa.openkart.tests;

import java.util.List;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.qa.openkart.Logger.Log;
import com.qa.openkart.base.BaseTest;
import com.qa.openkart.constants.AppCostants;

public class AccountPageTest extends BaseTest {

	@BeforeClass
	public void accSetup() {
		accPage = loginPage.doLogin(prop.getProperty("username"), prop.getProperty("password"));
	}

	@Test
	public void accPageTitleTest() {
		Log.info("Inside the test method accPageTitleTest....");
		String accTitle = accPage.getAccPageTitle();
		Assert.assertEquals(accTitle, AppCostants.ACCOUNTS_PAGE_TITLE);
	}

	@Test
	public void accPageURLTest() {
		Log.info("Inside the test method accPageURLTest....");
		String accPageURL = accPage.checkAccPageURL();
		Assert.assertTrue(accPageURL.contains(AppCostants.ACC_PAGE_URL_FRACTION));
	}

	@Test
	public void isLogoutLinkExists() {
		Log.info("Inside the test method isLogoutLinkExists....");
		Assert.assertTrue(accPage.isLogoutPresent());
	}

	@Test
	public void isMyAccountLinkExists() {
		Log.info("Inside the test method isMyAccountLinkExists....");
		Assert.assertTrue(accPage.myAccountLinkExists());
	}

	@Test
	public void getAccountPageHeaders() {
		Log.info("Inside the test method getAccountPageHeaders....");
		List<String> acntHeadersLits = accPage.getAccountPageHeader();
		System.out.println(acntHeadersLits);
	}
	
	@Test
	public void searchTest() {
		accPage.doSearch("macbook");		
	}

}
