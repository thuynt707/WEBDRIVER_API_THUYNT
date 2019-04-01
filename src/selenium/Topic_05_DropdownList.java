package selenium;

import java.util.concurrent.TimeUnit;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;


import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Topic_05_DropdownList {
	WebDriver driver;
	WebDriverWait waitExplicit;
	JavascriptExecutor executorScript;

	@BeforeClass
	public void beforeClass() {
		driver = new FirefoxDriver();
		waitExplicit = new WebDriverWait(driver, 30);
		executorScript = (JavascriptExecutor) driver;
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}
	
	@Test
	public void TC_01_DefaultDropdown() {
		driver.get("https://daominhdam.github.io/basic-form/index.html");
		WebElement jobRole01 = driver.findElement(By.xpath("//select[@id='job1']"));
		Select jobRoleSelect = new Select(jobRole01);
		//Check dropdown does not support multiple select
		Assert.assertFalse(jobRoleSelect.isMultiple());
		
		//Select by Visible text
		jobRoleSelect.selectByVisibleText("Automation Tester");
		Assert.assertEquals(jobRoleSelect.getFirstSelectedOption().getText(), "Automation Tester");
		
		//Select by Value
		jobRoleSelect.selectByValue("manual");
		Assert.assertEquals(jobRoleSelect.getFirstSelectedOption().getText(), "Manual Tester");
		
		//Select by Index
		jobRoleSelect.selectByIndex(3);
		Assert.assertEquals(jobRoleSelect.getFirstSelectedOption().getText(), "Mobile Tester");
		
		//Check dropdown size
		Assert.assertEquals(jobRoleSelect.getOptions().size(), 5);
	}
	
	@Test
	public void TC_02_CustomDropdown() throws Exception {
		driver.get("http://jqueryui.com/resources/demos/selectmenu/default.html");
		selectItemInCustomDropdown("//span[@id='number-button']", "//ul[@id='number-menu']/li[@class='ui-menu-item']/div", "19");
		Thread.sleep(1000);
		selectItemInCustomDropdown("//span[@id='number-button']", "//ul[@id='number-menu']/li[@class='ui-menu-item']/div", "1");
		Thread.sleep(1000);
		selectItemInCustomDropdown("//span[@id='number-button']", "//ul[@id='number-menu']/li[@class='ui-menu-item']/div", "10");
		Thread.sleep(1000);
		
		//5: Check the item is selected successful
		Assert.assertTrue(isElementDisplayed("//span[@id='number-button']//span[@class='ui-selectmenu-text' and text()='10']"));
	}
	
	@Test
	public void TC_03_CustomAngularDropdown() throws Exception {
		driver.get("https://material.angular.io/components/select/examples");
		
		selectItemInCustomDropdown("//mat-select[@id='mat-select-5']//span", "//mat-option/span", "Alabama");
		Thread.sleep(1000);
		Assert.assertTrue(isElementDisplayed("//mat-select[@id='mat-select-5']//span[text()='Alabama']"));
		
		selectItemInCustomDropdown("//mat-select[@id='mat-select-5']//span", "//mat-option/span", "Georgia");
		Thread.sleep(1000);
		Assert.assertTrue(isElementDisplayed("//mat-select[@id='mat-select-5']//span[text()='Georgia']"));
		
		selectItemInCustomDropdown("//mat-select[@id='mat-select-5']//span", "//mat-option/span", "Nevada");
		Thread.sleep(1000);
		Assert.assertTrue(isElementDisplayed("//mat-select[@id='mat-select-5']//span[text()='Nevada']"));
		
		selectItemInCustomDropdown("//mat-select[@id='mat-select-5']//span", "//mat-option/span", "Wyoming");
		Thread.sleep(1000);
		//5: Check the item is selected successful
		Assert.assertTrue(isElementDisplayed("//mat-select[@id='mat-select-5']//span[text()='Wyoming']"));
	}
	
	@Test
	public void TC_04_CustomTelerikDropdown() throws Exception {
		driver.get("https://demos.telerik.com/kendo-ui/dropdownlist/index");
		
		selectItemInCustomDropdown("//input[@id='color']/preceding-sibling::span", "//ul[@id='color_listbox']/li", "Orange");
		Thread.sleep(1000);
		//5: Check the item is selected successful
		Assert.assertTrue(isElementDisplayed("//span[@class='k-input' and text()='Orange']"));
	}
	
	@Test
	public void TC_05_CustomVueDropdown() throws Exception {
		driver.get("https://mikerodham.github.io/vue-dropdowns/");
		
		selectItemInCustomDropdown("//div[@class='btn-group']/li", "//ul[@class='dropdown-menu']//a", "Second Option");
		Thread.sleep(1000);
		//5: Check the item is selected successful
		Assert.assertTrue(isElementDisplayed("//div[@class='btn-group']/li[contains(text(),'Second Option')]"));
	}
	
	
	public void selectItemInCustomDropdown(String parentXpath, String allItemXpath, String expectedValueItem) throws Exception {
		//1: Click into dropdown to show all item
		WebElement parentDropdown = driver.findElement(By.xpath(parentXpath));
		executorScript.executeScript("arguments[0].click();", parentDropdown);
		
		//2: Wait until all items are shown
		waitExplicit.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(allItemXpath)));
		
		List <WebElement> allItems = driver.findElements(By.xpath(allItemXpath));
		System.out.println("All element in dropdown list = " + allItems.size());
		
		for(WebElement childElement : allItems) {
			if(childElement.getText().equals(expectedValueItem)) {
				//3: Scroll to find expected item
				executorScript.executeScript("arguments[0].scrollIntoView(true);", childElement);
				Thread.sleep(1000);
				
				//4: Click into item
				childElement.click();
				Thread.sleep(1000);
				break;
			}
		}	
	}
	
	public boolean isElementDisplayed(String xpathValue) {
		WebElement element = driver.findElement(By.xpath(xpathValue));
		if (element.isDisplayed()) {
			System.out.println("Element is displayed");
			return true;
		} else {
			System.out.println("Element is not displayed");
			return false;
		}
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}

}