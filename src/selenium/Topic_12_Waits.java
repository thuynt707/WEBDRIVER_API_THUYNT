package selenium;

import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.server.handler.FindElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_12_Waits {
	WebDriver driver;
	WebDriverWait waitExplicit;
	
	By startBtn = By.xpath("//button[contains(text(),'Start')]");
	By loadingIcon = By.xpath("//div[@id = 'loading']/img");
	By helloText = By.xpath("//div[@id='finish']/h4[text()='Hello World!']");

	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.gecko.driver", ".\\driver\\geckodriver.exe");
		driver = new FirefoxDriver();
		driver.manage().window().maximize();
		
	}

	@Test
	public void TC_01_ImplicitWait() {
		driver.get("http://the-internet.herokuapp.com/dynamic_loading/2");
		
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		
		driver.findElement(startBtn).click();
		//Take 5s to render "Hello World"
		Assert.assertTrue(driver.findElement(helloText).isDisplayed());
		
	}
	@Test
	public void TC_02_LoadingIconInvisible() {
		driver.get("http://the-internet.herokuapp.com/dynamic_loading/2");
		
		driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
		waitExplicit = new WebDriverWait(driver, 5);
		
		driver.findElement(startBtn).click();
		
		//Take 5s for "Loading" invisible
		waitExplicit.until(ExpectedConditions.invisibilityOfElementLocated(loadingIcon));
		
		Assert.assertTrue(driver.findElement(helloText).isDisplayed());
		
	}
	
	@Test
	public void TC_03_HelloworldTextVisible() {
		driver.get("http://the-internet.herokuapp.com/dynamic_loading/2");
		
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		waitExplicit = new WebDriverWait(driver, 2);
		
		driver.findElement(startBtn).click();
		
		//Take 5s for "Hello world" text visible
		waitExplicit.until(ExpectedConditions.visibilityOf(driver.findElement(helloText)));
		
		Assert.assertTrue(driver.findElement(helloText).isDisplayed());
		
	}
	
	@Test
	public void TC_04_LoadingIcon_HelloText_NoLongerInDOM() {
		driver.get("http://the-internet.herokuapp.com/dynamic_loading/2");
		
		//driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		waitExplicit = new WebDriverWait(driver, 5);
		
		//Invisible + not in DOM
		System.out.println("Start time = " + getDateTimeSecond());
		waitExplicit.until(ExpectedConditions.invisibilityOfElementLocated(loadingIcon));
		System.out.println("End time = " + getDateTimeSecond());
		
		//Invisible + not in DOM
		System.out.println("Start time = " + getDateTimeSecond());
		waitExplicit.until(ExpectedConditions.invisibilityOfElementLocated(helloText));
		System.out.println("End time = " + getDateTimeSecond());
		
		System.out.println("Start time = " + getDateTimeSecond());
		driver.findElement(startBtn).click();
		System.out.println("End time = " + getDateTimeSecond());
		
		//Invisible + in DOM
		System.out.println("Start time = " + getDateTimeSecond());
		waitExplicit.until(ExpectedConditions.invisibilityOfElementLocated(loadingIcon));
		System.out.println("End time = " + getDateTimeSecond());
		
		//Invisible + in DOM
		System.out.println("Start time = " + getDateTimeSecond());
		waitExplicit.until(ExpectedConditions.invisibilityOfElementLocated(startBtn));
		System.out.println("End time = " + getDateTimeSecond());
	}
	
	public Date getDateTimeSecond() {
        Date date = new Date();
        date = new Date(date.getTime());
        return date;
}
	
	@AfterClass
	public void afterClass() {
		driver.quit();
	}

}