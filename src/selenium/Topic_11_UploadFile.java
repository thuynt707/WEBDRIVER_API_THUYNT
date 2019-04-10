package selenium;

import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class Topic_11_UploadFile {
	WebDriver driver;
	JavascriptExecutor jsExecutor;
	WebDriverWait waitExplicit;

	String rootFolder = System.getProperty("user.dir");
	String fileName01 = "Image01.jpg";
	String fileName02 = "Image02.png";
	String fileName03 = "Image03.png";

	// get path of file
	String filePath01 = rootFolder + "\\uploadFiles\\" + fileName01;
	String filePath02 = rootFolder + "\\uploadFiles\\" + fileName02;
	String filePath03 = rootFolder + "\\uploadFiles\\" + fileName03;
	String[] files = { filePath01, filePath02, filePath03 };

	String chromePath = rootFolder + "\\uploadFiles\\chrome.exe";
	String firefoxPath = rootFolder + "\\uploadFiles\\firefox.exe";
	String iePath = rootFolder + "\\uploadFiles\\ie.exe";

	String firstName = "Thuy";
	String subFolderName = "Thuy" + randomNumber();
	String email = "Thuy" + randomNumber() + "@gmail.com";

	@BeforeTest
	public void beforeTest() {
		// Chrome
//		System.setProperty("webdriver.chrome.driver", ".\\driver\\chromedriver.exe");
//		driver = new ChromeDriver();

		// Firefox
//		System.setProperty("webdriver.gecko.driver", ".\\driver\\geckodriver.exe");
//		driver = new FirefoxDriver();

		// IE
		System.setProperty("webdriver.ie.driver", ".\\driver\\IEDriverServer.exe");
		driver = new InternetExplorerDriver();

		jsExecutor = (JavascriptExecutor) driver;
		waitExplicit = new WebDriverWait(driver, 60);

		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();

	}

	@Test
	public void TC_01_Sendkeys_Upload_Queue() throws Exception {
		// 1 - Go to url
		driver.get("http://blueimp.github.com/jQuery-File-Upload/");

		// 2 - Send file path of 3 files by Sendkeys
		for (String file : files) {
			WebElement addFilesBtn = driver.findElement(By.xpath("//input[@type = 'file']"));
			addFilesBtn.sendKeys(file);
			Thread.sleep(1000);
		}

		// 3 - Verify 3 files are selected successful
		Assert.assertTrue(driver.findElement(By.xpath("//p[text() = '" + fileName01 + "']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//p[text() = '" + fileName02 + "']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//p[text() = '" + fileName03 + "']")).isDisplayed());

		// 4 - Click button Start upload
		driver.findElement(By.xpath("//span[text()='Start upload']")).click();

		// 5 - Verify 3 selected files are uploaded successful
		Assert.assertTrue(driver.findElement(By.xpath("//a[text() = '" + fileName01 + "']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//a[text() = '" + fileName02 + "']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//a[text() = '" + fileName03 + "']")).isDisplayed());

	}

	@Test
	public void TC_02_Sendkeys_Upload_Multiple_One_Time() throws Exception {
		// 1 - Go to url
		driver.get("http://blueimp.github.com/jQuery-File-Upload/");
		Thread.sleep(2000);

		// 2 - Send file path of 3 files by Sendkeys
		WebElement addFilesBtn = driver.findElement(By.xpath("//input[@type = 'file']"));
		addFilesBtn.sendKeys(filePath01 + "\n" + filePath02 + "\n" + filePath03);

		// 3 - Verify 3 files are selected successful
		Assert.assertTrue(driver.findElement(By.xpath("//p[text() = '" + fileName01 + "']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//p[text() = '" + fileName02 + "']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//p[text() = '" + fileName03 + "']")).isDisplayed());

		Thread.sleep(1000);

		// 4 - Click button Start upload
		List<WebElement> startButtons = driver.findElements(By.xpath("//table//button[@class='btn btn-primary start']"));
		for (WebElement startButton : startButtons) {
			if (driver.toString().contains("internet explorer")) {
				clickToElementByJS(startButton);
				System.out.println("Click by JS!");
			} else {
				startButton.click();
				System.out.println("Click by Selenium Builtin!");
			}

			Thread.sleep(1000);

		}

		// 5 - Verify 3 selected files are uploaded successful
		Assert.assertTrue(driver.findElement(By.xpath("//a[text() = '" + fileName01 + "']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//a[text() = '" + fileName02 + "']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//a[text() = '" + fileName03 + "']")).isDisplayed());

	}

	@Test
	public void TC_03_Upload_file_by_AutoIT() throws Exception {
		driver.get("http://blueimp.github.com/jQuery-File-Upload/");
		Thread.sleep(3000);
		for(String file: files) {
			
			if (driver.toString().contains("chrome") || driver.toString().contains("firefox")) {
				System.out.println("Go to Chrome");
				WebElement addFilesBtn = driver.findElement(By.cssSelector(".fileinput-button"));
				addFilesBtn.click();
				Thread.sleep(1000);
				
			} else {
				System.out.println("Go to IE");
				WebElement uploadFile = driver.findElement(By.xpath("//input[@type='file']"));
				clickToElementByJS(uploadFile);
				Thread.sleep(1000);
				
			}
			
//			Runtime.getRuntime().exec(new String[] { chromePath, file });
			Runtime.getRuntime().exec(new String[] { firefoxPath, file });
//			Runtime.getRuntime().exec(new String[] { iePath, file });
			Thread.sleep(1000);
		}
		
		//Check files are selected successful
		Assert.assertTrue(driver.findElement(By.xpath("//p[text()= '" + fileName01 + "']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//p[text()= '" + fileName02 + "']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//p[text()= '" + fileName03 + "']")).isDisplayed());

		List<WebElement> startButtons = driver.findElements(By.xpath("//span[text()='Start']"));

		for (WebElement start : startButtons) {

			start.click();

			Thread.sleep(1000);
		}
		
		//Check files are uploaded successful
		Assert.assertTrue(driver.findElement(By.xpath("//a[text()='" + fileName01 + "']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//a[text()='" + fileName02 + "']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//a[text()='" + fileName03 + "']")).isDisplayed());
	}
	
	@Test
	public void TC_04_Robot() throws Exception {
		driver.get("http://blueimp.github.com/jQuery-File-Upload/");
		Thread.sleep(3000);
		
		for(String file: files) {
			// Specify the file location with extension
	        StringSelection select = new  StringSelection(file);

	        // Copy to clipboard
	        Toolkit.getDefaultToolkit().getSystemClipboard().setContents(select, null);

	        if (driver.toString().contains("chrome")  || driver.toString().contains("firefox")) {
	            WebElement uploadFile =  driver.findElement(By.cssSelector(".fileinput-button"));
	            uploadFile.click();
	            Thread.sleep(1000);
	            
	        } else {
	            System.out.println("Go to IE");
	            WebElement uploadFile =  driver.findElement(By.xpath("//input[@type='file']"));
	            clickToElementByJS(uploadFile);
	            Thread.sleep(1000);
	        }
	        
	        Robot robot = new Robot();
	        Thread.sleep(1000);

	        // Press Enter
	        robot.keyPress(KeyEvent.VK_ENTER);
	        robot.keyRelease(KeyEvent.VK_ENTER);

	        // Press Ctrl-V
	        robot.keyPress(KeyEvent.VK_CONTROL);
	        robot.keyPress(KeyEvent.VK_V);

	        // Release Ctrl-V
	        robot.keyRelease(KeyEvent.VK_CONTROL);
	        robot.keyRelease(KeyEvent.VK_V);
	        Thread.sleep(1000);

	        // Press Enter
	        robot.keyPress(KeyEvent.VK_ENTER);
	        robot.keyRelease(KeyEvent.VK_ENTER);
		}
		
		// Check files are uploaded successful
		Assert.assertTrue(driver.findElement(By.xpath("//p[text()='" + fileName01 + "']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//p[text()='" + fileName02 + "']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//p[text()='" + fileName03 + "']")).isDisplayed());
		
		List<WebElement> startButtons = driver.findElements(By.xpath("//span[text()='Start']"));

		for (WebElement start : startButtons) {

			start.click();

			Thread.sleep(1000);
		}
		
		//Check files are uploaded successful
		Assert.assertTrue(driver.findElement(By.xpath("//a[text()='" + fileName01 + "']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//a[text()='" + fileName02 + "']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//a[text()='" + fileName03 + "']")).isDisplayed());
	}

	@Test
	public void TC_05_Upload_files() throws Exception {
		
//		Step 01 - Open URL: 'https://encodable.com/uploaddemo/'
		driver.get("https://encodable.com/uploaddemo/");
		
//		Step 02 - Choose Files to Upload (Ex: UploadFile.jpg)
		WebElement chooseFileBtn = driver.findElement(By.xpath("//input[@type='file']"));
		chooseFileBtn.sendKeys(filePath01);
		
//		Step 03 - Select dropdown (Upload to: /uploaddemo/files/)
		WebElement uploadToDropdown = driver.findElement(By.xpath("//select[@name='subdir1']"));
		Select uploadToSelect = new Select(uploadToDropdown);
		uploadToSelect.selectByVisibleText("/uploaddemo/files/");
		
//		Step 04 - Input random folder to 'New subfolder? Name:) textbox (Ex: dam1254353)
		WebElement subfolder = driver.findElement(By.xpath("//input[@id='newsubdir1']"));
		subfolder.sendKeys(subFolderName);
		Thread.sleep(1000);
		
//		Step 05 - Input email and firstname (dam@gmail.com/ DAM DAO)
		WebElement emailTextbox = driver.findElement(By.xpath("//input[@name='email_address']"));
		emailTextbox.clear();
		emailTextbox.sendKeys(email);
		
		
		WebElement firstnameTextbox = driver.findElement(By.xpath("//input[@name='first_name']"));
		firstnameTextbox.clear();
		firstnameTextbox.sendKeys(firstName);
		Thread.sleep(1000);
		
//		Step 06 - Click Begin Upload (Note: Wait for page load successfully)
		WebElement uploadBtn = driver.findElement(By.xpath("//input[@id='uploadbutton']"));
		
		if(driver.toString().contains("internet explorer")) {
			clickToElementByJS(uploadBtn);
		}else {
			uploadBtn.click();
		}	
		Thread.sleep(5000);
		
//		Step 07 - Verify information
//	    + Email Address: dam@gmail.com/ First Name: DAM DAO
//	    + File name: UploadFile.jpg
		String innerText = (String) jsExecutor.executeScript("return document.documentElement.innerText");
		Assert.assertTrue(innerText.contains(email));
		Assert.assertTrue(innerText.contains(firstName));
		Assert.assertTrue(innerText.contains(fileName01));
		
//		Step 08 - Click 'View Uploaded Files' link
		WebElement viewUploadLink = driver.findElement(By.xpath("//a[contains(text(),'View Uploaded Files')]"));
		if(driver.toString().contains("internet explorer")) {
			clickToElementByJS(viewUploadLink);
		}else {
			viewUploadLink.click();
		}	
		Thread.sleep(2000);
		
//		Step 09 - Click to random folder (Ex: dam1254353)
		clickToElementByJS(driver.findElement(By.xpath("//a[text()='"+subFolderName+"']")));
		Thread.sleep(2000);
		
//		Step 10 - Verify file name exist in folder (UploadFile.jpg)
		Assert.assertTrue(driver.findElement(By.xpath("//a[text()='"+fileName01+"']")).isDisplayed());
	
	}

	public int randomNumber() {
		Random random = new Random();
		int randomNumber = random.nextInt(9999);
		return randomNumber;
	}

	public Object clickToElementByJS(WebElement element) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		return js.executeScript("arguments[0].click();", element);
	}

	@AfterTest
	public void afterTest() {
		driver.quit();
	}

}