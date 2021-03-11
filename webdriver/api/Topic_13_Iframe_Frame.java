package api;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class Topic_13_Iframe_Frame {
	WebDriver driver;
	String projectLocation = System.getProperty("user.dir");

	@BeforeClass
	public void beforeClass() {
		
		
		System.setProperty("webdriver.chrome.driver", projectLocation + "/browserDrivers/chromedriver/");
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}

	//@Test
	public void TC_01_Iframe() {
		driver.get("https://automationfc.com/2020/02/18/training-online-automation-testing/");
		
		// Swithch vao iframe cua facebook
		driver.switchTo().frame(driver.findElement(By.xpath("//iframe[contains(@title, 'Facebook Social Plugin')]")));
		
		Assert.assertEquals(driver.findElement(By.xpath("//a[@title='Automation FC']")).getText(), "Automation FC");
		
		String likeText = driver.findElement(By.xpath("//a[@title='Automation FC']/parent::div/following-sibling::div")).getText();
		
		int likeNumber = Integer.parseInt(likeText.split(" ")[0].replace(",", ""));
		
		assertThat(likeNumber, greaterThan(1900));
		
		// Swith to Top Window
		driver.switchTo().defaultContent();
		
		Assert.assertEquals(driver.findElement(By.className("post-title")).getText(), "[Training Online] – Fullstack Selenium WebDriver Framework in Java (Livestream)");
		
		// Swith to Google Doc Iframe
		driver.switchTo().frame(driver.findElement(By.xpath("//iframe[contains(@src,'docs.google.com')]")));
		
		Assert.assertEquals(driver.findElement(By.cssSelector(".exportFormTitle")).getText(), "KHÓA HỌC SELENIUM AUTOMATION TESTING");
	}

	@Test
	public void TC_02_Iframe() {

		driver.get("https://kyna.vn/");
		
		
		// Swith to chat iframe
		driver.switchTo().frame("cs_chat_iframe");
		
		driver.findElement(By.xpath("//div[@class='border_overlay meshim_widget_widgets_BorderOverlay']")).click();
		
		//driver.findElement(By.xpath("//textarea[@ng-model='login.content']")).sendKeys("Automation Testing"); 
		
		//driver.findElement(By.xpath("//textarea[@ng-model='login.content']")).sendKeys(Keys.ENTER);
		
		sleepInSecond(5);
		
		Assert.assertTrue(driver.findElement(By.xpath("//input[@ng-model='login.username']")).isDisplayed());
		
		// Switch to Top Window (Home Page)
		driver.switchTo().defaultContent();
		
		driver.findElement(By.id("live-search-bar")).sendKeys("Excel");
		driver.findElement(By.cssSelector(".search-button")).click();
		
		List<WebElement> courseNameHeader = driver.findElements(By.cssSelector("div.content h4"));
		
		List<String> courseNameText = new ArrayList<String>();
		
		for (WebElement courseElement : courseNameHeader) {
			System.out.println(courseElement.getText() );
			courseNameText.add(courseElement.getText());
		}
		
		for (String courseName : courseNameText) {
			Assert.assertTrue(courseName.contains("Excel"));
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