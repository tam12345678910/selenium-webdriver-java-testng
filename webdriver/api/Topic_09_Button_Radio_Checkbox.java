package api;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_09_Button_Radio_Checkbox {
	WebDriver driver;
	JavascriptExecutor js;
	
	@BeforeClass
	public void beforeClass() {
		driver = new FirefoxDriver();
		js = (JavascriptExecutor) driver;
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}

	@Test
	public void TC_01_Button() {
		driver.get("https://www.fahasa.com/customer/account/create?attemp=1");

		By loginButton = By.cssSelector(".fhs-btn-login");
		
		driver.findElement(By.xpath("//a[text()='Đăng nhập']")).click();
		
		Assert.assertFalse(driver.findElement(loginButton).isEnabled());
		
		// input
		driver.findElement(By.cssSelector("#login_username")).sendKeys("automation@gmai.com");
		driver.findElement(By.cssSelector("#login_password")).sendKeys("automation");
		sleepInSecond(2);
		
		Assert.assertTrue(driver.findElement(loginButton).isEnabled());
		
		driver.navigate().refresh();
		
		driver.findElement(By.xpath("//a[text()='Đăng nhập']")).click();
		
		removeDisableAttributeByJS(loginButton);
		sleepInSecond(2);
		
		driver.findElement(loginButton).click();
		sleepInSecond(2);
		
		Assert.assertEquals(driver.findElement(By.xpath("//label[text()='Số điện thoại/Email']/following-sibling::div[@class='fhs-input-alert']")).getText(), "Thông tin này không thể để trống");
		Assert.assertEquals(driver.findElement(By.xpath("//label[text()='Mật khẩu']/following-sibling::div[@class='fhs-input-alert']")).getText(), "Thông tin này không thể để trống");
		
		
	}
	
	@Test
	public void TC_02_Default_Radio_CheckBox() {
		// Login Page Url matching
		driver.get("https://automationfc.github.io/multiple-fields/");

		driver.findElement(By.xpath("//input[@value='Gallstones']")).click();
		sleepInSecond(1);
		
		Assert.assertTrue(driver.findElement(By.xpath("//input[@value='Gallstones']")).isSelected());
		
		//de-select
		driver.findElement(By.xpath("//input[@value='Gallstones']")).click();
		sleepInSecond(1);
		
		Assert.assertFalse(driver.findElement(By.xpath("//input[@value='Gallstones']")).isSelected());
		
		//select radio
		driver.findElement(By.xpath("//input[@value='I have a loose diet']")).click(); 
		
		
		Assert.assertTrue(driver.findElement(By.xpath("//input[@value='I have a loose diet']")).isSelected());
	}
	
	

	@Test
	public void TC_03_Custom_Radio_Checkbox_I() {
		// Login Page title
		
		driver.get("https://material.angular.io/components/radio/examples");
		
		clickByJs(By.xpath("//input[@value='Spring']"));
		sleepInSecond(2);
		Assert.assertTrue(driver.findElement(By.xpath("//input[@value='Spring']")).isSelected());
	}

	@Test
	public void TC_03_Custom_Radio_Checkbox_II() {
		// Login Page title
		
		driver.get("https://docs.google.com/forms/d/e/1FAIpQLSfiypnd69zhuDkjKgqvpID9kwO29UCzeCVrGGtbNPZXQok0jA/viewform");
		
		//Before click
		Assert.assertTrue(driver.findElement(By.xpath("//div[@aria-label='Cần Thơ' and @aria-checked='false']")).isDisplayed());
		
		driver.findElement(By.xpath("//div[@aria-label='Cần Thơ']")).click();
		sleepInSecond(3);
		
		// After Click
		Assert.assertTrue(driver.findElement(By.xpath("//div[@aria-label='Cần Thơ' and @aria-checked='true']")).isDisplayed());
	}
	
	public void removeDisableAttributeByJS(By by) {
		WebElement element = driver.findElement(by);
		js.executeScript("arguments[0].removeAttribute('disabled')", element);
	}
	
	public void sleepInSecond(long second) {
		try {
			Thread.sleep(second * 1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void clickByJs(By by) {
		WebElement element = driver.findElement(by);
		js.executeScript("arguments[0].click();", element);
		
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}

}