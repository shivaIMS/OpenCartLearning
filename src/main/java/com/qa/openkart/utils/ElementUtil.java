package com.qa.openkart.utils;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.qa.openkart.exceptions.ElementException;
import com.qa.openkart.factory.DriverFactory;

public class ElementUtil {
	private WebDriver driver;
	private JavaScriptUtil jsUtil;

	public ElementUtil(WebDriver driver) {
		this.driver = driver;
		jsUtil = new JavaScriptUtil(driver);
	}

	
	private void checkHighlight(WebElement element) {
		if (Boolean.parseBoolean(DriverFactory.highlight)) {
			jsUtil.flash(element);
		}
	}
	public WebElement getElement(By locator) {
		WebElement element = null;
		try {
			element=driver.findElement(locator);
			checkHighlight(element);
		} catch (NoSuchElementException e) {
			e.printStackTrace();
		}
		return driver.findElement(locator);

	}

	public boolean isElementDisplayed(By locator) {
		return getElement(locator).isDisplayed();
	}

	public void doSendKeys(By locator, String value) {
		getElement(locator).clear();
		getElement(locator).sendKeys(value);
	}

	public List<WebElement> getElements(By locator) {
		return driver.findElements(locator);
	}

	public int getElementsSize(By locator) {
		return getElements(locator).size();
	}

	public boolean isElementExist(By locator) {
		if (getElements(locator).size() == 1) {
			return true;
		}
		return false;
	}

	public boolean isElementExistCount(By locator) {
		if (getElements(locator).size() > 0) {
			return true;
		}
		return false;
	}

	public List<String> getElemsTextList(By locator) {
		List<WebElement> eleList = getElements(locator);
		ArrayList<String> textList = new ArrayList<String>();
		for (WebElement txt : eleList) {
			String text = txt.getText();
			if (text.length() != 0) {
				textList.add(text);
			}
		}
		return textList;
	}

	public ArrayList<String> getElementAttributeList(By locator, String attrName) {
		List<WebElement> eleList = getElements(locator);
		ArrayList<String> eleAttrList = new ArrayList<String>();// pc=0

		for (WebElement e : eleList) {
			String attrValue = e.getAttribute(attrName);

			if (attrValue.length() != 0) {
				eleAttrList.add(attrValue);
			}
		}

		return eleAttrList;

	}

	public List<String> getElementsTextList(By locator) {
		System.out.println("Inside a getElementsTextList method ");
		List<WebElement> listOfImages = getElements(locator);
		ArrayList<String> eleTextList = new ArrayList<String>();// pc=0

		for (WebElement e : listOfImages) {
			String text = e.getText();
			if (text.length() != 0) {
				eleTextList.add(text);
			}
		}

		return eleTextList;
	}

	/***************** select dropdown utils ********************************/

	public void clickWhenReady(By locator, int timeout) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
		WebElement element =  wait.until(ExpectedConditions.elementToBeClickable(locator));
		checkHighlight(element);
		element.click();
	}

	/***
	 * An expectation for checking that all elements present on the web page that
	 * match the locatorare visible. Visibility means that the elements are not only
	 * displayed but also have a heightand width that is greater than 0.
	 * 
	 * @param locator
	 * @param timeout
	 * @return
	 */
	public List<WebElement> waitForAllElementsVisible(By locator, int timeout) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
		return wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(locator));
	}

	/***
	 * An expectation for checking that there is at least one element present on a
	 * web page.
	 * 
	 * @param locator
	 * @param timeout
	 * @return
	 */
	public List<WebElement> waitForListOfElements(By locator, int timeout) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
		return wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(locator));
	}

	public void doSelectIndex(By locator, int index) {

		Select select = new Select(getElement(locator));
		select.selectByIndex(index);

	}

	public void doSelectByVisibleText(By locator, String visibleText) {
		nullBlankCheck(visibleText);
		Select select = new Select(getElement(locator));
		select.selectByVisibleText(visibleText);

	}

	public void doSelectByValue(By locator, String value) {
		nullBlankCheck(value);
		Select select = new Select(getElement(locator));
		select.selectByValue(value);

	}

	private void nullBlankCheck(String value) {
		if (value == null || value.length() == 0) {
			throw new ElementException("Visible text canot be null");
		}
	}

	public void doClick(By locator) {
		getElement(locator).click();
	}

	public void doClick(By locator, int timeOut) {
		waitForElementVisible(locator, timeOut).click();
	}

	public String waitForURLContains(String urlFraction, int timeOut) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));

		try {
			if (wait.until(ExpectedConditions.urlContains(urlFraction))) {
				return driver.getCurrentUrl();
			}
		} catch (Exception e) {
			System.out.println("url fraction is not found within : " + timeOut);
		}
		return driver.getCurrentUrl();

	}

	public void clickOnCheckBox(String playerName) {
		String xpath = "//a[text()='" + playerName
				+ "']/parent::td/preceding-sibling::td/child::input[@type='checkbox']";
		driver.findElement(By.xpath(xpath)).click();
	}

	public String getCompanyName(String playerName) {
		String xpath = "//a[text()='" + playerName + "']/parent::td/following-sibling::td/child::a[@context='company']";
		String companyis = driver.findElement(By.xpath(xpath)).getText();
		return companyis;
	}

	public String getPhoneNumber(String playerName) {
		String xpath = "(//a[text()='" + playerName + "']/parent::td/following-sibling::td/following-sibling::td)[1]";
		String phoneNumber = driver.findElement(By.xpath(xpath)).getText();
		return phoneNumber;
	}

	public String getPlayerName(String plName) {
		String player = driver.findElement(By.xpath("(//a[@title='" + plName + "'])[1]")).getText();
		return player;
	}

	public List<String> playerInfo(String playerName) {
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		List<WebElement> list = driver
				.findElements(By.xpath("(//a[@title='" + playerName + "'])[1]/ancestor::td/following-sibling::td"));
		List<String> textList = new ArrayList<String>();
		for (WebElement elements : list) {
			String ele = elements.getText();
			textList.add(ele);
		}
		return textList;
	}

	/***
	 * An expectation for checking that an element is present on the DOM of a page.
	 * This does notnecessarily mean that the element is visible.
	 * 
	 * @param locator
	 * @param time
	 * @return
	 */

	public WebElement waitForElementPresence(By locator, int time) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(time));
		WebElement element =  wait.until(ExpectedConditions.presenceOfElementLocated(locator));
		checkHighlight(element);
		return element;
	}

	/***
	 * An expectation for checking that an element is present on the DOM of a page
	 * and visible.Visibility means that the element is not only displayed but also
	 * has a height and width that isgreater than 0.
	 * 
	 * @param locator
	 * @param time
	 * @return
	 */

	public WebElement waitForElementVisible(By locator, int time) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(time));
		WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
		checkHighlight(element);
		return element;
	}

	public String waitForTitleContains(String titleFraction, int timeout) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		try {
			if (wait.until(ExpectedConditions.titleContains(titleFraction))) {
				return driver.getTitle();
			}
		} catch (Exception e) {
			System.out.println("Title is not found within " + timeout);
		}
		return null;
	}

	public String waitForTitleIs(String title, int timeout) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
		try {
			if (wait.until(ExpectedConditions.titleContains(title))) {
				return driver.getTitle();
			}
		} catch (Exception e) {
			System.out.println("Complete Title is not found within " + timeout);
		}
		return null;
	}

	public String waitForCompleteUrl(String signUp, int timeout) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
		try {
			if (wait.until(ExpectedConditions.urlContains(signUp))) {
				return driver.getCurrentUrl();
			}
		} catch (Exception e) {
			System.out.println("Current Fraction URL is not found within " + timeout);
		}
		return null;
	}

	public String waitForPartialUrl(String signUp, int timeout) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
		try {
			if (wait.until(ExpectedConditions.urlContains(signUp))) {
				return driver.getCurrentUrl();
			}
		} catch (Exception e) {
			System.out.println("Current Fraction URL is not found within " + timeout);
		}
		return null;
	}

	public Alert waitForJsAlert(int timeout) {
		System.out.println("Inside waitForJsAlert");
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
		return wait.until(ExpectedConditions.alertIsPresent());
	}

	public String getJsAlertText(int timeout) {
		System.out.println("Inside getJsAlertText");
		return waitForJsAlert(timeout).getText();
	}

	public void acceptJsAlert(int timeout) {
		System.out.println("Inside acceptJsAlert");
		waitForJsAlert(timeout).accept();
	}

	public void dismissAlert(int timeout) {
		System.out.println("Inside dismissAlert");
		waitForJsAlert(timeout).dismiss();
	}

	public String doGetElementText(By locator) {
		return getElement(locator).getText();
	}

}
