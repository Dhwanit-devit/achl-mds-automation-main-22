package stepDefinitions;

import org.testng.Assert;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import pageObjects.DashboardObject;
import selenium.Element;
import selenium.Wait;

public class DashboardSteps {

	public DashboardObject dashboardObject;

	public DashboardSteps(DashboardObject dashboardObject) {
		this.dashboardObject = dashboardObject;
	}

	@Then("^Verify the dashbord header$")
	public void verify_the_dashbord_header(){
		Wait.untilPageLoadComplete(Element.driver);
		//appLoginObject.sendKeys(appLoginObject.email, "hannes.loesch@gmail.com");
		//dashboardObject.sendKeys(dashboardObject.password, "Meinbeck@#123");
		String currentpageurl = Element.driver.getCurrentUrl();
		Assert.assertTrue(currentpageurl.contains("dashboard"));
		Assert.assertTrue(dashboardObject.getText(dashboardObject.header).contains("Meinbeck Dashboard"));
	}

	@And("^They need to click on a Select dropdown menu icon$")
	public void they_need_to_click_on_a_select_dropdown_menu_icon() {
		dashboardObject.click(dashboardObject.appdropdown);
		dashboardObject.click(dashboardObject.language);
	}
	
	

}