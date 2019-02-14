package stepdefs;

import java.util.List;

import cucumber.api.DataTable;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class Stepdefinition {
	
	@Before
	public void startBrowser()
	{
		System.out.println("Start the Browser");
	}
	
	@After
	public void closeBrowser()
	{
		System.out.println("Close the Browser");
	}
	
	@Before("@Regression")
	public void RegressionBefore()
	{
		System.out.println("Start the Browser @Regression");
	}
	
	@After("@Regression")
	public void RegressionAfter()
	{
		System.out.println("Close the Browser @Regression");
	}
	
	
	@Given("^Launch Orange HRM Application$")
	public void launch_Orange_HRM_Application() throws Throwable {
	    System.out.println("Launch Orange HRM Application");
	}

	@When("^User enter credentials$")
	public void user_enter_credentials() throws Throwable {
		System.out.println("User enter credentials");
	}

	@Then("^User logged in successfully$")
	public void user_logged_in_successfully() throws Throwable {
		System.out.println("User logged in successfully");
	}
	
	@When("^User enter \"([^\"]*)\" and \"([^\"]*)\"$")
	public void user_enter_and(String arg1, String arg2) throws Throwable {
	    System.out.println("Username is : "+arg1);
	    System.out.println("Password is : "+arg2);
	}
	
	@When("^User enter credentials \"([^\"]*)\" and \"([^\"]*)\"$")
	public void user_enter_credentials_and(String arg1, String arg2) throws Throwable {
		 System.out.println("Username is : "+arg1);
		 System.out.println("Password is : "+arg2);
	}
	
	@When("^User enter userID and password$")
	public void user_enter_userID_and_password(DataTable arg1) throws Throwable {
		
		List<List<String>> testData = arg1.raw();
	    System.out.println(testData.get(0).get(0));
	    System.out.println(testData.get(0).get(1));
	}

}
