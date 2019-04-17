package selenium;

import java.util.Date;
import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;
import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.google.common.base.Function;

public class Topic_12_Waits {
	WebDriver driver;
	WebDriverWait waitExplicit;

	By startBtn = By.xpath("//button[contains(text(),'Start')]");
	By loadingIcon = By.xpath("//div[@id = 'loading']/img");
	By helloText = By.xpath("//div[@id='finish']/h4[text()='Hello World!']");

	@BeforeTest
	public void beforeClass() {
		System.setProperty("webdriver.gecko.driver", ".\\driver\\geckodriver.exe");
		driver = new FirefoxDriver();
		driver.manage().window().maximize();

	}

//	@Test
	public void TC_01_ImplicitWait() {
		driver.get("http://the-internet.herokuapp.com/dynamic_loading/2");

		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

		driver.findElement(startBtn).click();
		// Take 5s to render "Hello World"
		Assert.assertTrue(driver.findElement(helloText).isDisplayed());

	}

//	@Test
	public void TC_02_LoadingIconInvisible() {
		driver.get("http://the-internet.herokuapp.com/dynamic_loading/2");

		driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
		waitExplicit = new WebDriverWait(driver, 5);

		driver.findElement(startBtn).click();

		// Take 5s for "Loading" invisible
		waitExplicit.until(ExpectedConditions.invisibilityOfElementLocated(loadingIcon));

		Assert.assertTrue(driver.findElement(helloText).isDisplayed());

	}

//	@Test
	public void TC_03_HelloworldTextVisible() {
		driver.get("http://the-internet.herokuapp.com/dynamic_loading/2");

		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		waitExplicit = new WebDriverWait(driver, 2);

		driver.findElement(startBtn).click();

		// Take 5s for "Hello world" text visible
		waitExplicit.until(ExpectedConditions.visibilityOf(driver.findElement(helloText)));

		Assert.assertTrue(driver.findElement(helloText).isDisplayed());

	}

	// @Test
	public void TC_04_LoadingIcon_HelloText_NoLongerInDOM() {
		driver.get("http://the-internet.herokuapp.com/dynamic_loading/2");

		// driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		waitExplicit = new WebDriverWait(driver, 5);

		// Invisible + not in DOM
		System.out.println("Start time = " + getDateTimeSecond());
		waitExplicit.until(ExpectedConditions.invisibilityOfElementLocated(loadingIcon));
		System.out.println("End time = " + getDateTimeSecond());

		// Invisible + not in DOM
		System.out.println("Start time = " + getDateTimeSecond());
		waitExplicit.until(ExpectedConditions.invisibilityOfElementLocated(helloText));
		System.out.println("End time = " + getDateTimeSecond());

		System.out.println("Start time = " + getDateTimeSecond());
		driver.findElement(startBtn).click();
		System.out.println("End time = " + getDateTimeSecond());

		// Invisible + in DOM
		System.out.println("Start time = " + getDateTimeSecond());
		waitExplicit.until(ExpectedConditions.invisibilityOfElementLocated(loadingIcon));
		System.out.println("End time = " + getDateTimeSecond());

		// Invisible + in DOM
		System.out.println("Start time = " + getDateTimeSecond());
		waitExplicit.until(ExpectedConditions.invisibilityOfElementLocated(startBtn));
		System.out.println("End time = " + getDateTimeSecond());
	}

	@Test
	public void TC_04_HelloWorldTextVisibleFailed_01() {
		driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
		waitExplicit = new WebDriverWait(driver, 5);

		driver.get("http://the-internet.herokuapp.com/dynamic_loading/2");
		System.out.println("Start time before click = " + getDateTimeSecond());
		driver.findElement(startBtn).click();
		System.out.println("Start time after click = " + getDateTimeSecond());

		waitExplicit.until(ExpectedConditions.visibilityOfElementLocated(helloText));
		System.out.println("End time = " + getDateTimeSecond());
	}

	@Test
	public void TC_04_HelloWorldTextVisibleFailed_02() {
		driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
		waitExplicit = new WebDriverWait(driver, 4);

		driver.get("http://the-internet.herokuapp.com/dynamic_loading/2");
		System.out.println("Start time before click = " + getDateTimeSecond());
		driver.findElement(startBtn).click();
		System.out.println("Start time after click = " + getDateTimeSecond());

		waitExplicit.until(ExpectedConditions.visibilityOfElementLocated(helloText));
		System.out.println("End time = " + getDateTimeSecond());
	}

	@Test
	public void TC_04_HelloWorldTextVisibleFailed_03() {
		driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
		waitExplicit = new WebDriverWait(driver, 4);

		driver.get("http://the-internet.herokuapp.com/dynamic_loading/2");
		System.out.println("Start time before click = " + getDateTimeSecond());
		driver.findElement(startBtn).click();
		System.out.println("Start time after click = " + getDateTimeSecond());

		waitExplicit.until(ExpectedConditions.visibilityOfElementLocated(helloText));
		System.out.println("End time = " + getDateTimeSecond());
	}

	@Test
	public void TC_04_HelloWorldTextVisibleFailed_04() {
		driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
		waitExplicit = new WebDriverWait(driver, 4);

		driver.get("http://the-internet.herokuapp.com/dynamic_loading/2");
		System.out.println("Start time before click = " + getDateTimeSecond());
		driver.findElement(startBtn).click();
		System.out.println("Start time after click = " + getDateTimeSecond());

		waitExplicit.until(ExpectedConditions.visibilityOfElementLocated(helloText));
		System.out.println("End time = " + getDateTimeSecond());
	}

	@Test
	public void TC_04_HelloWorldTextVisibleFailed_05() {
		driver.manage().timeouts().implicitlyWait(4, TimeUnit.SECONDS);
		waitExplicit = new WebDriverWait(driver, 4);

		driver.get("http://the-internet.herokuapp.com/dynamic_loading/2");
		System.out.println("Start time before click = " + getDateTimeSecond());
		driver.findElement(startBtn).click();
		System.out.println("Start time after click = " + getDateTimeSecond());

		waitExplicit.until(ExpectedConditions.visibilityOfElementLocated(helloText));
		System.out.println("End time = " + getDateTimeSecond());
	}

	@Test
	public void TC_05_AjaxLoading_WithoutExplicitWait() {
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.get("https://demos.telerik.com/aspnet-ajax/ajaxloadingpanel/functionality/explicit-show-hide/defaultcs.aspx");

		Assert.assertTrue(driver.findElement(By.xpath("//div[@class='contentWrapper']")).isDisplayed());

		String beforeDateSelected = driver.findElement(By.xpath("//span[@id='ctl00_ContentPlaceholder1_Label1']")).getText();
		System.out.println("Date before selected is: " + beforeDateSelected);

		driver.findElement(By.xpath("//a[text() = '17']")).click();

		Assert.assertTrue(driver.findElement(By.xpath("//span[@id='ctl00_ContentPlaceholder1_Label1' and text()='Wednesday, April 17, 2019']")).isDisplayed());

		Assert.assertTrue(driver.findElement(By.xpath("//a[text() = '17']/parent::td[@class='rcSelected']")).isDisplayed());

		String afterDateSelected = driver.findElement(By.xpath("//span[@id='ctl00_ContentPlaceholder1_Label1']")).getText().trim();
		System.out.println("Date after selected is: " + afterDateSelected);

		Assert.assertEquals(afterDateSelected, "Wednesday, April 17, 2019");
	}

	@Test
	public void TC_05_AjaxLoading_WithExplicitWait() {
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.get("https://demos.telerik.com/aspnet-ajax/ajaxloadingpanel/functionality/explicit-show-hide/defaultcs.aspx");
		waitExplicit = new WebDriverWait(driver, 30);

		waitExplicit.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='contentWrapper']")));
		// Assert.assertTrue(driver.findElement(By.xpath("//div[@class='contentWrapper']")).isDisplayed());

		String beforeDateSelected = driver.findElement(By.xpath("//span[@id='ctl00_ContentPlaceholder1_Label1']")).getText();
		System.out.println("Date before selected is: " + beforeDateSelected);

		driver.findElement(By.xpath("//a[text() = '17']")).click();

		waitExplicit.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//div[@class='raDiv']")));
		// Assert.assertTrue(driver.findElement(By.xpath("//span[@id='ctl00_ContentPlaceholder1_Label1' and text()='Wednesday, April 17, 2019']")).isDisplayed());

		waitExplicit.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[text() = '17']/parent::td[@class='rcSelected']")));
		// Assert.assertTrue(driver.findElement(By.xpath("//a[text() = '17']/parent::td[@class='rcSelected']")).isDisplayed());

		String afterDateSelected = driver.findElement(By.xpath("//span[@id='ctl00_ContentPlaceholder1_Label1']")).getText().trim();
		System.out.println("Date after selected is: " + afterDateSelected);

		Assert.assertEquals(afterDateSelected, "Wednesday, April 17, 2019");
	}

	@Test
	public void TC_06_FluentWait() {
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.get("https://daominhdam.github.io/fluent-wait/");
		waitExplicit = new WebDriverWait(driver, 30);
		
		WebElement countdount =  driver.findElement(By.xpath("//div[@id='javascript_countdown_time']"));
		waitExplicit.until(ExpectedConditions.visibilityOf(countdount));

		// Khởi tạo Fluent wait
		new FluentWait<WebElement>(countdount)
		           // Tổng time wait là 15s
		           .withTimeout(Duration.ofSeconds(15))
		            // Tần số mỗi 1s check 1 lần
		            .pollingEvery(Duration.ofSeconds(1))
		           // Nếu gặp exception là find ko thấy element sẽ bỏ  qua
		            .ignoring(NoSuchElementException.class)
		            // Kiểm tra điều kiện
		            .until(new Function<WebElement, Boolean>() {
		                public Boolean apply(WebElement element) {
		                           // Kiểm tra điều kiện countdount = 00
		                           boolean flag =  element.getText().endsWith("00");
		                           System.out.println("Time = " +  element.getText());
		                           // return giá trị cho function apply
		                           return flag;
		                }
		           });
	}

	public Date getDateTimeSecond() {
		Date date = new Date();
		date = new Date(date.getTime());
		return date;
	}

	@AfterTest
	public void afterClass() {
		driver.quit();
	}

}