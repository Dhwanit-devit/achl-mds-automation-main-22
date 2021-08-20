package managers;

import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.Collections;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Capabilities;
import org.openqa.selenium.UnexpectedAlertBehaviour;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.RemoteWebDriver;

public class WebDriverManager {

	private WebDriverManager() {
		throw new IllegalStateException("WebDriverManager Class");
	}

	static WebDriver driver = getDriver();
	private static final String CHROME_LOG = "webdriver.chrome.logfile";

	public static WebDriver getDriver() {
		if (driver == null)
			driver = createLocalDriver();
		return driver;
	}

	private static ChromeOptions chromeOption() {
		ChromeOptions options = new ChromeOptions();
		options.addArguments("disable-infobars");
		options.addArguments("start-maximized");
		options.addArguments("incognito");
		options.addArguments("--ignore-certificate-errors");
		options.addArguments("--disable-popup-blocking");
		options.addArguments("--disable-extensions");
		options.addArguments("–disable-notifications");
		
		options.setExperimentalOption("excludeSwitches", Collections.singletonList("enable-automation"));

		Boolean headless = Boolean.valueOf(ApplicationProperties.getInstance().getProperty("headless"));
		if (Boolean.TRUE.equals(headless)) {
			options.addArguments("headless");
			options.addArguments("window-size=1600x1200");
		}
		options.setCapability(CapabilityType.UNEXPECTED_ALERT_BEHAVIOUR, UnexpectedAlertBehaviour.ACCEPT);
		options.setCapability(ChromeOptions.CAPABILITY, options);
		return options;
	}

	private static WebDriver createLocalDriver() {
		io.github.bonigarcia.wdm.WebDriverManager.chromedriver().setup();
		System.setProperty(CHROME_LOG, ApplicationProperties.getInstance().getProperty("chromeLog"));
		driver = new ChromeDriver(chromeOption());

		Boolean windowMaximize = Boolean.valueOf(ApplicationProperties.getInstance().getProperty("windowMaximize"));

		if (Boolean.TRUE.equals(windowMaximize))
			driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(
				Long.parseLong(ApplicationProperties.getInstance().getProperty("implicitWait")), TimeUnit.SECONDS);
		Capabilities cap = ((RemoteWebDriver) driver).getCapabilities();
		setPorperties(cap.getVersion());
		return driver;
	}

	public static void closeDriver() throws IOException {
		driver.close();
		driver.quit();
	}

	public static void setPorperties(String version) {
		String profile = "report_generation/report.properties";
		Properties pro = new Properties();
		try {
			pro.load(new FileReader(profile));
			if (!pro.containsKey("browser.version")) {
				pro.setProperty("browser.version", version);
				pro.store(new FileOutputStream(profile, true), "Chrome Version");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}