package com.test;

import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

public class SampleTest 
{
	WebDriver driver;
	
	@BeforeTest
	public void startReport()
	{
		System.setProperty("webdriver.chrome.driver", "src\\test\\resources\\Driver\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}
	@Test(priority=1)
	public void Test1() throws Exception
	{
		driver.get("https://www.google.co.in");
		System.out.println("################Verify The Title of Google####################");
		Assert.assertTrue(driver.getTitle().contains("Google"));
	}
	@AfterTest
	public void endReport()
	{
		driver.close();
    }
}