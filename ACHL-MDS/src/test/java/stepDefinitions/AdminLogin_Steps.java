package stepDefinitions;

import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class AdminLogin_Steps {

	WebDriver driver;
	
	//This is @F1.1 scenario
	@Given("I am on MDS admin login page")
	public void i_am_on_mds_admin_login_page() {
		System.setProperty("webdriver.chrome.driver", "webdriver/chromedriver.exe");           
		  
		driver = new ChromeDriver(); 
		driver.manage().window().maximize();
		driver.get("http://mdsqa.achlcme.org/Administrator");
	}

	@When("I enter email and password")
	public void i_enter_email_and_password() {
		System.out.println("2");
		WebElement UN = driver.findElement(By.xpath("//input[@id='txtEmail']"));
		UN.sendKeys("admin@mds.com");
		WebElement PWD = driver.findElement(By.xpath("//input[@id='Password']"));
		PWD.sendKeys("MDSAdmin!");
	}

	@When("I clicked on login button")
	public void i_clicked_on_login_button() throws InterruptedException {
		System.out.println("3");
		WebElement Submit = driver.findElement(By.xpath("//input[@value='Login']"));
		Submit.submit();
		Thread.sleep(2000);
	}

	@Then("User should be logged in to admin")
	public void user_should_be_logged_in_to_admin() {
		String URL = driver.getCurrentUrl();
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		System.out.println(URL);
	}
	
	//This is @F1S1 scenario
	@Given("I am on MDS admin dashboard page")
	public void i_am_on_mds_admin_dashboard_page() throws InterruptedException {
		try {
			String CourseURL = driver.getCurrentUrl();
			System.out.println("URL");
		}
		catch(NullPointerException e) {
			System.out.println("NullPointerException thrown!");
		}
		Thread.sleep(2000);
	}

	@When("I click on Course Management button")
	public void i_click_on_course_management_button() throws InterruptedException {
		try {
			WebElement CourseMgt = driver.findElement(By.xpath("//fieldset[3]//div[2]//div[2]//a[1]"));
			CourseMgt.click();
		}
		catch(NullPointerException e) {
			System.out.println("NullPointerException thrown!");
		}
		Thread.sleep(2000);
	}

	@Then("User is redirected to Course Management page")
	public void user_is_redirected_to_course_management_page() {
		try {
			String CourseURL = driver.getCurrentUrl();
			CourseURL.compareTo("https://mdsqa.achlcme.org/Administrator/Meeting");
		}
		catch(NullPointerException e) {
			System.out.println("NullPointerException thrown!");
		}
	}
	
	//This is @F1S2 scenario
	@Given("I am on the Course Management page")
	public void i_am_on_the_course_management_page() {
	    // Write code here that turns the phrase above into concrete actions
	    throw new io.cucumber.java.PendingException();
	}

	@When("I click on the Add course button")
	public void i_click_on_the_add_course_button() {
	    // Write code here that turns the phrase above into concrete actions
	    throw new io.cucumber.java.PendingException();
	}

	@Then("I should be redirected to the Add Course page")
	public void i_should_be_redirected_to_the_add_course_page() {
	    // Write code here that turns the phrase above into concrete actions
	    throw new io.cucumber.java.PendingException();
	}

}
