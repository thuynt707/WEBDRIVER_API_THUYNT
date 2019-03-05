package selenium;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_03_SeleniumWebDriverAPI {
	WebDriver driver;

	@BeforeClass
	public void beforeClass() {
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}

	// Check Email/ Age (Under 18)/ Education is displayed
	@Test
	public void TC_01_CheckDisplay() {
		driver.get("https://daominhdam.github.io/basic-form/index.html");
		WebElement email = driver.findElement(By.id("mail"));
		if (email.isDisplayed()) {
			email.sendKeys("Automation Testing");
		}

		WebElement ageUnder18 = driver.findElement(By.id("under_18"));
		if (ageUnder18.isDisplayed()) {
			ageUnder18.click();
		}

		WebElement education = driver.findElement(By.id("edu"));
		if (education.isDisplayed()) {
			education.sendKeys("Automation Testing");
		}
	}

	// Check Email/ Age (Under 18)/ Education/ Job Role 01/ Interests (Development)/
	// Slider 01/ Button is enabled
	@Test
	public void TC_02_CheckEnable() {
		driver.get("https://daominhdam.github.io/basic-form/index.html");

		WebElement email = driver.findElement(By.id("mail"));
		if (email.isEnabled()) {
			System.out.println("Email element is enabled");
		}

		WebElement ageUnder18 = driver.findElement(By.id("under_18"));
		if (ageUnder18.isEnabled()) {
			System.out.println("Age (Under 18) element is enabled");
		}

		WebElement education = driver.findElement(By.id("edu"));
		if (education.isEnabled()) {
			System.out.println("Education element is enabled");
		}

		WebElement job1 = driver.findElement(By.id("job1"));
		if (job1.isEnabled()) {
			System.out.println("Job Role 01 element is enabled");
		}

		WebElement dev = driver.findElement(By.id("development"));
		if (dev.isEnabled()) {
			System.out.println("Development element is enabled");
		}

		WebElement slider01 = driver.findElement(By.id("slider-1"));
		if (slider01.isEnabled()) {
			System.out.println("Slider 01 element is enabled");
		}

		WebElement buttonEnabled = driver.findElement(By.id("button-enabled"));
		if (buttonEnabled.isEnabled()) {
			System.out.println("ButtonEnabled element is enabled");
		}
	}

	// Check Age (Under 18)/ Interests (Development) is selected
	@Test
	public void TC_03_CheckSelected() throws InterruptedException {
		driver.get("https://daominhdam.github.io/basic-form/index.html");

		WebElement ageUnder18 = driver.findElement(By.id("under_18"));
		ageUnder18.click();
		Thread.sleep(2000);
		if (ageUnder18.isSelected()) {
			System.out.println("Age (Under 18) is selected");
		} else {
			ageUnder18.click();
		}

		WebElement dev = driver.findElement(By.id("development"));
		dev.click();
		Thread.sleep(2000);
		if (dev.isSelected()) {
			System.out.println("Development element is selected");
		} else {
			dev.click();
		}

	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}

}
