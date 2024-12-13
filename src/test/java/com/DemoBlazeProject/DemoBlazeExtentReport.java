package com.DemoBlazeProject;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class DemoBlazeExtentReport {

			public static void main(String[] args) {
			ExtentSparkReporter htmlReport = new ExtentSparkReporter("DemoBlazeReport.html");	//Report File
			ExtentReports report = new ExtentReports();		//Report
			report.attachReporter(htmlReport);				//Attaching the file with report
			
			ExtentTest test;								//Represents test cases
			
			//Add environment details
			report.setSystemInfo("User", "Lekha");
			report.setSystemInfo("Machine", "Dell");
			report.setSystemInfo("Operating System", "Windows 11");
			report.setSystemInfo("Browser", "Google Chrome");
			report.setSystemInfo("EMail", "lekha.thunugunta@gmail.com");
			
			//Configure Report
			htmlReport.config().setDocumentTitle("DemoBlaze Test");
			htmlReport.config().setReportName("Report For DemoBlaze page");
			htmlReport.config().setTheme(Theme.STANDARD);
			htmlReport.config().setTimeStampFormat("dd-MMM-yyy");
			
			//Test
			WebDriver driver = new ChromeDriver();
			driver.manage().window().maximize();
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
			
			driver.get("https://www.demoblaze.com/");
			
			System.out.println("Title: " + driver.getTitle());
			
			driver.close();
			
			test = report.createTest("DemoBlaze Title");
			test.log(Status.PASS, MarkupHelper.createLabel("DemoBlaze Title Test:Pass", ExtentColor.GREEN));
			
			report.flush();			//Will generate the report
		}

	
	}


