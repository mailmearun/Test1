package com.test;

import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.SkipException;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.Markup;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.ChartLocation;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class SampleExtentTest 
{
	ExtentHtmlReporter htmlReporter;
	ExtentReports extent;
	ExtentTest logger;
	WebDriver driver;
	WebElement Element=null;
	
	@BeforeTest
	public void startReport()
	{
		htmlReporter = new ExtentHtmlReporter(System.getProperty("user.dir") +"/test-output/ExtReport.html");
		extent = new ExtentReports ();
		extent.attachReporter(htmlReporter);
		extent.setSystemInfo("Host Name", "SoftwareTestingMaterial");
		extent.setSystemInfo("Environment", "Automation Testing");
		extent.setSystemInfo("User Name", "Arun Kumar");
		
		htmlReporter.config().setDocumentTitle("Title of the Report (BrowserTitle)");
		htmlReporter.config().setReportName("Name of the Report (In HTML)");
		htmlReporter.config().setTestViewChartLocation(ChartLocation.TOP);
		htmlReporter.config().setTheme(Theme.STANDARD);
		System.setProperty("webdriver.chrome.driver", "E:\\Test\\Driver\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}
		
	@Test(priority=1)
	public void Test1() throws Exception
	{
		logger = extent.createTest("passTest");
		driver.get("https://www.google.co.in");
		WebDriverWait wait = new WebDriverWait(driver, 40);
		WebElement element = driver.findElement(By.xpath("//*[@name='btnI']"));
		logger.log(Status.FAIL, MarkupHelper.createLabel("Test Case Pass", ExtentColor.GREEN));
		//Adding Custom step to Extent Report
		//WebElement Google = driver.findElement(By.xpath("//*[@name='btnI']"));
		extentReportlogSteps(com.aventstack.extentreports.Status.PASS,"Feeling Lucky",element);
		
		Assert.assertTrue(driver.getTitle().contains("Google"));
	}
	
	@Test(priority=2)
	public void Test2()
	{
		System.out.println("Test Maven");
		logger = extent.createTest("failTest");
		driver.get("https://www.msn.com");
		logger.log(Status.FAIL, MarkupHelper.createLabel("Test Case Failed", ExtentColor.GREY));
		Assert.assertTrue(driver.getTitle().contains("Google"));
	}
	
	@Test(priority=3)
	public void Test3()
	{
		logger = extent.createTest("skipTest");
		logger.log(Status.SKIP, "Test Case skip");
		logger.log(Status.SKIP, MarkupHelper.createLabel("Test Case skipped1", ExtentColor.ORANGE));
		throw new SkipException("Skipping - This is not ready for testing ");
	}
	
	@AfterMethod
	public void getResult(ITestResult result) throws Exception
	{
		if(result.getStatus() == ITestResult.FAILURE)
		{
			//Add Screenshot to Step
			String screenshotPath = SampleExtentTest.getScreenhot(driver, result.getName(),Element);
			logger.log(Status.FAIL, result.getName() + " - Test Case Failed", MediaEntityBuilder.createScreenCaptureFromPath(screenshotPath).build());
			logger.log(Status.FAIL, MarkupHelper.createLabel(result.getName() + " - Test Case Failed", ExtentColor.RED));
			//extent.removeTest(logger);
		}
		else if(result.getStatus() == ITestResult.SKIP)
		{
			logger.log(Status.SKIP, MarkupHelper.createLabel(result.getName() + " - Test Case Skipped2", ExtentColor.ORANGE));	
		}
		else if(result.getStatus() == ITestResult.SUCCESS)
		{
			logger.log(Status.PASS, MarkupHelper.createLabel(result.getName() + " - Test Case Passed2", ExtentColor.GREEN));	
		}
	}
	
	@AfterTest
	public void endReport(){
		extent.flush();
    }
	//Method to take screenshot
	public static String getScreenhot(WebDriver driver, String screenshotName, WebElement Ele) throws Exception 
	{
		String dateName = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
		String destination = System.getProperty("user.dir")+"/test-output/Extent/" + screenshotName + dateName+".png";
		JavascriptExecutor javascript = (JavascriptExecutor) driver;
		if(Ele!=null)
		{
			javascript.executeScript("arguments[0].style.border='3px solid red'", Ele);
			FileUtils.copyFile(((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE),new File(destination));
		}
		else
		{
			FileUtils.copyFile(((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE),new File(destination));
		}
		return destination;
	}
	//Method to add screenshot to Step
	public void extentReportlogSteps(com.aventstack.extentreports.Status status, String Details, WebElement element) throws Exception 
	{
		String screenshotPath = SampleExtentTest.getScreenhot(driver, Details,element);
		logger.log(status,Details, MediaEntityBuilder.createScreenCaptureFromPath(screenshotPath).build());
		
	}
	

}