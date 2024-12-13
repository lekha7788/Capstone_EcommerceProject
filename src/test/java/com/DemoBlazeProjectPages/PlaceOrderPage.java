package com.DemoBlazeProjectPages;

import java.time.Duration;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class PlaceOrderPage {
	
		WebDriver driver;
		public PlaceOrderPage(WebDriver driver) {
			this.driver=driver;
		}
		By name=By.id("name");
		By Country=By.id("country");
		By City=By.id("city");
		By CreditCard=By.id("card");
		By Month=By.id("month");
		By Year =By.id("year");
		By Purchase=By.xpath("//button[text()='Purchase']");
		
		public void placeOrder(String n,String country,String city,String card,String month,String year1) {
			driver.findElement(name).sendKeys(n);
			driver.findElement(Country).sendKeys(country);
			driver.findElement(City).sendKeys(city);
			driver.findElement(CreditCard).sendKeys(card);
			driver.findElement(Month).sendKeys(month);
			driver.findElement(Year).sendKeys(year1);
			
			driver.findElement(Purchase).click();
			//WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(10));
			 // wait.until(ExpectedConditions.alertIsPresent());
			
			
			  
		}

}
