package selenium;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_02_Xpath_Css {
	WebDriver driver;

	@BeforeClass
	public void beforeClass() {
		/*
		 * System.getProperty("webdriver.chrome.driver", ".\\driver\\chromedriver.exe");
		 * driver = new ChromeDriver();
		 */
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		
	}

	@Test
	public void TC_01_LoginWithEmailPasswordEmpty() {
		driver.get("http://live.guru99.com/");
		driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']")).click();
		
		driver.findElement(By.id("email")).sendKeys("");
		driver.findElement(By.name("login[password]")).sendKeys("");
		driver.findElement(By.xpath("//button[@title='Login']")).click();
		
		String errorEmailMessage = driver.findElement(By.id("advice-required-entry-email")).getText();
		Assert.assertEquals(errorEmailMessage, "This is a required field.");
		
		String errorPassMessage = driver.findElement(By.id("advice-required-entry-pass")).getText();
		Assert.assertEquals(errorPassMessage, "This is a required field.");
	}
	
	@Test
	public void TC_02_LoginWithEmailInvalid() {
		driver.get("http://live.guru99.com/");
		driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']")).click();
		
		driver.findElement(By.id("email")).sendKeys("123@123");
		driver.findElement(By.name("login[password]")).sendKeys("");
		driver.findElement(By.xpath("//button[@title='Login']")).click();
		
		String errorEmailMessage = driver.findElement(By.id("advice-validate-email-email")).getText();
		Assert.assertEquals(errorEmailMessage, "Please enter a valid email address. For example johndoe@domain.com.");
	}
	
	@Test
	public void TC_03_LoginWithPassLessThanSixChar() {
		driver.get("http://live.guru99.com/");
		driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']")).click();
		
		driver.findElement(By.id("email")).sendKeys("automation09@gmail.com");
		driver.findElement(By.name("login[password]")).sendKeys("12345");
		driver.findElement(By.xpath("//button[@title='Login']")).click();
		
		String errorPassMessage = driver.findElement(By.id("advice-validate-password-pass")).getText();
		Assert.assertEquals(errorPassMessage, "Please enter 6 or more characters without leading or trailing spaces.");
	}
	
	@Test
	public void TC_04_LoginWithPassIncorrect() {
		driver.get("http://live.guru99.com/");
		driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']")).click();
		
		driver.findElement(By.id("email")).sendKeys("automation@gmail.com");
		driver.findElement(By.name("login[password]")).sendKeys("123123123");
		driver.findElement(By.xpath("//button[@title='Login']")).click();
		
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		String errorPassMessage = driver.findElement(By.xpath("//span[text()='Invalid login or password.']")).getText();
		Assert.assertEquals(errorPassMessage, "Invalid login or password.");
		
	}
	
	@Test
	public void TC_05_CreateAnAccount() throws InterruptedException {
		driver.get("http://live.guru99.com/");
		driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']")).click();
		
		//Click Create an Account button
		driver.findElement(By.xpath("//a[@title='Create an Account']")).click();
		
		//Input First Name/ Last Name/ Email Address/ Password/ Confirm Password
		driver.findElement(By.id("firstname")).sendKeys("Test");
		driver.findElement(By.id("lastname")).sendKeys("Automation");
		driver.findElement(By.id("email_address")).sendKeys("automationTest" + randomNumber() + "@gmail.com");
		driver.findElement(By.id("password")).sendKeys("123456");
		driver.findElement(By.xpath("//input[@title='Confirm Password']")).sendKeys("123456");
		
		//Click button Register
		driver.findElement(By.xpath("//button[@title='Register']")).click();
		
		String successMsg = driver.findElement(By.xpath(".//div[@class='dashboard']//span[text()='Thank you for registering with Main Website Store.']")).getText();
		Assert.assertEquals(successMsg, "Thank you for registering with Main Website Store.");
		
		//Click Logout
		driver.findElement(By.xpath("//a[@class='skip-link skip-account']")).click();
		driver.findElement(By.xpath("//div[@id='header-account']//a[@title='Log Out']")).click();
		
		//Verify back to Home page
		Thread.sleep(5000);
		String homePageTitle = driver.getTitle();
		System.out.println(homePageTitle);
		Assert.assertEquals(homePageTitle, "Home page");
		
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}
	
	public int randomNumber() {
		Random random = new Random();
		return random.nextInt(999999);
	}
}
