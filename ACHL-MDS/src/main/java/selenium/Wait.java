package selenium;

import java.time.Duration;
import java.util.function.Function;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.FluentWait;

import managers.ApplicationProperties;
import managers.WebDriverManager;

public class Wait {

	private static final Logger logger = Logger.getLogger(Wait.class.getName());

	private Wait() {
		PropertyConfigurator.configure("config/log4j.properties");
	}

	public static void untilJqueryIsDone(WebDriver driver) {
		untilJqueryIsDone(driver, Long.parseLong(ApplicationProperties.getInstance().getProperty("implicitWait")));
	}

	public static void untilJqueryIsDone(WebDriver driver, Long timeoutInSeconds) {
		until(driver, d -> {
			final Boolean isJqueryCallDone = (Boolean) ((JavascriptExecutor) driver)
					.executeScript("return jQuery.active==0");
			if (Boolean.TRUE.equals(!isJqueryCallDone))
				logger.info("JQuery call is in Progress");
			return isJqueryCallDone;
		}, timeoutInSeconds);
	}

	public static void untilPageLoadComplete(WebDriver driver) {
		untilPageLoadComplete(driver, Long.parseLong(ApplicationProperties.getInstance().getProperty("implicitWait")));
	}

	public static void untilPageLoadComplete(WebDriver driver, Long timeoutInSeconds) {
		until(driver, d -> {
			final Boolean isPageLoaded = (Boolean) ((JavascriptExecutor) driver)
					.executeScript("return document.readyState").equals("complete");
			if (Boolean.TRUE.equals(!isPageLoaded))
				logger.info("Document is loading");
			return isPageLoaded;
		}, timeoutInSeconds);
	}

	public static void until(Function<WebDriver, Boolean> waitCondition) {
		until(WebDriverManager.getDriver(), waitCondition,
				Long.parseLong(ApplicationProperties.getInstance().getProperty("implicitWait")));
	}

	private static void until(WebDriver driver, Function<WebDriver, Boolean> waitCondition, Long timeoutInSeconds) {
		FluentWait<WebDriver> webDriverWait = new FluentWait<>(driver);
		webDriverWait.withTimeout(Duration.ofSeconds(timeoutInSeconds));
		try {
			webDriverWait.until(waitCondition);
		} catch (Exception e) {
			logger.info("Failed to Load :=> " + e.getMessage());
		}
	}

	public static void until(ExpectedCondition<WebElement> conditions) {
		FluentWait<WebDriver> wait = new FluentWait<WebDriver>(WebDriverManager.getDriver())
				.withTimeout(Duration.ofSeconds(30)).pollingEvery(Duration.ofSeconds(3));
		try {
			wait.until(conditions);
		} catch (Exception e) {
			logger.info("Failed to Load :=> " + e.getMessage());
		}
	}
}