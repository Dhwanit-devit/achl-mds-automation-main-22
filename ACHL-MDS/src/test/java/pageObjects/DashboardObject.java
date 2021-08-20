package pageObjects;

import org.openqa.selenium.By;

import selenium.Element;


public class DashboardObject extends Element {

	// Login Element
	public By header = By.xpath("//section[@class='content-header']//h1");
	public By appdropdown = By.xpath("//a[@data-hover='dropdown']");
	public By language = By.xpath("//a[normalize-space()='English']");
	
	

	public void navigate(String url) {
		driver.get(url);
	}
	
}
	