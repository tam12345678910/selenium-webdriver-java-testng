package api;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_02_Xpath_Css_Part_I {
	WebDriver driver;

	@BeforeClass
	public void beforeClass() {
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize(); 
		driver.get("https://alada.vn/tai-khoan/dang-ky.html");
	}

	@Test
	public void TC_01_Validate() throws InterruptedException {
		  // Hiểu được HTML của 1 element
		  // Tại sao lại phải bắt element
		  // Bắt xong rồi  thì làm gì/ thao tác như thế nào
		
		  // Thao tác vs ĐĂNG KÍ Button
		  driver.findElement(By.xpath("//form[@id='frmLogin']//button[text()='ĐĂNG KÝ']")).click();
		  
		  // driver: đại diện cho selenium webdriver - gọi thư viện ra để sử dụng
		  // findElement: tìm element
		  // By.id/ classname/ name/ xpath/ cssSelector/ tagname/ linkText/ partialLinkText: loại locator gì
		  // click(): click vào button
		  
		  Thread.sleep(3000);
		  
		  // Nhập vào textbox HỌ TÊN
		  driver.findElement(By.cssSelector("input[name='txtFirstname']")).sendKeys("Automation FC");
		  
		  Thread.sleep(3000);
		  // Nhập vào textbox password
		  driver.findElement(By.id("txtPassword")).sendKeys("123456789");
		  
		  Thread.sleep(3000);		  
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}

}