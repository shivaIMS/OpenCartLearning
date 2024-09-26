package com.qa.openkart.factory;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.apache.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.safari.SafariDriver;

import com.qa.openkart.Logger.Log;
import com.qa.openkart.errors.AppError;
import com.qa.openkart.exceptions.BrowserException;
import com.qa.openkart.exceptions.FrameworkException;

public class DriverFactory {
	WebDriver driver;

	Properties prop;
	OptionsManager optionsManager;
	public static String highlight;

	public static ThreadLocal<WebDriver> t1Driver = new ThreadLocal<WebDriver>();

	public WebDriver initDriver(Properties prop) {
		String browserName = prop.getProperty("browser");
		highlight = prop.getProperty("highlight");
		// System.out.println("browser name is : " + browserName);
		Log.info("Browser name is : " + browserName);
		Log.error("==============CHECK TEST METHOD==================");
		Log.info("++++++++++++ BrowserName ++++++++++++");
		Log.info(browserName);
		optionsManager = new OptionsManager(prop);
		switch (browserName.trim().toLowerCase()) {
		case "chrome":
			// driver = new ChromeDriver(optionsManager.getChromeOptions());
			t1Driver.set(new ChromeDriver(optionsManager.getChromeOptions()));
			break;
		case "firefox":
			// driver = new FirefoxDriver(optionsManager.getFirefoxOptions());
			t1Driver.set(new FirefoxDriver(optionsManager.getFirefoxOptions()));
			break;
		case "edge":
			// driver = new EdgeDriver(optionsManager.getEdgeOptions());
			t1Driver.set(new EdgeDriver(optionsManager.getEdgeOptions()));
			break;
		case "safari":
			driver = new SafariDriver();
			break;
		default:
			// System.out.println("Please pass the right browser...!!!" + browserName);
			Log.info("++++++++++++Please pass the right browser...!!!++++++++" + browserName);
			throw new BrowserException("NO BROWSER FOUND " + browserName);
		}
		getDriver().manage().deleteAllCookies();
		getDriver().manage().window().maximize();
		getDriver().get(prop.getProperty("url"));
		// driver.get("https://naveenautomationlabs.com/opencart/index.php?route=account/login");
		return getDriver();
	}

	public static WebDriver getDriver() {
		return t1Driver.get();
	}

	public Properties initProp() throws FileNotFoundException {
		FileInputStream ip = null;
		prop = new Properties();
		String envName = System.getProperty("env");
		try {
			if (envName == null) {
				System.out.println("No environment is given hence running it on QA env" + envName);
				ip = new FileInputStream("./src/test/resources/config/config.qa.properties");
			} else {
				switch (envName.toLowerCase().trim()) {
				case "qa":
					ip = new FileInputStream("./src/test/resources/config/config.qa.properties");
					break;
				case "dev":
					ip = new FileInputStream("./src/test/resources/config/config.dev.properties");
					break;
				case "stage":
					ip = new FileInputStream("./src/test/resources/config/config.stage.properties");
					break;
				case "uat":
					ip = new FileInputStream("./src/test/resources/config/config.uat.properties");
					break;
				case "prod":
					ip = new FileInputStream("./src/test/resources/config/config.properties");
					break;

				default:
					System.out.println("Plz enter correct env " + envName);
					Log.info("==========Plz enter the correct env==========" + envName);
					throw new FrameworkException(AppError.ENV_NOT_FOUND);
				}
			}
		} catch (FileNotFoundException e) {
			// TODO: handle exception
			e.printStackTrace();
		}

		try {
			prop.load(ip);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return prop;
	}

	public static String getScreenshot(String methodName) {
		File srcFile = ((TakesScreenshot) getDriver()).getScreenshotAs(OutputType.FILE);// temp directory
		String path = System.getProperty("user.dir") + "/screenshot/" + methodName + "_" + System.currentTimeMillis()
				+ ".png";

		File destination = new File(path);

		try {
			FileHandler.copy(srcFile, destination);
		} catch (IOException e) {
			e.printStackTrace();
		}

		return path;
	}
}
