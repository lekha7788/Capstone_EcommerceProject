package com.DemoBlazeProjectPages;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.Generic.Utility;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

public class HomePage 
{
WebDriver driver;

JavascriptExecutor js=(JavascriptExecutor)driver;
public HomePage(WebDriver driver) {
	this.driver=driver;
}
//Locator for Home page button

By SignUp=By.id("signin2");
By LogIn=By.id("login2");
By Phones=By.xpath("//a[text()='Phones']");
By Laptops=By.xpath("//a[text()='Laptops']");
By Monitors=By.xpath("//a[text()='Monitors']");
By Cart =By.xpath("//a[text()='Cart']");

//Locators for SignUp button

By uName=By.id("sign-username");
By pswd=By.id("sign-password");
By signUpBtn=By.xpath("//button[text()='Sign up']");

//Locators for LogIn
By loginuserId=By.id("loginusername");
By loginPass=By.id("loginpassword");
By loginBtn1=By.xpath("//*[@id=\"logInModal\"]/div/div/div[3]/button[2]");

//Method to click SignUp
public void clickSignUp(String user,String pass, ExtentTest test) {
	driver.findElement(SignUp).click();
	driver.findElement(uName).sendKeys(user);
	driver.findElement(pswd).sendKeys(pass);
	driver.findElement(signUpBtn).click();
	WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(10));
	  wait.until(ExpectedConditions.alertIsPresent());
	  //Thread.sleep(10000);
	  Alert alert=driver.switchTo().alert();
	String alertmsg=alert.getText();
	alert.accept();
	if(alertmsg.equals("Sign up successful."))
	test.log(Status.PASS, "Sign up successful.");
	else
		test.log(Status.FAIL, "Sign up was not successful.");
	//Assert.assertEquals(alertmsg,"Sign up successful." );
	  System.out.println("SignUp Successful : "+alertmsg);
}

public void clickLogin(String user,String pass) {
	
	driver.findElement(LogIn).click();
	driver.findElement(loginuserId).sendKeys(user);
	driver.findElement(loginPass).sendKeys(pass);
	driver.findElement(loginBtn1).click();
}
public void clickPhones(ExtentTest test) {
	driver.findElement(Phones).click();
	//driver.findElement(Phones).click();
	JavascriptExecutor js=(JavascriptExecutor)driver;
	  js.executeScript("window.scrollBy(0,380)", "");
	  try {
		Thread.sleep(2000);
	} catch (InterruptedException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	  List<WebElement> phones=driver.findElements(By.xpath("//a[text()='Samsung galaxy s7']"));
	  //System.out.println(phone.getAttribute("innerHTML"));
	  if(phones.size()>0)
	   test.log(Status.PASS, "Phone Found");
	  else
		  test.log(Status.FAIL,"Phone not found" );
		  driver.findElement(By.xpath("//a[text()='Samsung galaxy s7']")).click();
	  driver.findElement(By.xpath("//a[text()='Add to cart']")).click();
		 String  Title=driver.getTitle();
		 System.out.println("Title of page is :"+Title);
		 
}
public void searchLaptops(ExtentTest test) throws InterruptedException {
	driver.findElement(Laptops).click();
	JavascriptExecutor js=(JavascriptExecutor)driver;
	js.executeScript("window.scrollBy(0,350)", "");
	  try {
		Thread.sleep(2000);
	} catch (InterruptedException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	  
	  List<WebElement> laptops=driver.findElements(By.xpath("//a[text()='Sony vaio i5']"));
	  if(laptops.size()>0)
		   test.log(Status.PASS, "Laptop Found");
		  else
			  test.log(Status.FAIL,"Laptop not found" );
	  //driver.findElement(By.xpath("//*[@id=\"tbodyid\"]/div[3]/div/div/h4/a")).click();
	  WebElement laptopName=driver.findElement(By.xpath("//a[text()='Sony vaio i5']"));
	  laptopName.click();
	// Assert.assertEquals(true, laptopName.getText().contains("Sony vaio i5") ,"Laptop searched is not matching");
	 // System.out.println(" Selected laptop is :"+laptopName.getText());
	  driver.findElement(By.xpath("//a[text()='Add to cart']")).click();
	  Thread.sleep(2000);
	  Alert alt=driver.switchTo().alert();
	  System.out.println("Alert Mesage after adding laptop to cart is "+ alt.getText());
	  alt.accept();
	  driver.switchTo().defaultContent();
	  
	
}
public void clickMonitors(ExtentTest test) throws InterruptedException {
	driver.findElement(Monitors).click();
	JavascriptExecutor js=(JavascriptExecutor)driver;
	js.executeScript("window.scrollBy(0,200)", "");
	List<WebElement> monitors=driver.findElements(By.id("tbodyid"));
	if(monitors.size()>0)
		test.log(Status.PASS, "Monitors Found");
	else
		test.log(Status.FAIL, "Monitors not found");
	driver.findElement(By.xpath("//a[text()='Apple monitor 24']")).click();
	
	driver.findElement(By.xpath("//a[text()='Add to cart']")).click();
	Thread.sleep(2000);
	  Alert alt=driver.switchTo().alert();
	  System.out.println("Alert Mesage after adding laptop to cart is "+ alt.getText());
	  alt.accept();
	  driver.switchTo().defaultContent();
	
}

public void clickHome() {
	driver.findElement(By.xpath("//a[text()='Home ']")).click();
}
public void clickCart(ExtentTest test) throws InterruptedException {
	driver.findElement(Cart).click();
	List<WebElement> cartItems=driver.findElements(By.id("tbodyid"));
	if(cartItems.size()>0)
		test.log(Status.PASS,"Items present in cart");
	else
		test.log(Status.FAIL,"Items not present in cart" );
	driver.findElement(By.xpath("//button[text()='Place Order']")).click();
	Thread.sleep(2000);
	PlaceOrderPage pop=new PlaceOrderPage(driver);
	pop.placeOrder("Lekha", "India", "Bangalore", "79642456", "July","2027");
	
	//cart.clickOnPlaceOrder();
	Utility.getScreenShot(driver,"Laptop Order");
	System.out.println("Laptop Order Placed");
	driver.findElement(By.xpath("//button[text()='OK']")).click();
	driver.findElements(By.xpath("//button[text()='Close']")).get(2).click();
}

}
