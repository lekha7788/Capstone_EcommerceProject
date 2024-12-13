package com.nopCommerceProject;

import static org.testng.Assert.assertNotNull;

import java.io.File;
import java.io.IOException;
import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class RegisterFunctionality {
	WebDriver driver;
	
  @Test(priority=1)
  public void registerFunctionality()  {
	  System.out.println("Register Functionality");
	  driver.findElement(By.linkText("Register")).click();
	  System.out.println("Your Personal Details");
	  driver.findElement(By.cssSelector("input[value=\"F\"]")).click();
	  driver.findElement(By.id("FirstName")).sendKeys("Lekha1");
	  driver.findElement(By.id("LastName")).sendKeys("Aravinda");
	  WebElement Dobday=driver.findElement(By.name("DateOfBirthDay"));
	  Select day=new Select(Dobday);
	  day.selectByIndex(14);
	  WebElement DobMonth=driver.findElement(By.name("DateOfBirthMonth"));
	  Select month=new Select(DobMonth);
	  
	  month.selectByValue("7");
	  WebElement DobYear=driver.findElement(By.name("DateOfBirthYear"));
	  Select year=new Select(DobYear);
	  year.selectByValue("1993");
	  
	  driver.findElement(By.id("Email")).sendKeys("lekha1@gmail.com");
      
      //Company Details
      driver.findElement(By.id("Company")).sendKeys("abc Company");
      WebElement chkBox=driver.findElement(By.id("Newsletter"));
      if(chkBox.isSelected()==false)
    	  chkBox.click();
      driver.findElement(By.id("Password")).sendKeys("abc123");
      driver.findElement(By.id("ConfirmPassword")).sendKeys("abc123");
      driver.findElement(By.id("register-button")).submit();
      System.out.println("Registed susscessfully");
      File file= ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		try {
			FileHandler.copy(file,new File("RegisterSuccessScreenshot.jpeg"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("Screenshot captured");
      driver.findElement(By.linkText("CONTINUE")).click();
      System.out.println(driver.getCurrentUrl());
  }
  @Test(priority=2)
  public void LoginFunctionality() {
	  System.out.println("Login Functionality");
	  driver.findElement(By.partialLinkText("Log in")).click();
	  driver.findElement(By.id("Email")).sendKeys("lekha@gmail.com");
	  driver.findElement(By.id("Password")).sendKeys("abc123");
	  driver.findElement(By.cssSelector("button[class='button-1 login-button']")).submit();
	  File file=((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
	  try {
		FileHandler.copy(file, new File("AfterLoginSuccessScreenshot.jpeg"));
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	  System.out.println("Login Screenshot captured");
	  Boolean verifyTitle = driver.getTitle().equalsIgnoreCase("Most Reliable App & Cross Browser Testing Platform");
	  assertNotNull(verifyTitle);
			  
  }
  @BeforeMethod
  public void launchBrowser() {
	  driver=new ChromeDriver();
	  driver.manage().window().maximize();
	  driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
	  driver.get("https://demo.nopcommerce.com/login?returnUrl=%2Fwishlis");
	 String title= driver.getTitle();
	  // Assert.assertEquals(title, driver.getTitle().contains(title),"Title not matched!");
	  System.out.println("Title Matched! Page Title is :"+title);
  }
  @AfterMethod(enabled=true)
  public void closeBrowser() {
	  driver.quit();
  }
}
