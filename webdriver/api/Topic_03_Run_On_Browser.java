package api;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.Test;

public class Topic_03_Run_On_Browser {
	WebDriver driver;
	String projectLocation = System.getProperty("user.dir");
	
	//@Test
	public void TC_01_Run_On_Firefox() {
		driver = new FirefoxDriver();
		driver.get("https://google.com");
		driver.quit();
	}

	@Test
	public void TC_02_Run_On_Chrome() {
		// 01
		//System.setProperty("webdriver.chrome.driver", "/Users/vlttduon/Documents/02 - Selenium API/browserDrivers/chromedriver");
		
		//02
		 //System.setProperty("webdriver.chrome.driver", "./browserDrivers/chromedriver");
		
		//03
		System.setProperty("webdriver.chrome.driver", projectLocation + "/browserDrivers/chromedriver");
		
		driver = new ChromeDriver();
		driver.get("https://google.com");
		driver.quit();
	}
}
