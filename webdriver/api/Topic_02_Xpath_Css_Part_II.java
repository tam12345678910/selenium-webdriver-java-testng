package api;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_02_Xpath_Css_Part_II {
	WebDriver driver;

	@BeforeClass
	public void beforeClass() {
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize(); 
		driver.get("http://live.demoguru99.com/index.php/customer/account/login/");
	}
	
	@Test
	public void TC_01_ID() throws InterruptedException {
		driver.findElement(By.id("email")).sendKeys("automationtest@gmail.com");
		driver.findElement(By.id("pass")).sendKeys("123456");
		
		Thread.sleep(3000);
	}
	@Test
	public void TC_02_Class() throws InterruptedException {
		driver.navigate().refresh();
		
		driver.findElement(By.className("validate-password")).sendKeys("123456");
		
		driver.findElement(By.className("account-login")).isDisplayed();
		
		Thread.sleep(3000);
	}
	
	@Test
	public void TC_03_name() throws InterruptedException {
		driver.findElement(By.name("login[username]")).sendKeys("");
		
		Thread.sleep(3000);
	}
	
	@Test
	public void TC_04_Tagname() throws InterruptedException {
		System.out.println("Tong so link tren trang hien tai = " + driver.findElements(By.tagName("a")).size());
	}
	
	@Test
	public void TC_05_LinkText() throws InterruptedException {
		driver.get("https://login.ubuntu.com/");
		
		// Text cua link tuyet doi (toan bo chuoi)
		driver.findElement(By.linkText("Read More ›")).click();
		Thread.sleep(4000);	
	}
	
	@Test
	public void TC_06_Partial_LinkText() {
		driver.get("https://login.ubuntu.com/");
		
		// Text cua link tuong doi (1 phan chuoi)
		driver.findElement(By.partialLinkText("Read")).click();
	}
	@Test
	public void TC_07_Css_Selector() throws InterruptedException {
		driver.get("https://login.ubuntu.com/");
		
		driver.findElement(By.cssSelector("input[id='id_email']")).sendKeys("automation@gmail.com");
		sleepInSecond(2);
		driver.findElement(By.cssSelector("input[id='id_email']")).clear();
		
		driver.findElement(By.cssSelector("input[placeholder='Your email address']")).sendKeys("testing@gmai.com");
		Thread.sleep(2000);
		driver.findElement(By.cssSelector("input[placeholder='Your email address']")).clear();
		
		driver.findElement(By.cssSelector("input[name='email']")).sendKeys("testing@gmai.com");
		Thread.sleep(2000);
		driver.findElement(By.cssSelector("input[name='email']")).clear();
	}
	
	@Test
	public void TC_08_Xpath() {
		driver.get("https://login.ubuntu.com/");
		
		driver.findElement(By.xpath("//form[@id='login-form']//input[]@id='id_email'")).sendKeys("test@gmail.com");
		sleepInSecond(3);
		driver.findElement(By.xpath("//form[@id='login-form']//input[]@id='id_email'")).clear();
	}
	
	public void sleepInSecond(long time) {
		try {
			Thread.sleep(time * 1000);
		}catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}

}