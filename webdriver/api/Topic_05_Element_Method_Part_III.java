 package api;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_05_Element_Method_Part_III {
	WebDriver driver;

	
	@BeforeClass
	public void beforeClass() {
		
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}
	
	@Test
	public void TC_01_Displayed() throws InterruptedException {
		
		driver.get("https://login.mailchimp.com/signup/");
		
		driver.findElement(By.id("email")).sendKeys("testing@gmail.com");
		
		driver.findElement(By.id("new_username")).sendKeys("duong vinh tam");
		
		driver.findElement(By.id("new_password")).sendKeys("auto");
		
		Thread.sleep(1000);
		
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class='lowercase-char completed' and text()='One lowercase character']")).isDisplayed());
		Assert.assertFalse(driver.findElement(By.id("create-account")).isEnabled());
		
		driver.findElement(By.id("new_password")).clear();
		
		driver.findElement(By.id("new_password")).sendKeys("Auto");
		
		Thread.sleep(1000);
		
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class='uppercase-char completed' and text()='One uppercase character']")).isDisplayed());
		Assert.assertFalse(driver.findElement(By.id("create-account")).isEnabled());
		
		driver.findElement(By.id("new_password")).clear();
		
		driver.findElement(By.id("new_password")).sendKeys("123456");
		
		Thread.sleep(1000);
		
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class='number-char completed' and text()='One number']")).isDisplayed());
		Assert.assertFalse(driver.findElement(By.id("create-account")).isEnabled());
		
		
		driver.findElement(By.id("new_password")).clear();
		
		driver.findElement(By.id("new_password")).sendKeys("!$$%%%%");
		
		Thread.sleep(1000);
		
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class='special-char completed' and text()='One special character']")).isDisplayed());
		Assert.assertFalse(driver.findElement(By.id("create-account")).isEnabled());
		
		driver.findElement(By.id("new_password")).clear();
		
		driver.findElement(By.id("new_password")).sendKeys("Automation");
		
		Thread.sleep(1000);
		
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class='8-char completed' and text()='8 characters minimum']")).isDisplayed());
		Assert.assertFalse(driver.findElement(By.id("create-account")).isEnabled());
		
		driver.findElement(By.id("new_password")).clear();
		
		driver.findElement(By.id("new_password")).sendKeys("Auto123!!!");
		Thread.sleep(1000);
		
		Assert.assertTrue(driver.findElement(By.id("create-account")).isEnabled());
	}

	
	
	@AfterClass
	public void afterClass() {
		driver.quit();
	}
}
