package api;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_08_Custom_Dropdow {

	WebDriver driver;
	WebDriverWait explicitWait;
	String projectLocation = System.getProperty("user.dir");
	JavascriptExecutor jsExecutor;

	String month = "February";
	String[] firstMonths = { "March", "May", "September", "November" };
	String[] secondMonths = { "March", "May", "September"};

	@BeforeClass
	public void beforeClass() {

		System.setProperty("webdriver.chrome.driver", projectLocation + "/browserDrivers/chromedriver");

		driver = new ChromeDriver();
		jsExecutor = (JavascriptExecutor) driver;
		explicitWait = new WebDriverWait(driver, 30);

		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}

	@Test
	public void TC_01_Jquery() {
		driver.get("https://jqueryui.com/resources/demos/selectmenu/default.html");

		selectedInCustomDropdown("//span[@id='number-button']", "//ul[@id='number-menu']//div", "19");
		Assert.assertEquals(driver
				.findElement(By.xpath("//span[@id='number-button']//span[@class='ui-selectmenu-text']")).getText(),
				"19");
	}

	@Test
	public void TC_02_Angular() {

		driver.get(
				"https://ej2.syncfusion.com/angular/demos/?_ga=2.262049992.437420821.1575083417-524628264.1575083417#/material/drop-down-list/data-binding");

		selectedInCustomDropdown("//ejs-dropdownlist[@id='games']", "//ul[@id='games_options']//li", "Basketball");
		Assert.assertEquals(getAngularDropdownSelectedText(), "Basketball");
	}

	@Test
	public void TC_03_React() {
		driver.get("https://react.semantic-ui.com/maximize/dropdown-example-selection/");

		selectedInCustomDropdown("//div[@role='listbox']", "//div[@role='option']/span", "Christian");
		sleepInSecond(2);
		Assert.assertEquals(driver.findElement(By.xpath("//div[@class='divider text']")).getText(), "Christian");
	}

	@Test
	public void TC_04_VueJs() {
		driver.get("https://mikerodham.github.io/vue-dropdowns/");

		selectedInCustomDropdown("//li[@class='dropdown-toggle']", "//ul[@class='dropdown-menu']//a", "Second Option");
		sleepInSecond(2);
		Assert.assertEquals(driver.findElement(By.xpath("//li[@class='dropdown-toggle']")).getText(), "Second Option");
	}

	@Test
	public void TC_05_Editable() {
		driver.get("https://react.semantic-ui.com/maximize/dropdown-example-search-selection/");

		selectedInEditableDropdown("//input[@class='search']", "//div[@role='option']/span", "Albania");
		sleepInSecond(2);
		Assert.assertEquals(driver.findElement(By.xpath("//div[@class='divider text']")).getText(), "Albania");
	}

	@Test
	public void TC_06_Multiple_Select() {

		driver.get("http://multiple-select.wenzhixin.net.cn/templates/template.html?v=189&url=basic.html");
		
		selectedMultipleItemInCustomeDropDown("(//button[@class='ms-choice'])[1]", "(//div[@class='ms-drop bottom'])[1]//li//span", firstMonths);
		sleepInSecond(2);
		Assert.assertTrue(areItemSelected(firstMonths));
		
		driver.navigate().refresh();
		
		selectedMultipleItemInCustomeDropDown("(//button[@class='ms-choice'])[1]", "(//div[@class='ms-drop bottom'])[1]//li//span", secondMonths);
		sleepInSecond(2);
		Assert.assertTrue(areItemSelected(secondMonths));
		
	}

	public void selectedInCustomDropdown(String parentXpath, String allItemXpath, String expectedText) {
		// click vao dropdown
		driver.findElement(By.xpath(parentXpath)).click();
		explicitWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(allItemXpath)));

		List<WebElement> allItem = driver.findElements(By.xpath(allItemXpath));

		for (WebElement item : allItem) {
			if (item.getText().equals(expectedText)) {
				item.click();
				sleepInSecond(2);
				break;
			}

		}

	}

	public void selectedMultipleItemInCustomeDropDown(String parentXpath, String allItemXpath, String[] months) {
		// click vao dropdown
		driver.findElement(By.xpath(parentXpath)).click();
		explicitWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(allItemXpath)));

		List<WebElement> allItem = driver.findElements(By.xpath(allItemXpath));

		for (String month : months) {
			for (WebElement item : allItem) {
				if (item.getText().equals(month)) {
					item.click();
					sleepInSecond(2);
					break;
				}

			}
		}
	}
	
	public void selectedInEditableDropdown(String parentXpath, String allItemXpath, String expectedText) {
		// click vao dropdown
		driver.findElement(By.xpath(parentXpath)).clear();
		driver.findElement(By.xpath(parentXpath)).click();
		explicitWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(allItemXpath)));

		List<WebElement> allItem = driver.findElements(By.xpath(allItemXpath));

		for (WebElement item : allItem) {
			if (item.getText().equals(expectedText)) {
				item.click();
				sleepInSecond(2);
				break;
			}

		}

	}

	public boolean areItemSelected(String[] months) {

		List<WebElement> itemSelected = driver.findElements(By.xpath("//li[@class='selected']//input"));

		int numberItemSelected = itemSelected.size();

		String allItemSelectedText = driver.findElement(By.xpath("//button[@class='ms-choice']/span)[1]")).getText();
		System.out.print("Text da chon" + allItemSelectedText);

		if (numberItemSelected <= 3 && numberItemSelected > 0) {
			for (String item : months) {
				if (allItemSelectedText.contains(item)) {
					break;
				}
			}
			return true;

		} else if (numberItemSelected >= 12) {

			return driver.findElement(By.xpath("//button[@class='ms-choice']/span[text()='All selected']"))
					.isDisplayed();
		} else if (numberItemSelected > 3 && numberItemSelected < 12) {

			return driver
					.findElement(By.xpath(
							"//button[@class='ms-choice']/span[text()='" + numberItemSelected + " of 12 selected']"))
					.isDisplayed();
		} else {
			new RuntimeException("No Selected month");
			return false;
		}

	}

	public String getAngularDropdownSelectedText() {
		return (String) jsExecutor
				.executeScript("return document.querySelector(\"select[name='games']>option[selected]\").text");

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
