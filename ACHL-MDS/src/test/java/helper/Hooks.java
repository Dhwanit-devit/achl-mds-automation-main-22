package helper;

import java.io.IOException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.html5.WebStorage;
import org.openqa.selenium.logging.LogEntries;
import io.cucumber.java.After;
import io.cucumber.java.Scenario;
import managers.WebDriverManager;
import pageObjects.AppLoginObject;
import selenium.Element;

public class Hooks {

	AppLoginObject loginLogout;

	public Hooks(AppLoginObject loginLogout) {
		this.loginLogout = loginLogout;
	}

	@After(order = 0)
	public void attachScreenShot(Scenario scenario) throws IOException {
		WebStorage webStorage = (WebStorage)Element.driver;
		webStorage.getSessionStorage().clear();
		webStorage.getLocalStorage().clear();
		Element.driver.manage().deleteAllCookies();
		System.out.println("======================= attachScreenShot : @After =============================");
		byte[] screenshot = ((TakesScreenshot) WebDriverManager.getDriver()).getScreenshotAs(OutputType.BYTES);
		scenario.attach(screenshot, "image/png", "Snapshot is");
		WebDriverManager.getDriver().close();
	}

	@After(order = 1)
	public void addLog(Scenario scenario) throws IOException {
		LogEntries entry = WebDriverManager.getDriver().manage().logs().get("browser");
		scenario.attach(entry.toJson().toString(), "text/plain", "Log Entry");
		//WebDriverManager.getDriver().close();
	}
	
	
	
}