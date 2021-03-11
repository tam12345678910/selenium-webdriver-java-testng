package api;

import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_14_Tab_Window {
	WebDriver driver;
	WebDriverWait expliciWait;
	Actions action;
	
	@BeforeClass
	public void beforeClass() {
		driver = new FirefoxDriver();
		expliciWait = new WebDriverWait(driver, 10);
		action  = new Actions(driver);
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}

	// @Test
	public void TC_01_2_Window_Tabs() {
		driver.get("https://automationfc.github.io/basic-form/index.html");

		// Moi 1 tab / window se co 1 Id dai dien

		// Lay ra cai ID cua tab/ window hien tai dang active
		String parentWindowID = driver.getWindowHandle();

		driver.findElement(By.xpath("//a[text()='GOOGLE']")).click();

		sleepInSecond(3);

		switchToWindowByID(parentWindowID);

		Assert.assertTrue(driver.findElement(By.xpath("//img[@id='hplogo']")).isDisplayed());
	}

	//@Test
	public void TC_02_Greater_Than_2_Windows_Tab() {
		driver.get("https://automationfc.github.io/basic-form/index.html");

		// Lay ra cai Id cua tao truoc khi click
		String parentWindowID = driver.getWindowHandle();

		driver.findElement(By.xpath("//a[text()='GOOGLE']")).click();
		sleepInSecond(3);

		swithToWindowByTitle("Google");
		sleepInSecond(2);

		Assert.assertTrue(driver.findElement(By.xpath("//img[@id='hplogo']")).isDisplayed());

		swithToWindowByTitle("SELENIUM WEBDRIVER FORM DEMO");
		sleepInSecond(2);

		driver.findElement(By.xpath("//a[text()='FACEBOOK']")).click();
		sleepInSecond(2);

		swithToWindowByTitle("Facebook - Đăng nhập hoặc đăng ký");
		sleepInSecond(2);
		Assert.assertTrue(driver.findElement(By.xpath("//button[@name='login']")).isDisplayed());

		swithToWindowByTitle("SELENIUM WEBDRIVER FORM DEMO");
		sleepInSecond(2);

		driver.findElement(By.xpath("//a[text()='LAZADA']")).click();
		sleepInSecond(2);

		swithToWindowByTitle("Shopping online - Buy online on Lazada.vn ");
		sleepInSecond(2);

		Assert.assertTrue(driver.findElement(By.xpath("//input[@id='q']")).isDisplayed());

		// Quay tro lai trang dau tien (parent) - close het tat ca cac tab con lai
		closeAllWindowExceptParent(parentWindowID);
	}
	
	@Test
	public void TC_03_Compare_Product() {
		driver.get("http://live.demoguru99.com/");
		
		driver.findElement(By.xpath("//a[text()='Mobile']")).click();
		
		String parentID = driver.getWindowHandle();
		
		driver.findElement(By.xpath("//a[text()='IPhone']/parent::h2/following-sibling::div[@class='actions']//a[text()='Add to Compare']")).click();
		
		Assert.assertEquals(driver.findElement(By.xpath("//span[text()='The product IPhone has been added to comparison list.']")).getText(), "The product IPhone has been added to comparison list.");
		
		driver.findElement(By.xpath("//a[text()='Samsung Galaxy']/parent::h2/following-sibling::div[@class='actions']//a[text()='Add to Compare']")).click();
		
		Assert.assertEquals(driver.findElement(By.xpath("//span[text()='The product Samsung Galaxy has been added to comparison list.']")).getText(), "The product Samsung Galaxy has been added to comparison list.");
		
		driver.findElement(By.xpath("//button[@title='Compare']")).click();
		sleepInSecond(2);
		
		swithToWindowByTitle("Products Comparison List - Magento Commerce");
		
		Assert.assertEquals(driver.findElements(By.xpath("//h2[@class='product-name']/a")).size(), 2);
		
		closeAllWindowExceptParent(parentID);
		sleepInSecond(5);
		
		driver.findElement(By.xpath("//a[text()='Clear All']")).click();
		
		expliciWait.until(ExpectedConditions.alertIsPresent());
		driver.switchTo().alert().accept();
		
		Assert.assertEquals(driver.findElement(By.xpath("//li[@class='success-msg']//span")).getText(), "The comparison list was cleared.");
		
		driver.findElement(By.xpath("//input[@id='search']")).sendKeys("Sony Xperia");
		driver.findElement(By.xpath("//button[@title='Search']")).click();
		
		sleepInSecond(5);
 	}

	public void sleepInSecond(long second) {
		try {
			Thread.sleep(second * 1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// 2 window/tab
	public void switchToWindowByID(String parentID) {

		Set<String> allWindowsID = driver.getWindowHandles();

		for (String windowID : allWindowsID) {
			if (!windowID.equals(parentID)) {

				// Switch vao 1 window//tab theo ID
				driver.switchTo().window(windowID);
				break;
			}

		}
	}

	// >= 2 window/tab
	public void swithToWindowByTitle(String expectedWindowTitle) {

		// Lay ra tat ca cac window/tab dang co
		Set<String> allWindowIDs = driver.getWindowHandles();

		for (String windowID : allWindowIDs) {
			driver.switchTo().window(windowID);

			String acctualWindowTitle = driver.getTitle();

			if (acctualWindowTitle.equals(expectedWindowTitle)) {
				break;

			}
		}
	}

	public void closeAllWindowExceptParent(String parentID) {

		// Lay ra tat ca cac ID cua window/ tab dang co
		Set<String> allWindowsID = driver.getWindowHandles();

		for (String windowID : allWindowsID) {
			if (!windowID.equals(parentID)) {
				driver.switchTo().window(windowID);
				// chi dong tab/ window dang active (driver)
				driver.close();
				sleepInSecond(1);
			}

			if (driver.getWindowHandles().size() == 1) {
				driver.switchTo().window(parentID);
				break;
			}
		}
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}

}