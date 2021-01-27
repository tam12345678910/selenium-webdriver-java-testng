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

public class Topic_05_Element_Method_Part_II {
	WebDriver driver;
	By emailTexbox = By.cssSelector("#mail");
	By EducationTextArea = By.cssSelector("#edu");
	By ageUnderEighteenRadioStatus = By.cssSelector("#under_18");
	By nameText = By.xpath("//h5[text()='Name: User5']");
	By slider1 = By.cssSelector("#slider-1");
	By slider2 = By.cssSelector("#slider-2");
	By javalanguaCheckbox = By.id("#java");
	
	
	@BeforeClass
	public void beforeClass() {
		
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}
	
	@Test
	public void TC_01_Displayed() {
		driver.get("https://automationfc.github.io/basic-form/index.html");
		
		if(isElementDisplayed(emailTexbox)) {
			sendKeyElement(emailTexbox, "Automation Testing");
		}
		
		if(isElementDisplayed(EducationTextArea)) {
			sendKeyElement(EducationTextArea, "Automation Testing");
		}
		
		if(isElementDisplayed(ageUnderEighteenRadioStatus)) {
			clickElement(ageUnderEighteenRadioStatus);
		}
		
		Assert.assertFalse(isElementDisplayed(nameText));
		
	}
	
	
	@Test
	public void TC_02_Enabled() {
		driver.get("https://automationfc.github.io/basic-form/index.html");
		
		Assert.assertTrue(isElementEnabled(emailTexbox));
		Assert.assertTrue(isElementEnabled(slider1));
		Assert.assertFalse(isElementEnabled(slider2));
	
	}
	
	@Test
	public void TC_03_Selected() {
		driver.get("https://automationfc.github.io/basic-form/index.html");
		
		clickElement(ageUnderEighteenRadioStatus);
		clickElement(javalanguaCheckbox);
		
		Assert.assertTrue(isElementSelected(ageUnderEighteenRadioStatus));
		Assert.assertTrue(isElementSelected(javalanguaCheckbox));
		
		clickElement(ageUnderEighteenRadioStatus);
		clickElement(javalanguaCheckbox);
		
		Assert.assertTrue(isElementSelected(ageUnderEighteenRadioStatus));
		Assert.assertFalse(isElementSelected(javalanguaCheckbox));
		
		
	}
	
	public boolean isElementDisplayed(By by ) {
		WebElement element = driver.findElement(by);
		if(element.isDisplayed()) {
			System.out.println("Element is display");
			return true;
		} else {
			System.out.println("Element is not display");
			return false;
		}
		
	}
	
	public boolean isElementEnabled(By by ) {
		WebElement element = driver.findElement(by);
		if(element.isEnabled()) {
			System.out.println("Element is enabled");
			return true;
		} else {
			System.out.println("Element is not disabled");
			return false;
		}
		
	}
	
	public boolean isElementSelected(By by ) {
		WebElement element = driver.findElement(by);
		if(element.isSelected()) {
			System.out.println("Element is selected");
			return true;
		} else {
			System.out.println("Element is not selected");
			return false;
		}
		
	}
	
	public void sendKeyElement(By by, String value) {
		WebElement element = driver.findElement(by);
		element.clear();
		element.sendKeys(value);
	}
	
	public void clickElement(By by) {
		WebElement element = driver.findElement(by);
		element.click();
	}
	
	
	@AfterClass
	public void afterClass() {
		driver.quit();
	}
}
