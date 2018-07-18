package tests;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.apache.log4j.xml.DOMConfigurator;

import io.github.bonigarcia.wdm.WebDriverManager;
import utils.TestUtils;

import org.openqa.selenium.JavascriptExecutor;

public class LoginModule {
	
	

	public WebDriver driver;
	public WebDriverWait wait;
	private static Logger Log = Logger.getLogger(LoginModule.class.getName());

	@Test
	public void signUp() throws InterruptedException {

		String newEmail = TestUtils.generateRandomEmail();
		driver.get("https://apptimize.com/");
		driver.manage().window().maximize();
		
		Log.info("Loaded Apptimize HomePage");
		
		//Go to SignUp Page
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/header/div/nav/ul[2]/li[2]/a")));
		WebElement signUp_Button = driver.findElement(By.xpath("/html/body/header/div/nav/ul[2]/li[2]/a"));
		signUp_Button.click();
		
		//Fill in SignUp form
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='fname']")));
		Log.info("Loaded Signup Page sucessfully");
		WebElement fName_Field = driver.findElement(By.xpath("//*[@id='fname']"));
		WebElement lName_Field = driver.findElement(By.xpath("//*[@id='lname']"));
		WebElement email_Field = driver.findElement(By.xpath("//*[@id='email']"));
		WebElement company_Field = driver.findElement(By.xpath("//*[@id='company']"));
		WebElement password_Field = driver.findElement(By.xpath("//*[@id='password']"));
		WebElement agree_CheckBox = driver.findElement(By.xpath("//*[@id='eula']"));
		WebElement submit_Button = driver.findElement(By.xpath("//*[@id='submit']"));
		
		
		fName_Field.sendKeys("Jeran");
		lName_Field.sendKeys("Jeyachandran");
		email_Field.sendKeys(newEmail+"@gmail.com");
		Log.info( "Generated a random email ID : "+newEmail+"@gmail.com");
		company_Field.sendKeys("Apptimize Candidate");
		password_Field.sendKeys("passpass123");
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].scrollIntoViewIfNeeded(true);", agree_CheckBox);
		agree_CheckBox.click();
		submit_Button.click();
		
		// Create a new App	
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\'zet-app-name\']")));
		Log.info("Loaded the create app page sucessfully.");
		WebElement appName_Field = driver.findElement(By.xpath("//*[@id='zet-app-name']"));
		WebElement android_Button = driver.findElement(By.xpath("//*[@id='zet-icon-android']"));
		WebElement addApp_Button = driver.findElement(By.xpath("//*[@id='zet-create-app']"));
		appName_Field.sendKeys("Trial_App");
		android_Button.click();
		addApp_Button.click();
		Log.info("App created sucessfully.");
		
		//Logout after creation
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\'zet-dashboard-create\']")));
        driver.navigate().to("https://apptimize.com/admin/logout");
        Log.info("Signed out sucessfully");
	}
	@BeforeClass
	public void beforeClass() {
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		wait = new WebDriverWait(driver,10);
		DOMConfigurator.configure("log4j.xml");	
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}
}
