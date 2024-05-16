package com.qa.openkart.base;

import java.io.FileNotFoundException;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.asserts.SoftAssert;

import com.qa.openakrt.pages.AccountPage;
import com.qa.openakrt.pages.LoginPage;
import com.qa.openakrt.pages.ProductInfoPage;
import com.qa.openakrt.pages.RegistrationPage;
import com.qa.openakrt.pages.SearchResultsPage;
import com.qa.openkart.factory.DriverFactory;
import com.qa.openkart.utils.ElementUtil;

public class BaseTest {

	WebDriver driver;
	DriverFactory df;
	protected Properties prop;
	protected LoginPage loginPage;
	protected ElementUtil elementUtil;
	protected AccountPage accPage;
	protected SearchResultsPage searchResultPage;
	protected ProductInfoPage producInfoPage;
	protected SoftAssert softAssert;
	protected RegistrationPage registrationPage;

	@Parameters({"browser"})
	@BeforeTest
	public void setup(String browserName) {
		df = new DriverFactory();
		try {
			prop = df.initProp();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		if(browserName!=null) {
			prop.setProperty("browser", browserName);
		}
		
		driver = df.initDriver(prop);
		loginPage = new LoginPage(driver);
		softAssert = new SoftAssert();
	}

	@AfterTest
	public void tearDown() {
		driver.quit();
	}
}
