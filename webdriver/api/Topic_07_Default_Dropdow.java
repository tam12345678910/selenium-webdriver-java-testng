 package api;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_07_Default_Dropdow {

	WebDriver driver;
	Select select;
	WebDriverWait explicitWait;
	JavascriptExecutor jsExecutor;
	Actions action;
	String firtsName, lastName, emailAddress, companyName, password;
	String date, month, year;
	

	@BeforeClass
	public void beforeClass() {
		
		driver = new FirefoxDriver();
		firtsName = "John";
		lastName = "Wick";
		emailAddress = "johnwick" + getRandomNumber() + "@hotmail.com";
		companyName = "holywod";
		password = "123456";
		
		date = "30";
		month = "September";
		year = "1998";
		
		explicitWait = new WebDriverWait(driver, 20);
		jsExecutor = (JavascriptExecutor) driver;
		action = new Actions(driver);
		
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}

	@Test
	public void TC_01_Register() {
		
		driver.get("https://demo.nopcommerce.com/register");
		/* 1 - Mở trang dang ky */
		driver.findElement(By.cssSelector(".ico-register")).click();
		
		/* 2 - Dien thong tin vao cac field bat buoc */
		checkToCheckboxOrRadio(By.cssSelector("#gender-male"));
		driver.findElement(By.id("FirstName")).sendKeys(firtsName);
		driver.findElement(By.id("LastName")).sendKeys(lastName);
		
		// khoi tao bien select  de thao tac vs dropdown Date
		select = new Select(driver.findElement(By.cssSelector("select[name='DateOfBirthDay']")));
		select.selectByVisibleText("30");
		Assert.assertEquals(select.getFirstSelectedOption().getText(), date);
		Assert.assertEquals(select.getOptions().size(), 32);
		
		// khoi tao bien select  de thao tac vs dropdown Date
		select = new Select(driver.findElement(By.cssSelector("select[name='DateOfBirthMonth']")));
		select.selectByVisibleText("September");
		Assert.assertEquals(select.getFirstSelectedOption().getText(), month);
		List<WebElement> itemList = select.getOptions();
		Assert.assertEquals(itemList.size(), 13);
				
		// khoi tao bien select  de thao tac vs dropdown Date
		select = new Select(driver.findElement(By.cssSelector("select[name='DateOfBirthYear']")));
		select.selectByVisibleText("1998");
		Assert.assertEquals(select.getFirstSelectedOption().getText(), year); 
		Assert.assertEquals(select.getOptions().size(), 112);
		
		driver.findElement(By.id("Email")).sendKeys(emailAddress);
		driver.findElement(By.id("Company")).sendKeys(companyName);
		
		checkToCheckboxOrRadio(By.id("Newsletter"));
		driver.findElement(By.id("Password")).sendKeys(password);
		driver.findElement(By.id("ConfirmPassword")).sendKeys(password);
		
		/* 3 Dang ky */
		driver.findElement(By.id("register-button")).click();
		
		/* 4 kiem tra xuat hien message dang ky thanh cong */
		Assert.assertEquals(driver.findElement(By.cssSelector(".result")).getText(), "Your registration completed");
		
		/* 5 vao trang my account */
		driver.findElement(By.cssSelector(".ico-account")).click();
		
		/* 6 kiem tra dung thong tin dang ky */
		Assert.assertTrue(driver.findElement(By.cssSelector("#gender-male")).isSelected());
		Assert.assertTrue(driver.findElement(By.cssSelector("#Newsletter")).isSelected());
		Assert.assertEquals(driver.findElement(By.id("FirstName")).getAttribute("value"), firtsName);
		Assert.assertEquals(driver.findElement(By.id("LastName")).getAttribute("value"), lastName);
		Assert.assertEquals(driver.findElement(By.id("Email")).getAttribute("value"), emailAddress);
		Assert.assertEquals(driver.findElement(By.id("Company")).getAttribute("value"), companyName);
		
		select = new Select(driver.findElement(By.cssSelector("select[name='DateOfBirthDay']")));
		Assert.assertEquals(select.getFirstSelectedOption().getText(), date);
		Assert.assertEquals(select.getOptions().size(), 32);
		
		select = new Select(driver.findElement(By.cssSelector("select[name='DateOfBirthMonth']")));
		Assert.assertEquals(select.getFirstSelectedOption().getText(), month);
		List<WebElement> itemList2 = select.getOptions();
		Assert.assertEquals(itemList2.size(), 13);
				
		select = new Select(driver.findElement(By.cssSelector("select[name='DateOfBirthYear']")));
		Assert.assertEquals(select.getFirstSelectedOption().getText(), year); 
		Assert.assertEquals(select.getOptions().size(), 112);
	}
	
	
	@Test
	public void TC_02_HTMl_DropDownList() {
	
		driver.get("https://automationfc.github.io/basic-form/index.html");
		
		select = new Select(driver.findElement(By.cssSelector("select[id='job1']")));
		Assert.assertFalse(select.isMultiple());
		
		select.selectByVisibleText("Mobile Testing");
		Assert.assertEquals(select.getFirstSelectedOption().getText(), "Mobile Testing");
		
		select.selectByValue("manual");
		Assert.assertEquals(select.getFirstSelectedOption().getAttribute("value"), "manual");
		
		select.selectByIndex(9);
		Assert.assertEquals(select.getFirstSelectedOption().getText(), "Functional UI Testing");

		select = new Select(driver.findElement(By.cssSelector("select[id='job1']")));
		List<WebElement> itemList = select.getOptions();
		Assert.assertEquals(itemList.size(), 10);
		
		select = new Select(driver.findElement(By.cssSelector("select[id='job2']")));

		ArrayList<String> allItemText = new ArrayList<String>();
		allItemText.add("Automation");
		allItemText.add("Mobile");
		allItemText.add("Desktop");
		
		for(String item : allItemText) {
			select.selectByVisibleText(item);
		}
		
		List<WebElement> lstWebElement = select.getAllSelectedOptions();
		ArrayList<String> allSelectedText = new ArrayList<String>();
		
		for(WebElement item : lstWebElement) {
			allSelectedText.add(item.getText());
		}
		
		Assert.assertTrue(select.isMultiple());
		Assert.assertEquals(allItemText, allSelectedText);
 	}
	
	public void checkToCheckboxOrRadio(By by) {
		WebElement element = driver.findElement(by);
		if (!element.isSelected()) {
			element.click();
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
	
	public int getRandomNumber() {
		Random rand = new Random();
		rand.nextInt(99999);
		return rand.nextInt();
	}	
	
	@AfterClass
	public void afterClass() {
		driver.quit();
	}
	
}
