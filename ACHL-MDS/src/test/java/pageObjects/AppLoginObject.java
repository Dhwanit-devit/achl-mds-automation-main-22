package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;

import managers.UserManager;
import selenium.Element;
import selenium.Wait;


public class AppLoginObject extends Element {

	// Login Element
	public By email = By.xpath("//input[@name='username']");
	public By password = By.xpath("//input[@name='password']");
	public By lgnbutton = By.xpath("//*[@id=\"signin\"]");
	public By userdrop = By.xpath("//li[@class='dropdown user user-menu']//span[@class='hidden-xs']");
	public By Log_out = By.xpath("//i[@class='fa fa-fw fa-power-off']");
	
	public void navigate(String url) {
		driver.get(url);
	}

	public void googleLogin(String user) {
		navigate(UserManager.getProperty("base.url"));
		sleep(3);
		if ((user.equalsIgnoreCase("ClientUser"))) {
			JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeScript("localStorage.setItem(arguments[0],arguments[1])", "token", UserManager.getToken(user));
			js.executeScript("localStorage.setItem(arguments[0],arguments[1])", "userId", UserManager.getkey(user));
			driver.navigate().refresh();
		} else {
			sendKeys(email, UserManager.getProperty("client.login.username"));
			sendKeys(password, UserManager.getProperty("client.login.password"));
			click(lgnbutton);
			sleep(5);
		}
		Wait.untilPageLoadComplete(driver);
		sleep(3);
	}

	
	public void doLogout() {
		Element.driver.manage().deleteAllCookies();
		click(userdrop);
		click(Log_out);
		sleep(2);
		Element.driver.manage().deleteAllCookies();

	}

}