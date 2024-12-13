package com.DemoBlazeProjectPages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CartPage {
	WebDriver driver;
	public  CartPage(WebDriver driver) {
		this.driver=driver;
	}
	By Delete=By.xpath("//a[text()='Delete']");
	By PlaceOrder=By.xpath("//button[text()='Place Order']");
	public void clickOnDelete(String obj) {
		driver.findElement(Delete).click();
	}
	public void clickOnPlaceOrder() {
		driver.findElement(PlaceOrder).click();
	}

}
