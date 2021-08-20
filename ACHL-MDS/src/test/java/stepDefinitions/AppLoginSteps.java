package stepDefinitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import pageObjects.AppLoginObject;

public class AppLoginSteps {

	public AppLoginObject appLoginObject;

	public AppLoginSteps(AppLoginObject appLoginObject) {
		this.appLoginObject = appLoginObject;
	}

	@Given("Login to webapp as a {string}")
	public void login(String arg1) {
		appLoginObject.googleLogin(arg1);
	}

	@And("Logout from the application")
	public void logout() {
		appLoginObject.doLogout();

	}

}