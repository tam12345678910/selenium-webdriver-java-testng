package api;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_02_Xpath_Css_Part_III {
	WebDriver driver;

	@BeforeClass
	public void beforeClass() {
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}

	@Test
	public void TC_01_Login_Empty_Email_And_Passwords() {
		driver.get("http://live.demoguru99.com/index.php/");
		
		driver.findElement(By.xpath("//div[@class='footer']//a[text()='My Account']")).click();
		
		driver.findElement(By.id("email")).sendKeys("");
		driver.findElement(By.name("login[password]")).sendKeys("");
		driver.findElement(By.id("send2")).click();
		
		String errorEmailMessage = driver.findElement(By.id("advice-required-entry-email")).getText();
		String errorPasswordMessage = driver.findElement(By.id("advice-required-entry-pass")).getText();
		
		Assert.assertEquals(errorEmailMessage, "This is a required field.");
		Assert.assertEquals(errorPasswordMessage, "This is a required field.");
	}

	@Test
	public void TC_02_Login_Invalid_Email() {
		
		driver.get("http://live.demoguru99.com/index.php/");
		
		driver.findElement(By.xpath("//div[@class='footer']//a[text()='My Account']")).click();
		
		driver.findElement(By.id("email")).sendKeys("123@345.678");
		driver.findElement(By.name("login[password]")).sendKeys("123123");
		driver.findElement(By.id("send2")).click();
		
		String errorEmailMessage = driver.findElement(By.id("advice-validate-email-email")).getText();
		
		Assert.assertEquals(errorEmailMessage, "Please enter a valid email address. For example johndoe@domain.com.");
	
	}

	@Test
	public void TC_03_Login_Invalid_Password() {
		
		driver.get("http://live.demoguru99.com/index.php/");
		
		driver.findElement(By.xpath("//div[@class='footer']//a[text()='My Account']")).click();
		
		driver.findElement(By.id("email")).sendKeys("tam@gmail.com");
		driver.findElement(By.name("login[password]")).sendKeys("111");
		driver.findElement(By.id("send2")).click();
		
		String errorPasswordMessage = driver.findElement(By.id("advice-validate-password-pass")).getText();
		
		Assert.assertEquals(errorPasswordMessage, "Please enter 6 or more characters without leading or trailing spaces.");

	}
	
	@Test
	public void TC_04_Login_Incorrect_Password() {
		
		driver.get("http://live.demoguru99.com/index.php/");
		
		driver.findElement(By.xpath("//div[@class='footer']//a[text()='My Account']")).click();
		
		driver.findElement(By.id("email")).sendKeys("tam@gmail.com");
		driver.findElement(By.name("login[password]")).sendKeys("123456123456");
		driver.findElement(By.id("send2")).click();
		
		String errorPasswordMessage = driver.findElement(By.xpath("//li[@class='error-msg']//span")).getText();
		
		Assert.assertEquals(errorPasswordMessage, "Invalid login or password.");

	}
	
	@Test
	public void TC_05_Create_New_Account() {
		
		driver.get("http://live.demoguru99.com/index.php/");
		
		driver.findElement(By.xpath("//div[@class='footer']//a[text()='My Account']")).click();
		
		driver.findElement(By.xpath("//a[@class='button']")).click();
		
		driver.findElement(By.id("firstname")).sendKeys("Duong");
		
		driver.findElement(By.id("middlename")).sendKeys("Vinh");
		
		driver.findElement(By.id("lastname")).sendKeys("Tam");
		
		Random randomGenerator = new Random();  
		int randomInt = randomGenerator.nextInt(1000);   
		
		driver.findElement(By.id("email_address")).sendKeys("username"+ randomInt +"@gmail.com");
		
		driver.findElement(By.id("password")).sendKeys("123456");
		
		driver.findElement(By.id("confirmation")).sendKeys("123456");
		
		driver.findElement(By.xpath("//div[@class='buttons-set']//button[@class='button']")).click();
		
		Assert.assertEquals(driver.findElement(By.xpath("//li[@class='success-msg']//span")).getText(), "Thank you for registering with Main Website Store.");
		
		Assert.assertTrue(driver.findElement(By.xpath("//div[@class='box-content']/p")).getText().contains("Duong Vinh Tam"));
		
		Assert.assertTrue(driver.findElement(By.xpath("//div[@class='box-content']/p")).getText().contains("username"+ randomInt +"@gmail.com"));
		
		driver.findElement(By.xpath("//div[@class='account-cart-wrapper']//span[text()='Account']")).click();
		
		driver.findElement(By.xpath("//div[@class='links']/ul//li[@class=' last']/a")).click();
		
		Assert.assertEquals(driver.findElement(By.xpath("//div[@class='main-container col2-right-layout']//h2[contains(text(), \"This is demo site for\")]")).getText(), "THIS IS DEMO SITE FOR   ");
	}
	
	@Test
	public void TC_06_Login_Valid_Email_Password() {
		
		driver.get("http://live.demoguru99.com/");
		
		driver.findElement(By.xpath("//div[@class='footer']//ul//a[text()='My Account']")).click();
		
		driver.findElement(By.id("email")).sendKeys("vinhtam123@gmail.com");
		
		driver.findElement(By.name("login[password]")).sendKeys("123456");
		
		driver.findElement(By.id("send2")).click();
		
		Assert.assertEquals(driver.findElement(By.xpath("//div[@class='page-title']/h1")).getText(), "MY DASHBOARD");
		
		Assert.assertEquals(driver.findElement(By.xpath("//p[@class='hello']/strong")).getText(), "Hello, Duong Vinh Tam!");
		
		Assert.assertTrue(driver.findElement(By.xpath("//div[@class='box-content']/p")).getText().contains("Duong Vinh Tam"));
		
		Assert.assertTrue(driver.findElement(By.xpath("//div[@class='box-content']/p")).getText().contains("vinhtam123@gmail.com"));
		
	}
	
	@AfterClass
	public void afterClass() {
		driver.quit();
	}

}