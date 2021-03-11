package api;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;


public class Topic_15_Javascript_Executor_Part_I {
	WebDriver driver;
	JavascriptExecutor jsExecutor;
	WebDriverWait explicitWait;
	String html5Message;

	@BeforeClass
	public void beforeClass() {
		driver = new FirefoxDriver();
		jsExecutor = (JavascriptExecutor) driver;
		explicitWait = new WebDriverWait(driver, 10);
		
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}

	//@Test
	public void TC_01_Browser_Element() {
		navigateToUrlByJS(driver,"http://live.demoguru99.com/");
		
		String liveGuruDomain = (String) executeForBrowser("return document.domain;");
		Assert.assertEquals(liveGuruDomain, "live.demoguru99.com");
		
		
		String homePageURL = (String) executeForBrowser("return document.URL;");
		Assert.assertEquals(homePageURL, "http://live.demoguru99.com/");
		
		highlightElement("//a[text()='Mobile']");
		clickToElementByJS("//a[text()='Mobile']");
		
		highlightElement("//a[text()='Samsung Galaxy']/parent::h2/following-sibling::div[@class='actions']/button");
		clickToElementByJS("//a[text()='Samsung Galaxy']/parent::h2/following-sibling::div[@class='actions']/button");
		
		Assert.assertTrue(getInnerText().contains("Samsung Galaxy was added to your shopping cart."));
		
		Assert.assertTrue(areJQueryAndJSLoadedSuccess("Samsung Galaxy was added to your shopping cart."));
		
		highlightElement("//a[text()='Customer Service']");
		clickToElementByJS("//a[text()='Customer Service']");
		
		String customerService = (String) executeForBrowser("return document.title");
		
		Assert.assertEquals(customerService, "Customer Service");
		
		highlightElement("//input[@id='newsletter']");
		scrollToElement("//input[@id='newsletter']");
		sleepInSecond(3);
		
		sendkeyToElementByJS("//input[@id='newsletter']", generateEmail());
		
		highlightElement("//button[@title='Subscribe']");
		clickToElementByJS("//button[@title='Subscribe']");
		
		Assert.assertTrue(getInnerText().contains("Thank you for your subscription"));
		
		navigateToUrlByJS(driver,"http://demo.guru99.com/v4/");
		
		Assert.assertEquals(executeForBrowser("return document.domain;"), "demo.guru99.com");
	}
	
	//@Test
	public void TC_02_HTML5_Validation_Message() {
		driver.get("https://automationfc.github.io/html5/index.html");
		
		driver.findElement(By.name("submit-btn")).click();
		sleepInSecond(2);
		
		html5Message = getElementValidationMessage("//input[@id='fname']");
		Assert.assertEquals(html5Message, "Please fill out this field.");
		
		driver.findElement(By.xpath("//input[@id='fname']")).sendKeys("Duong Tam");
		driver.findElement(By.name("submit-btn")).click();
		sleepInSecond(2);
		
		html5Message = getElementValidationMessage("//input[@id='pass']");
		Assert.assertEquals(html5Message, "Please fill out this field.");
		
		driver.findElement(By.xpath("//input[@id='pass']")).sendKeys("123456");
		sleepInSecond(2);
		
		driver.findElement(By.name("submit-btn")).click();
		sleepInSecond(2);
		
		html5Message = getElementValidationMessage("//input[@id='em']");
		Assert.assertEquals(html5Message, "Please fill out this field.");
		
		driver.findElement(By.xpath("//input[@id='em']")).clear();
		driver.findElement(By.xpath("//input[@id='em']")).sendKeys("123@456@789");
		sleepInSecond(2);
		
		driver.findElement(By.name("submit-btn")).click();
		sleepInSecond(2);
		
		html5Message = getElementValidationMessage("//input[@id='em']");
		Assert.assertEquals(html5Message,  "Please enter an email address.");
		
		driver.findElement(By.xpath("//input[@id='em']")).clear();
		driver.findElement(By.xpath("//input[@id='em']")).sendKeys("auto@12345");
		sleepInSecond(2);
		driver.findElement(By.name("submit-btn")).click();
		sleepInSecond(2);
		
		html5Message = getElementValidationMessage("//input[@id='em']");
		Assert.assertEquals(html5Message, "Please match the requested format.");
		
		driver.findElement(By.xpath("//input[@id='em']")).clear();
		driver.findElement(By.xpath("//input[@id='em']")).sendKeys("tam@gmail.com");
		driver.findElement(By.name("submit-btn")).click();
		sleepInSecond(2);
		
		html5Message = getElementValidationMessage("//select");
		Assert.assertEquals(html5Message, "Please select an item in the list.");
	}
	
	@Test
	public void TC_03_Hidden_Element() {
		driver.get("http://live.demoguru99.com/");
		
		clickToElementByJS("//div[@id='header-account']//a[@title='My Account']");
		sleepInSecond(5);
	}

	public Object executeForBrowser(String javaScript) {
		jsExecutor = (JavascriptExecutor) driver;
		return jsExecutor.executeScript(javaScript);
	}

	public String getInnerText() {
		jsExecutor = (JavascriptExecutor) driver;
		return (String) jsExecutor.executeScript("return document.documentElement.innerText;");
	}

	public boolean areExpectedTextInInnerText(WebDriver driver, String textExpected) {
		jsExecutor = (JavascriptExecutor) driver;
		String textActual = (String) jsExecutor.executeScript("return document.documentElement.innerText.match('" + textExpected + "')[0]");
		return textActual.equals(textExpected);
	}

	public void scrollToBottomPage(WebDriver driver) {
		jsExecutor = (JavascriptExecutor) driver;
		jsExecutor.executeScript("window.scrollBy(0,document.body.scrollHeight)");
	}

	public void navigateToUrlByJS(WebDriver driver, String url) {
		jsExecutor = (JavascriptExecutor) driver;
		jsExecutor.executeScript("window.location = '" + url + "'");
	}

	public void highlightElement(String locator) {
		jsExecutor = (JavascriptExecutor) driver;
		WebElement element = getElement(locator);
		String originalStyle = element.getAttribute("style");
		jsExecutor.executeScript("arguments[0].setAttribute(arguments[1], arguments[2])", element, "style", "border: 2px solid red; border-style: dashed;");
		sleepInSecond(1);
		jsExecutor.executeScript("arguments[0].setAttribute(arguments[1], arguments[2])", element, "style", originalStyle);
	}

	public void clickToElementByJS(String locator) {
		jsExecutor = (JavascriptExecutor) driver;
		jsExecutor.executeScript("arguments[0].click();", getElement(locator));
	}

	public void scrollToElement(String locator) {
		jsExecutor = (JavascriptExecutor) driver;
		jsExecutor.executeScript("arguments[0].scrollIntoView(true);", getElement(locator));
	}

	public void sendkeyToElementByJS(String locator, String value) {
		jsExecutor = (JavascriptExecutor) driver;
		jsExecutor.executeScript("arguments[0].setAttribute('value', '" + value + "')", getElement(locator));
	}

	public void removeAttributeInDOM(WebDriver driver, String locator, String attributeRemove) {
		jsExecutor = (JavascriptExecutor) driver;
		jsExecutor.executeScript("arguments[0].removeAttribute('" + attributeRemove + "');", getElement(locator));
	}

	public boolean areJQueryAndJSLoadedSuccess(String textExpected) {
		explicitWait = new WebDriverWait(driver, 30);
		jsExecutor = (JavascriptExecutor) driver;

		ExpectedCondition<Boolean> jQueryLoad = new ExpectedCondition<Boolean>() {
			@Override
			public Boolean apply(WebDriver driver) {
				try {
					return ((Long) jsExecutor.executeScript("return jQuery.active") == 0);
				} catch (Exception e) {
					return true;
				}
			}
		};

		ExpectedCondition<Boolean> jsLoad = new ExpectedCondition<Boolean>() {
			@Override
			public Boolean apply(WebDriver driver) {
				return jsExecutor.executeScript("return document.readyState").toString().equals("complete");
			}
		};

		return explicitWait.until(jQueryLoad) && explicitWait.until(jsLoad);
	}

	public String getElementValidationMessage(WebDriver driver, String locator) {
		jsExecutor = (JavascriptExecutor) driver;
		return (String) jsExecutor.executeScript("return arguments[0].validationMessage;", getElement(locator));
	}

	public boolean isImageLoaded(WebDriver driver, String locator) {
		jsExecutor = (JavascriptExecutor) driver;
		boolean status = (boolean) jsExecutor.executeScript("return arguments[0].complete && typeof arguments[0].naturalWidth != \"undefined\" && arguments[0].naturalWidth > 0", getElement(locator));
		if (status) {
			return true;
		} else {
			return false;
		}
	}
	
	public WebElement getElement(String xpathLocator) {
		return driver.findElement(By.xpath(xpathLocator));
	}
	
	public void sleepInSecond(long second) {
		try {
			Thread.sleep(second * 1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public String generateEmail() {
		Random rand = new Random();
		return "testing"  + rand.nextInt(9999) + "@github.io";
	}
	
	public String getElementValidationMessage(String locator) {
		jsExecutor = (JavascriptExecutor) driver;
		WebElement element = getElement(locator);
		return (String) jsExecutor.executeScript("return arguments[0].validationMessage", element); 
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}

}