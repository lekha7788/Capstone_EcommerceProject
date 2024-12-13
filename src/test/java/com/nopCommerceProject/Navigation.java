package com.nopCommerceProject;

import static org.testng.Assert.assertListContainsObject;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class Navigation {
	WebDriver driver;
  @Test
  public void Computers() {
	  WebElement comp= driver.findElement(By.partialLinkText("Computer"));
	  comp.click();
	 WebElement cObj1= driver.findElement(By.partialLinkText("Desktop"));
	 List<WebElement> list=comp.findElements(By.tagName("a"));
	 for(WebElement elem  :list)
	 {
		 System.out.println(elem.getText());
	 }
	
	 System.out.println(cObj1);
	  //((Assert) compLinks).assertListContainsObject("Desktop", "", "")
	
	
	
	  
  }
  @BeforeMethod 
  public void LaunchBrowser() {
	  driver=new ChromeDriver();
	  driver.manage().window().maximize();
	  driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
	  driver.get("https://demo.nopcommerce.com/login?returnUrl=%2Fwishlis");
	  System.out.println(driver.getCurrentUrl());
  }
}
