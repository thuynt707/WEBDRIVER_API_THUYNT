package selenium;

import java.util.Date;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
//import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class Topic_10_JavascriptExecutor {
	WebDriver driver;
	String dateFormat1 = "DD/MM/YYYY";
	String dateFormat2 = "YYYY-DD-MM";
	Date before;
	// Declare variable data;
	String customerName;
	String gender;
	String dob;
	String address;
	String city;
	String state;
	String pin;
	String phoneNo;
	String email;
	String password;
	
	String firstName;
	String lastName;
	String emailAdd;
	String psw;
	String confirmPassword;
	
	
	// Locate element (new customer - live.guru99.com)
	By customerNameTextbox = By.xpath("//input[@name='name']");
	By genderRadio = By.xpath("//input[@value='m']");
	By dateOfBirth = By.xpath("//input[@name='dob']");
	By addressTextarea = By.xpath("//textarea[@name='addr']");
	By cityTextbox = By.xpath("//input[@name='city']");
	By stateTextbox = By.xpath("//input[@name='state']");
	By pinTextbox = By.xpath("//input[@name='pinno']");
	By phoneTextbox = By.xpath("//input[@name='telephoneno']");
	By emailTextbox = By.xpath("//input[@name='emailid']");
	By passwordTextbox = By.xpath("//input[@name='password']");
	By submitButton = By.xpath("//input[@name='sub']");
	
	// Locate Element's value
	By customerNameValue = By.xpath("//td[text() = 'Customer Name']/following-sibling::td");
	By genderValue = By.xpath("//td[text() = 'Gender']/following-sibling::td");
	By birthDateValue = By.xpath("//td[text() = 'Birthdate']/following-sibling::td");
	By addressValue = By.xpath("//td[text() = 'Address']/following-sibling::td");
	By cityValue = By.xpath("//td[text() = 'City']/following-sibling::td");
	By stateValue = By.xpath("//td[text() = 'State']/following-sibling::td");
	By pinValue = By.xpath("//td[text() = 'Pin']/following-sibling::td");
	By phoneValue = By.xpath("//td[text() = 'Mobile No.']/following-sibling::td");
	By emailValue = By.xpath("//td[text() = 'Email']/following-sibling::td");
	
	// Locate element (create new account - live.guru99.com)
	By firstNameTextbox = By.xpath("//input[@id='firstname']");
	By lastNameTextbox = By.xpath("//input[@id='lastname']");
	By emailAddTexbox = By.xpath("//input[@id='email_address']");
	By pswTextbox = By.xpath("//input[@id='password']");
	By confirmPasswordTextbox = By.xpath("//input[@id='confirmation']");
	By registerBtn = By.xpath("//button[@title='Register']");

	@BeforeTest
	public void beforeTest() {
		//driver = new FirefoxDriver();
		System.setProperty("webdriver.chrome.driver", ".\\driver\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		
		// Data test new customer
		customerName = "Automation Test";
		gender = "male";
		dob = "07/07/1989";
		address = "121 Kim Nguu";
		city = "Ha Noi";
		state = "Hai Ba Trung";
		pin = "100000";
		phoneNo = "0987654321";
		email = "autotest" + randomNumber() + "@gmail.com";
		password = "123456";
		
		//Data test create new account
		firstName = "Auto";
		lastName = "Test";
		emailAdd = "Selenium" + randomNumber() + "@gmail.com";
		psw = "abcD123";
		confirmPassword = "abcD123";
		
	}

	@Test
	public void TC_01_JE() {
		//driver.get("http://live.guru99.com/");
		navigateToUrlByJS("http://live.guru99.com");
		
		String domainName = executeForBrowser("return document.domain");
		Assert.assertEquals(domainName, "live.guru99.com");
		
		String urlName = executeForBrowser("return document.URL");
		Assert.assertEquals(urlName, "http://live.guru99.com/");
		
		WebElement mobileLink = driver.findElement(By.xpath("//a[text()='Mobile']"));
		highlightElement(mobileLink);
		clickToElementByJS(mobileLink);
		
		WebElement samsungCartButton = driver.findElement(By.xpath("//a[text()='Samsung Galaxy']/parent::h2/following-sibling::div[@class='actions']/button"));
		highlightElement(samsungCartButton);
		clickToElementByJS(samsungCartButton);
		
		String innerText = executeForBrowser("return document.documentElement.innerText");
		Assert.assertTrue(innerText.contains("Samsung Galaxy was added to your shopping cart."));
		Assert.assertEquals(driver.findElement(By.xpath("//li[@class='success-msg']//span")).getText(), "Samsung Galaxy was added to your shopping cart.");
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class='success-msg']//span[text()='Samsung Galaxy was added to your shopping cart.']")).isDisplayed());
		
		WebElement privacyPolicy = driver.findElement(By.xpath("//a[text()='Privacy Policy']"));
		highlightElement(privacyPolicy);
		clickToElementByJS(privacyPolicy);
		String privacyPolicyPageTitle = executeForBrowser("return document.title");
		Assert.assertEquals(privacyPolicyPageTitle, "Privacy Policy");
		
		scrollToBottomPage();
		WebElement wishlistRow = driver.findElement(By.xpath("//th[text()='WISHLIST_CNT']/following-sibling::td[text()='The number of items in your Wishlist.']"));
		highlightElement(wishlistRow);
		Assert.assertTrue(wishlistRow.isDisplayed());
		
		navigateToUrlByJS("http://demo.guru99.com/v4/");
		String domainGuruName = executeForBrowser("return document.domain");
		Assert.assertEquals(domainGuruName, "demo.guru99.com");
	}
	
	@Test
	public void TC_02_RemoveAttribute() {
		driver.get("http://demo.guru99.com/v4");
		driver.findElement(By.xpath("//input[@name='uid']")).sendKeys("mngr181358");
		driver.findElement(By.xpath("//input[@name='password']")).sendKeys("berydUp");
		driver.findElement(By.xpath("//input[@name='btnLogin']")).click();
		Assert.assertTrue(driver.findElement(By.xpath("//marquee[text()=\"Welcome To Manager's Page of Guru99 Bank\"]")).isDisplayed());
		
		driver.findElement(By.xpath("//a[contains(text(),'New Customer')]")).click();
		Assert.assertTrue(driver.findElement(By.xpath("//p[text()='Add New Customer']")).isDisplayed());

		// Input data for create new customer
		driver.findElement(customerNameTextbox).sendKeys(customerName);
		driver.findElement(genderRadio).click();
		
		WebElement dateOfBirthTextbox = driver.findElement(dateOfBirth);
		removeAttributeInDOM(dateOfBirthTextbox, "type");
		dateOfBirthTextbox.sendKeys(dob);
		
		driver.findElement(addressTextarea).sendKeys(address);
		driver.findElement(cityTextbox).sendKeys(city);
		driver.findElement(stateTextbox).sendKeys(state);
		driver.findElement(pinTextbox).sendKeys(pin);
		driver.findElement(phoneTextbox).sendKeys(phoneNo);
		driver.findElement(emailTextbox).sendKeys(email);
		driver.findElement(passwordTextbox).sendKeys(password);
		driver.findElement(submitButton).click();
		
		Assert.assertTrue(driver.findElement(By.xpath("//p[@class='heading3' and text()='Customer Registered Successfully!!!']")).isDisplayed());
	}
	
	@Test
	public void TC_03_CreateAnAccount() throws Exception {
		driver.get("http://live.guru99.com");
		
		WebElement myAccountLink = driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account'][contains(text(),'My Account')]"));
		highlightElement(myAccountLink);
		clickToElementByJS(myAccountLink);
		
		WebElement createAccountBtn = driver.findElement(By.xpath("//span[contains(text(),'Create an Account')]"));
		highlightElement(createAccountBtn);
		clickToElementByJS(createAccountBtn);
		
		WebElement firstNameE = driver.findElement(firstNameTextbox);
		highlightElement(firstNameE);
		sendkeyToElementByJS(firstNameE, firstName);
		
		WebElement lastNameE = driver.findElement(lastNameTextbox);
		highlightElement(lastNameE);
		sendkeyToElementByJS(lastNameE, lastName);
		
		WebElement emailE = driver.findElement(emailAddTexbox);
		highlightElement(emailE);
		sendkeyToElementByJS(emailE, emailAdd);
		
		WebElement passwordE = driver.findElement(pswTextbox);
		highlightElement(passwordE);
		sendkeyToElementByJS(passwordE, psw);
		
		WebElement confirmPasswordE = driver.findElement(confirmPasswordTextbox);
		highlightElement(confirmPasswordE);
		sendkeyToElementByJS(confirmPasswordE, confirmPassword);
		
		scrollToBottomPage();
		WebElement registerBtnE = driver.findElement(registerBtn);
		highlightElement(registerBtnE);
		clickToElementByJS(registerBtnE);
		Thread.sleep(3000);
		String innerText = executeForBrowser("return document.documentElement.innerText");
		Assert.assertTrue(innerText.contains("Thank you for registering with Main Website Store."));
		
		WebElement account = driver.findElement(By.xpath("//span[@class='label'][contains(text(),'Account')]"));
		highlightElement(account);
		account.click();
		WebElement logOut = driver.findElement(By.xpath("//a[@title='Log Out']"));
		highlightElement(logOut);
		clickToElementByJS(logOut);
		
		Thread.sleep(7000);
		JavascriptExecutor js = (JavascriptExecutor)driver;
		String homePageTitle =  js.executeScript("return document.title;").toString();
		Assert.assertEquals(homePageTitle, "Home page");
		
	}
	
	@AfterTest
	public void afterTest() {
		driver.quit();
	}
	public void highlightElement(WebElement element) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].style.border='6px groove red'", element);
        try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
    }

    public String executeForBrowser(String javaSript) {
            JavascriptExecutor js = (JavascriptExecutor) driver;
            return (String) js.executeScript(javaSript);
    }

    public Object clickToElementByJS(WebElement element) {
            JavascriptExecutor js = (JavascriptExecutor) driver;
            return js.executeScript("arguments[0].click();", element);
    }

    public Object sendkeyToElementByJS(WebElement element, String value) {
           JavascriptExecutor js = (JavascriptExecutor) driver;
           return js.executeScript("arguments[0].setAttribute('value', '" + value + "')", element);
    }

    public void removeAttributeInDOM(WebElement element, String attribute) {
            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("arguments[0].removeAttribute('" + attribute + "');", element);
            try {
				Thread.sleep(3000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
    }

    public Object scrollToBottomPage() {
            JavascriptExecutor js = (JavascriptExecutor) driver;
            return js.executeScript("window.scrollBy(0,document.body.scrollHeight)");
    }

    public Object navigateToUrlByJS(String url) {
            JavascriptExecutor js = (JavascriptExecutor) driver;
            return js.executeScript("window.location = '" + url + "'");
    }
    
	public int randomNumber() {
		Random random = new Random();
		return random.nextInt(999999);
	}

}