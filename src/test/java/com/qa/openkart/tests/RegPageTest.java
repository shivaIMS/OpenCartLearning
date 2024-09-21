package com.qa.openkart.tests;

import io.qameta.allure.Step;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import com.qa.openkart.base.BaseTest;
import com.qa.openkart.constants.AppCostants;
import com.qa.openkart.utils.ExcelUtil;
import com.qa.openkart.utils.StringUtils;

public class RegPageTest extends BaseTest {
	
	@BeforeClass
	public void regSetup() {
		registrationPage = loginPage.navigateToRegistrationPage();
	}
	
	@DataProvider
	public Object[][] registrationDataProvider(){
		return new Object[][] {
			{"Shiva", "IT", "8877665544", "shiv@123456", "Yes"},
			{"Bharath", "NonIT", "112233455", "shi@123456", "Yes"},
			{"Naveen", "NonIT", "3344334455", "shv@12456", "No"},
			{"david", "Software", "1122336677", "shiv@3456", "Yes"},
			{"millar", "Hardware", "7766551122", "shiv@12345", "No"},
			{"Tommy", "time", "7766443322", "shiv@12346", "Yes"},
		};
	}
	
	@DataProvider
	public Object[][] getDataFromTheExcel() {
		return ExcelUtil.getExcelTestData(AppCostants.REGISTER_SHEET_NAME);
	}


//	@Test(dataProvider = "getDataFromTheExcel")
//	public void userRegistrationPage(String fname, String lname,String telephone, String password, String subscribe) {
//		Assert.assertTrue(registrationPage.userRegister(fname, lname,StringUtils.randEMailID() ,telephone,password, subscribe));
//	}

	@Step("userRegistrationPage")
	@Test(dataProvider = "registrationDataProvider")
	public void userRegistrationPage(String fname, String lname,String telephone, String password, String subscribe) {
		Assert.assertTrue(registrationPage.userRegister(fname, lname,StringUtils.randEMailID() ,telephone,password, subscribe));
	}

}
