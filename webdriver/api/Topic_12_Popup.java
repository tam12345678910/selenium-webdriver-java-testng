package api;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_12_Popup {
	WebDriver driver;
	Actions action;
	JavascriptExecutor jsExecutor;
	WebDriverWait expliciWait;
	
	@BeforeClass
	public void beforeClass() {
		driver = new FirefoxDriver();
		expliciWait = new WebDriverWait(driver, 10);
		action  = new Actions(driver);
		
		jsExecutor = (JavascriptExecutor) driver;
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		//driver.manage().window().maximize();
	}

	@Test
	public void TC_01_Right_Click() {
		driver.get("https://www.zingpoll.com/");
		
		driver.findElement(By.id("Loginform")).click();
		
		
		// cho 1 element duoc hien thi
		expliciWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//form[@id='loginForm']")));
		Assert.assertTrue(driver.findElement(By.xpath("//form[@id='loginForm']")).isDisplayed());
		
		driver.findElement(By.xpath("//div[@id='Login']//button[@class='close']")).click();
		
		expliciWait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//form[@id='loginForm']")));
		Assert.assertFalse(driver.findElement(By.xpath("//form[@id='loginForm']")).isDisplayed());
		
	} 
	
	@Test
	public void TC_02_Fixed_Popup() {
		driver.get("https://bni.vn");
		
		expliciWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id='sgpb-popup-dialog-main-div']")));
		Assert.assertTrue(driver.findElement(By.xpath("//div[@id='sgpb-popup-dialog-main-div']")).isDisplayed());
		
		driver.findElement(By.xpath("//input[@value='JOIN WITH US']")).click();
		sleepInSecond(3);
		
		// cho 1 element co the click duoc hay ko
		expliciWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//img[@alt='Close']")));
		
		driver.findElement(By.xpath("//img[@alt='Close']")).click();
		
		expliciWait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//div[@id='sgpb-popup-dialog-main-div']")));
		Assert.assertFalse(driver.findElement(By.xpath("//div[@id='sgpb-popup-dialog-main-div']")).isDisplayed());
	}
	
	@Test
	public void TC_03_Random_Popup_In_Dom() {
		
		driver.get("https://blog.testproject.io/");
		sleepInSecond(7);
		
		// co xuat hien - dong popup di - qua step tiep theo
		// khong xuat hien  qua step tiep theo
		if(driver.findElement(By.xpath("//div[@id='close-mailch']")).isDisplayed()) {
			expliciWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@id='close-mailch']")));
			driver.findElement(By.xpath("//div[@id='close-mailch']")).click();
			sleepInSecond(2);
			
		}
		expliciWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//section//input[@class='search-field']")));
		driver.findElement(By.xpath("//section//input[@class='search-field']")).sendKeys("Selenium");
		
		expliciWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//section//span[@class='glass']")));
		driver.findElement(By.xpath("//section//span[@class='glass']")).click();
		
		sleepInSecond(5);

		
	}
	
	@Test
	public void TC_04_Random_Popup_Not_In_Dom() {
		
		driver.get("https://shopee.vn");
		
		List<WebElement> popup = driver.findElements(By.xpath("//img[@alt='home_popup_banner']"));
		
		if(popup.size() > 0 && popup.get(0).isDisplayed()) {
			expliciWait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".shopee-popup_close-btn")));
			driver.findElement(By.cssSelector(".shopee-popup_close-btn")).click();
			
		} else {
			System.out.print("popup ko xuat hien");
			
		}
	}
	
	
	public void sleepInSecond(long second) {
		try {
			Thread.sleep(second * 1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}

}