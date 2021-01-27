package api;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_05_Element_Method_Part_I {
	WebDriver driver;

	@BeforeClass
	public void beforeClass() {
		
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}
	
	@Test
	public void TC_01_Displayed() {
		driver.get("https://automationfc.github.io/basic-form/index.html");
		
		boolean displayEmail = driver.findElement(By.cssSelector("#mail")).isDisplayed();
		
		if(displayEmail == true) {
			driver.findElement(By.cssSelector("#mail")).sendKeys("Automation Testing");
			System.out.println("Email textbox is display");
		} else {
			System.out.println("Email textbox is not display");
			
		}
		
		boolean displayEdu = driver.findElement(By.cssSelector("#edu")).isDisplayed();
		
		if(displayEdu == true) {
			driver.findElement(By.cssSelector("#edu")).sendKeys("Automation Testing");
			System.out.println("Education textarea is display");
		} else {  
			System.out.println("Education textarea is not display");
			
		}	
		
		boolean ageUnderEighteenRadioStatus = driver.findElement(By.cssSelector("#under_18")).isDisplayed();
		
		if(ageUnderEighteenRadioStatus == true) {
			driver.findElement(By.cssSelector("#under_18")).click();
			System.out.println("Age under 18 is display");
		} else {  
			System.out.println("Age under 18 is not display");
			
		}
		
		boolean nameUserFiveTextStatus = driver.findElement(By.xpath("//h5[text()='Name: User5']")).isDisplayed();
		
		if(nameUserFiveTextStatus == true) {
			driver.findElement(By.cssSelector("#under_18")).click();
			System.out.println("Name user 5 is display");
		} else {  
			System.out.println("Name user 5 is not display");
			
		}	
	}
	
	@Test
	public void TC_02_Enabled() {
		driver.get("https://automationfc.github.io/basic-form/index.html");
		
		boolean enabledEmail = driver.findElement(By.cssSelector("#mail")).isEnabled();
		
		if(enabledEmail == true) {
			System.out.println("Email textbox is enabled");
		} else {
			System.out.println("Email textbox is not enabled");
		}
		
		driver.get("https://automationfc.github.io/basic-form/index.html");
		
		boolean sliderOneStatus = driver.findElement(By.cssSelector("#slider-1")).isEnabled();
		
		if(sliderOneStatus == true) {
			System.out.println("slider 1 is enabled");
		} else {
			System.out.println("slider 1 is not enabled");
		}
		
		boolean sliderTwoStatus = driver.findElement(By.cssSelector("#slider-2")).isEnabled();
		
		if(sliderTwoStatus == true) {
			System.out.println("slider 2 is enabled");
		} else {
			System.out.println("slider 2 is not enabled");
		}
	
	}
	
	@Test
	public void TC_03_Selected() {
		driver.get("https://automationfc.github.io/basic-form/index.html");
		
		driver.findElement(By.id("under_18")).click();
		driver.findElement(By.id("java")).click();
		
		Assert.assertTrue(driver.findElement(By.id("under_18")).isSelected());
		Assert.assertTrue(driver.findElement(By.id("java")).isSelected());
		
		driver.findElement(By.id("under_18")).click();
		driver.findElement(By.id("java")).click();
		
		Assert.assertTrue(driver.findElement(By.id("under_18")).isSelected());
		Assert.assertFalse(driver.findElement(By.id("java")).isSelected());
		
		
	}
	
	
	@AfterClass
	public void afterClass() {
		driver.quit();
	}
}
