package api;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_10_Alert {
	WebDriver driver;
	WebDriverWait expliciWait;
	Alert alert;
	
	@BeforeClass
	public void beforeClass() {
		driver = new FirefoxDriver();
		
		expliciWait = new WebDriverWait(driver, 10);
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		driver.get("http://demo.guru99.com/v4/");
	}

	@Test
	public void TC_01_Accept_Alert() {

		driver.get("https://automationfc.github.io/basic-form/index.html");
		
		driver.findElement(By.xpath("//button[text()='Click for JS Alert']")).click();
		
		alert = expliciWait.until(ExpectedConditions.alertIsPresent());
		
		Assert.assertEquals(alert.getText(), "I am a JS Alert");
		
		sleepInSecond(5);
		
		alert.accept();
		
		Assert.assertEquals(driver.findElement(By.id("result")).getText(), "You clicked an alert successfully");

		//Accept
		
		//Cancel
		
		//Input Value
		
		//Get Title
	}
	
	@Test
	public void TC_02_Confirm_Alert() {
		
		driver.get("https://automationfc.github.io/basic-form/index.html");
		
		driver.findElement(By.xpath("//button[text()='Click for JS Confirm']")).click();
		
		alert = expliciWait.until(ExpectedConditions.alertIsPresent());
		
		Assert.assertEquals(alert.getText(), "I am a JS Confirm");
		
		sleepInSecond(5);
		
		alert.dismiss();
		
		Assert.assertEquals(driver.findElement(By.id("result")).getText(), "You clicked: Cancel");
		
	}
	
	@Test
	public void TC_03_Prompt_Alert() {
		
		String alertText = "Automation Test 2021";
		
		driver.get("https://automationfc.github.io/basic-form/index.html");
		
		driver.findElement(By.xpath("//button[text()='Click for JS Prompt']")).click();
		
		alert = expliciWait.until(ExpectedConditions.alertIsPresent());
		
		Assert.assertEquals(alert.getText(), "I am a JS prompt");
		
		sleepInSecond(2);
		
		alert.sendKeys(alertText);
		
		sleepInSecond(2);
		
		alert.accept();
		
		Assert.assertEquals(driver.findElement(By.id("result")).getText(), "You entered: " + alertText);
		
	}
	
	@Test
	public void TC_04_Authentication_Alert() {

		// hrrp://username:password@
		
		driver.get("http://admin:admin@the-internet.herokuapp.com/basic_auth");
		
		Assert.assertTrue(driver.findElement(By.xpath("//p[contains(text(),'Congratulations! You must have the proper credentials.')]")).isDisplayed());
		
	}
	
	@Test
	public void TC_05_Authentication_Alert() {
		
		driver.get("http://the-internet.herokuapp.com/");

		String url = driver.findElement(By.xpath("//a[text()='Basic Auth']")).getAttribute("href");
		
		driver.get(getCredentialToUrl(url, "admin", "admin"));
		
		Assert.assertTrue(driver.findElement(By.xpath("//p[contains(text(),'Congratulations! You must have the proper credentials.')]")).isDisplayed());
	}
	
	
	public void sleepInSecond(long second) {
		try {
			Thread.sleep(second * 1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public String getCredentialToUrl(String url, String username, String password) {
		
		 String[] newUrl = url.split("//");
		 
		 url = newUrl[0] + "//" + username + ":" + password + "@" + newUrl[1];
		 
		 return url;
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}

}