package com.qa.openkart.factory;

import java.util.Properties;

import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;

import com.qa.openkart.Logger.Log;

public class OptionsManager {
	private Properties prop;
	private ChromeOptions co;
	private FirefoxOptions fo;
	private EdgeOptions eo;

	public OptionsManager(Properties prop) {
		this.prop = prop;
	}

	public ChromeOptions getChromeOptions() {
		co = new ChromeOptions();
		if (Boolean.parseBoolean(prop.getProperty("headless"))) {
			System.out.println("Running chrome in headless mode...!!!");
			Log.info("Running chrome in headless mode...!!!");
			co.addArguments("--headless");
		}

		if (Boolean.parseBoolean(prop.getProperty("incognito").trim())) {
			System.out.println("Running chrome in incognito mode...!!!");
			Log.info("Running chrome in incognito mode...!!!");
			co.addArguments("--incognito");
		}

		return co;
	}

	public FirefoxOptions getFirefoxOptions() {
		fo = new FirefoxOptions();
		if (Boolean.parseBoolean(prop.getProperty("headless"))) {
			System.out.println("Running firefox in headless mode...!!!");
			Log.info("Running firefox in headless mode...!!!");
			fo.addArguments("--headless");
		}

		if (Boolean.parseBoolean(prop.getProperty("incognito").trim())) {
			System.out.println("Running firfox in incognito mode...!!!");
			Log.info("Running firfox in incognito mode...!!!");
			fo.addArguments("--incognito");
		}

		return fo;
	}

	public EdgeOptions getEdgeOptions() {
		eo = new EdgeOptions();
		if (Boolean.parseBoolean(prop.getProperty("headless"))) {
			System.out.println("Running edge in headless mode...!!!");
			Log.info("Running edge in headless mode...!!!");
			eo.addArguments("--headless");
		}

		if (Boolean.parseBoolean(prop.getProperty("incognito").trim())) {
			System.out.println("Running edge in incognito mode...!!!");
			Log.info("Running edge in incognito mode...!!!");
			eo.addArguments("--inprivate");
		}

		return eo;
	}
}
