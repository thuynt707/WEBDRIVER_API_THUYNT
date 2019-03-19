package selenium;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_03_SeleniumWebDriverAPI {
	WebDriver driver;
	By emailTextbox = By.xpath("//input[@id='mail']");
	By under18Radio = By.xpath("//input[@id='under_18']");
	By educationTextArea = By.xpath("//textarea[@id='edu']");
	By jobRole01 = By.xpath("//select[@name='user_job1']");
	By developmentCheckBox = By.xpath("//input[@id='development']");
	By slider01 = By.xpath("//input[@id='slider-1']");
	By buttonEnabled = By.xpath("//button[@id='button-enabled']");
	By passwordTextBox = By.xpath("//input[@id='password']");
	By radioButtonDisable = By.xpath("//input[@id='radio-disabled']");
	By biography = By.xpath("//textarea[@id='bio']");
	By jobRole02 = By.xpath("//select[@id='job2']");
	By disabledCheckBox = By.xpath("//input[@id='check-disbaled']");
	By slider02 = By.xpath("//input[@id='slider-2']");
	By buttonDisabled = By.xpath("//button[@id='button-disabled']");

	@BeforeClass
	public void beforeClass() {
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}

	// Check Email/ Age (Under 18)/ Education is displayed
	@Test
	public void TC_01_CheckDisplay() throws Exception {
		driver.get("https://daominhdam.github.io/basic-form/index.html");
		if (isElementDisplayed(emailTextbox)) {
			driver.findElement(emailTextbox).sendKeys("Automation testing");
		}
		if (isElementDisplayed(under18Radio)) {
			driver.findElement(under18Radio).click();
		}
		if (isElementDisplayed(educationTextArea)) {
			driver.findElement(educationTextArea).sendKeys("Automation testing");
		}

		Thread.sleep(3000);
	}

	// Check Email/ Age (Under 18)/ Education/ Job Role 01/ Interests (Development)/
	// Slider 01/ Button is enabled
	@Test
	public void TC_02_CheckEnable() {
		driver.get("https://daominhdam.github.io/basic-form/index.html");
		Assert.assertTrue(isElementEnabled(emailTextbox));
		Assert.assertTrue(isElementEnabled(under18Radio));
		Assert.assertTrue(isElementEnabled(educationTextArea));
		Assert.assertTrue(isElementEnabled(jobRole01));
		Assert.assertTrue(isElementEnabled(developmentCheckBox));
		Assert.assertTrue(isElementEnabled(slider01));
		Assert.assertTrue(isElementEnabled(buttonEnabled));
		Assert.assertFalse(isElementEnabled(passwordTextBox));
		Assert.assertFalse(isElementEnabled(radioButtonDisable));
		Assert.assertFalse(isElementEnabled(biography));
		Assert.assertFalse(isElementEnabled(jobRole02));
		Assert.assertFalse(isElementEnabled(disabledCheckBox));
		Assert.assertFalse(isElementEnabled(slider02));
		Assert.assertFalse(isElementEnabled(buttonDisabled));
	}

	// Check selected for Age (Under 18)/ Interests (Development)
	@Test
	public void TC_03_CheckSelected() throws Exception {
		driver.get("https://daominhdam.github.io/basic-form/index.html");
		checkToCheckbox(under18Radio);
		checkToCheckbox(developmentCheckBox);
		Assert.assertTrue(isElementSelected(under18Radio));
		Assert.assertTrue(isElementSelected(developmentCheckBox));
		uncheckToCheckbox(developmentCheckBox);
		Assert.assertFalse(isElementSelected(developmentCheckBox));
	}

	public boolean isElementDisplayed(By by) {
		WebElement element = driver.findElement(by);
		if (element.isDisplayed()) {
			System.out.println("Element: " + by + "is displayed!");
			return true;
		} else {
			System.out.println("Element: " + by + "is not displayed!");
			return false;
		}

	}

	public boolean isElementEnabled(By by) {
		WebElement element = driver.findElement(by);
		if (element.isEnabled()) {
			System.out.println("Element: " + by + "is enabled!");
			return true;
		} else {
			System.out.println("Element: " + by + "is disabled!");
			return false;
		}
	}

	public boolean isElementSelected(By by) {
		WebElement element = driver.findElement(by);
		if (element.isSelected()) {
			System.out.println("Element: " + by + "is selected!");
			return true;
		} else {
			System.out.println("Element: " + by + "is de-selected!");
			return false;
		}
	}

	public void checkToCheckbox(By by) {
		WebElement element = driver.findElement(by);
		if (!element.isSelected()) {
			element.click();
		}
	}

	public void uncheckToCheckbox(By by) {
		WebElement element = driver.findElement(by);
		if (element.isSelected()) {
			element.click();
		}
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}

}
