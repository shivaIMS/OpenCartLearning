package com.qa.openkart.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.qa.openkart.Logger.Log;
import com.qa.openkart.base.BaseTest;
import com.qa.openkart.constants.AppCostants;
import com.qa.openkart.errors.AppError;

public class LoginPageTest extends BaseTest {

	@Test(priority = 1)
	public void loginPageTitleTest() {
		Log.info("Inside loginPageTitleTest....");
		String loginTitle = loginPage.getLoginTitleTest();
		Assert.assertEquals(loginTitle, AppCostants.LOGIN_PAGE_TILE, AppError.TTITLE_NOT_FOUND);
	}

	@Test(priority = 2)
	public void loginPageURLeTest() {
		Log.info("Inside loginPageURLeTest....");
		String loginCurrentURL = loginPage.getLoginCurrentURL();
		Assert.assertTrue(loginCurrentURL.contains(AppCostants.LOGIN_PAGE_URL_FRACTION), AppError.URL_NOT_FOUND);
	}

	@Test(priority = 3)
	public void loginPageFrgtPwdTest() {
		Log.info("Inside loginPageFrgtPwdTest....");
		Assert.assertTrue(loginPage.isForgtPwdLinkExist(), AppError.ELEMENT_NOT_FOUND);
	}

	@Test(priority = 4)
	public void loginTest() {
		Log.info("Inside loginTest....");
		accPage = loginPage.doLogin("dec2023@opencart.con", "Selenium@12345");
		Assert.assertEquals(accPage.getAccPageTitle(), AppCostants.ACCOUNTS_PAGE_TITLE);
	}

}
