package com.DemoBlazeProject;

import java.time.Duration;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.DemoBlazeProjectPages.CartPage;
import com.DemoBlazeProjectPages.HomePage;
import com.DemoBlazeProjectPages.PlaceOrderPage;

import com.Generic.Utility;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class DemoBlazeTestCase {
	WebDriver driver;
	HomePage home;
	JavascriptExecutor js = (JavascriptExecutor) driver;
	CartPage cart = new CartPage(driver);
	ExtentSparkReporter extentReport;//= new ExtentSparkReporter("DemoBlazeReport.html");	//Report File
	ExtentReports report;// = new ExtentReports();		//Report
	ExtentTest test;
	
	@BeforeTest
	public void LaunchBrowser() {
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		driver.get("https://www.demoblaze.com/");
		home = new HomePage(driver);
		extentReport= new ExtentSparkReporter("DemoBlazeReport.html");
		report = new ExtentReports();
		report.attachReporter(extentReport);
		test=report.createTest("DemoBlazeTest");
	}
	@Test(priority = 1, enabled = true)
	public void validateSignUp() throws InterruptedException {
		home.clickSignUp("lekha" + System.currentTimeMillis() + "@gmail.com", "lekha123", test);
		
		System.out.println("SignUp page");
		Utility.getScreenShot(driver, "SignUp");
		
	}

	@Test(priority = 2)
	public void LogIn() throws InterruptedException {
		home.clickLogin("lekha8@gmail.com", "lekha123");
		Thread.sleep(3000);
		System.out.println("Login successfull");
		WebElement user = driver.findElement(By.id("nameofuser"));
		System.out.println(user.getText());
		if(user.getText().contains("Welcome")==true)
			test.log(Status.PASS, "Login Successfull");
		else
			test.log(Status.FAIL,"Login Failed");
		//Assert.assertEquals(true, user.getText().contains("Welcome"));
		Utility.getScreenShot(driver, "LoginUser");
	}

	@Test(priority = 3)
	public void searchPhones() throws InterruptedException {
		home.clickPhones(test);
		Utility.getScreenShot(driver, "Phones");
		Thread.sleep(2000);
		Alert alt = driver.switchTo().alert();
		String alertMsg = alt.getText();
		System.out.println("Alert Message is :" + alertMsg);
		alt.accept();
		driver.switchTo().defaultContent();
		home.clickHome();
	}

	@Test(priority = 4)
	public void searchLaptops() throws InterruptedException {
		home.searchLaptops(test);
		Utility.getScreenShot(driver, "Laptops");
		home.clickHome();
	}

	@Test(priority = 5)
	public void searchMonitors() {
		try {
			home.clickMonitors(test);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Utility.getScreenShot(driver,"Monitors");
		
	}

	@Test(priority = 6)
	public void doCheckout() throws InterruptedException {
		home.clickCart(test);
		driver.findElement(By.partialLinkText("Log out")).click();
	}



	@AfterTest(enabled = true)
	public void tearDown() {
		report.flush();
		driver.quit();
	}

}
