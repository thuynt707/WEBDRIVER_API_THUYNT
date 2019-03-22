package selenium;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_04_TextboxTextarea {
	String dateFormat1 = "DD/MM/YYYY";
	String dateFormat2 = "YYYY-DD-MM";
	Date before;
	WebDriver driver;
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
	String customerID;
	String editAddress, editCity, editState, editPin, editPhoneNo, editEmail;
	
	
	// Locate element
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
	
	//Locate Element's value
	By customerNameValue = By.xpath("//td[text() = 'Customer Name']/following-sibling::td");
	By genderValue = By.xpath("//td[text() = 'Gender']/following-sibling::td");
	By birthDateValue = By.xpath("//td[text() = 'Birthdate']/following-sibling::td");
	By addressValue = By.xpath("//td[text() = 'Address']/following-sibling::td");
	By cityValue = By.xpath("//td[text() = 'City']/following-sibling::td");
	By stateValue = By.xpath("//td[text() = 'State']/following-sibling::td");
	By pinValue = By.xpath("//td[text() = 'Pin']/following-sibling::td");
	By phoneValue = By.xpath("//td[text() = 'Mobile No.']/following-sibling::td");
	By emailValue = By.xpath("//td[text() = 'Email']/following-sibling::td");
	
	@BeforeClass
	public void beforeClass() throws ParseException {
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		driver.get("http://demo.guru99.com/v4");
		driver.findElement(By.xpath("//input[@name='uid']")).sendKeys("mngr181358");
		driver.findElement(By.xpath("//input[@name='password']")).sendKeys("berydUp");
		driver.findElement(By.xpath("//input[@name='btnLogin']")).click();
		Assert.assertTrue(driver.findElement(By.xpath("//marquee[text()=\"Welcome To Manager's Page of Guru99 Bank\"]")).isDisplayed());

		// Data test
		customerName = "Automation Test";
		gender = "male";
		dob = "07/07/1989";
		before = new SimpleDateFormat(dateFormat1).parse(dob);
		
		// create date object which has value of -> before
		//before = new Date();
		
		// get actual date value -> to date object -> after
		// compare before and after 
		
		address = "121 Kim Nguu";
		city = "Ha Noi";
		state = "Hai Ba Trung";
		pin = "100000";
		phoneNo = "0987654321";
		email = "autotest" + randomNumber() + "@gmail.com";
		password = "123456";

		// Edit data test
		editAddress = "121 Kim Ma";
		editCity = "Ha Tay";
		editState = "Ba Dinh";
		editPin = "200000";
		editPhoneNo = "0987654322";
		editEmail = "editautotest" + randomNumber() + "@gmail.com";
	}

	@Test
	public void TC_01_CreateNewCustomer() throws Exception {
		driver.findElement(By.xpath("//a[contains(text(),'New Customer')]")).click();
		Assert.assertTrue(driver.findElement(By.xpath("//p[text()='Add New Customer']")).isDisplayed());

		// Input data for create new customer
		driver.findElement(customerNameTextbox).sendKeys(customerName);
		driver.findElement(genderRadio).click();
		driver.findElement(dateOfBirth).sendKeys(dob);
		driver.findElement(addressTextarea).sendKeys(address);
		driver.findElement(cityTextbox).sendKeys(city);
		driver.findElement(stateTextbox).sendKeys(state);
		driver.findElement(pinTextbox).sendKeys(pin);
		driver.findElement(phoneTextbox).sendKeys(phoneNo);
		driver.findElement(emailTextbox).sendKeys(email);
		driver.findElement(passwordTextbox).sendKeys(password);
		driver.findElement(submitButton).click();
		
		Assert.assertTrue(driver.findElement(By.xpath("//p[@class='heading3' and text()='Customer Registered Successfully!!!']")).isDisplayed());

		// Verify expected data = actual data
		
		Assert.assertEquals(driver.findElement(customerNameValue).getText(), customerName);
		Assert.assertEquals(driver.findElement(genderValue).getText(), gender);
		
		Date after = new SimpleDateFormat(dateFormat2).parse(driver.findElement(birthDateValue).getText());
		Assert.assertEquals(before.compareTo(after), 0);
	
		Assert.assertEquals(driver.findElement(addressValue).getText(), address);
		Assert.assertEquals(driver.findElement(cityValue).getText(), city);
		Assert.assertEquals(driver.findElement(stateValue).getText(), state);
		Assert.assertEquals(driver.findElement(pinValue).getText(), pin);
		Assert.assertEquals(driver.findElement(phoneValue).getText(), phoneNo);
		Assert.assertEquals(driver.findElement(emailValue).getText(), email);

		customerID = driver.findElement(By.xpath("//td[text()='Customer ID']/following-sibling::td")).getText();
		System.out.println("TC_01_CreateNewCustomer(): CustomerID = " + customerID);
	}

	@Test
	public void TC_02_EditCustomer() {
		System.out.println("TC_02_EditCustomer(): CustomerID = " + customerID);
		driver.findElement(By.xpath("//a[text()='Edit Customer']")).click();
		driver.findElement(By.xpath("//input[@name='cusid']")).sendKeys(customerID);
		driver.findElement(By.xpath("//input[@name='AccSubmit']")).click();
		
		// Input data for edit customer
		driver.findElement(addressTextarea).clear();
		driver.findElement(addressTextarea).sendKeys(editAddress);
		driver.findElement(cityTextbox).clear();
		driver.findElement(cityTextbox).sendKeys(editCity);
		driver.findElement(stateTextbox).clear();;
		driver.findElement(stateTextbox).sendKeys(editState);
		driver.findElement(pinTextbox).clear();
		driver.findElement(pinTextbox).sendKeys(editPin);
		driver.findElement(phoneTextbox).clear();
		driver.findElement(phoneTextbox).sendKeys(editPhoneNo);
		driver.findElement(emailTextbox).clear();
		driver.findElement(emailTextbox).sendKeys(editEmail);
		driver.findElement(submitButton).click();

		// Verify expected data = actual data
		Assert.assertTrue(driver.findElement(By.xpath("//p[@class='heading3' and text() = 'Customer details updated Successfully!!!']")).isDisplayed());
		Assert.assertEquals(driver.findElement(addressValue).getText(), editAddress);
		Assert.assertEquals(driver.findElement(cityValue).getText(), editCity);
		Assert.assertEquals(driver.findElement(stateValue).getText(), editState);
		Assert.assertEquals(driver.findElement(pinValue).getText(), editPin);
		Assert.assertEquals(driver.findElement(phoneValue).getText(), editPhoneNo);
		Assert.assertEquals(driver.findElement(emailValue).getText(), editEmail);
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
