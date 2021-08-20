package selenium;

import java.util.List;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import managers.WebDriverManager;

public class Element {

	public static final WebDriver driver = WebDriverManager.getDriver();
	public static final int SECONDS = 60;
	Actions build;
	public static final JavascriptExecutor jse = (JavascriptExecutor) driver;
	WebDriverWait wait;

	public static final Logger logger = Logger.getLogger(Element.class.getName());

	public Element() {
		PropertyConfigurator.configure("config/log4j.properties");
		PageFactory.initElements(new AjaxElementLocatorFactory(driver, SECONDS), this);
	}

	public void sleep(int timeInSeconds) {
		try {
			Thread.sleep(timeInSeconds * 1000L);
		} catch (Exception e) {
			logger.error("Fail to sleep : " + timeInSeconds);
		}
	}

	public void getHighlightElement(By by) {
		Wait.untilPageLoadComplete(driver);
		try {
			((JavascriptExecutor) driver).executeScript("arguments[0].setAttribute('style',arguments[1]);",
					driver.findElement(by), "border: 2px solid yellow;");
		} catch (Exception e) {
			logger.info("Fail to highlight the Element " + by);
		}
	}

	public boolean isSelected(By by) {
		try {
			moveToElement(by);
			return driver.findElement(by).isSelected();
		} catch (NoSuchElementException e) {
			logger.info(e.getMessage());
			return false;
		} catch (Exception e) {
			logger.info("Fail to check isSelected : " + by + " : " + e.getMessage());
			return false;
		}
	}

	public boolean isEnabled(By by) {
		try {
			moveToElement(by);
			return driver.findElement(by).isEnabled();
		} catch (NoSuchElementException e) {
			Assert.assertTrue(false,
					"Fail to find element to check isEnabled : " + by + " on page : " + driver.getCurrentUrl());
			return false;
		} catch (Exception e) {
			logger.info(e.getMessage());
			return false;
		}
	}

	public boolean waitFordetectPage(By by) {
		moveToElement(by);
		try {
			return driver.findElement(by).isDisplayed();
		} catch (NoSuchElementException e) {
			return false;
		} catch (Exception e) {
			logger.info("e : " + e.getMessage());
		}
		return false;
	}

	public boolean isDisplayed(By by) {
		try {
			moveToElement(by);
			return driver.findElement(by).isDisplayed();
		} catch (Exception e) {
			return false;
		}
	}

	public void click(By by) {
		int scroll = 0;
		moveToElement(by);
		do {
			try {
				driver.findElement(by).click();
				break;
			} catch (WebDriverException e) {
				try {
					((JavascriptExecutor) driver)
							.executeScript("window.scrollTo(0," + driver.findElement(by).getLocation().y + ")");
					((JavascriptExecutor) driver).executeScript("arguments[0].click();", driver.findElement(by));
					break;
				} catch (Exception e2) {
					scroll++;
				}
			}
		} while (scroll <= 2);

		if (scroll == 3) {
			Assert.assertTrue(false, "Fail to click on link : " + by + " on page : " + driver.getCurrentUrl());
		}
	}

	public void sendKeys(By by, String value) {
		try {
			moveToElement(by);
			sleep(1);
			driver.findElement(by).sendKeys(value);
		} catch (NoSuchElementException e) {
			Assert.assertTrue(false, "Fail to send keys from text box : " + by + " on page : " + e.getMessage());
		} catch (Exception e) {
			logger.info(e.getMessage());
		}
	}

	public void clear(By by) {
		try {
			moveToElement(by);
			driver.findElement(by).clear();
		} catch (NoSuchElementException e) {
			Assert.assertTrue(false, "Fail to clear value from text box : " + by + " on page : " + e.getMessage());
		} catch (Exception e) {
			logger.info(e.getMessage());
		}
	}

	public String getText(By by) {
		try {
			moveToElement(by);
			return driver.findElement(by).getText().trim();
		} catch (NoSuchElementException e) {
			Assert.assertTrue(false, "Fail to get text value from : " + by + " on page : " + e.getMessage());
		} catch (Exception e) {
			logger.info(e.getMessage());
		}
		return null;
	}

	public boolean isClickable(By by) {
		try {
			moveToElement(by);
			Wait.until(ExpectedConditions.elementToBeClickable(by));
			return true;
		} catch (Exception e) {
			logger.info(e.getMessage());
			return false;
		}
	}

	public String getTextRuntime(By by) {
		try {
			moveToElement(by);
			return driver.findElement(by).getText().trim();
		} catch (Exception e) {
			logger.info(e.getMessage());
		}
		return "";
	}

	public String getAttribute(By by, String attribute) {
		try {
			moveToElement(by);
			return driver.findElement(by).getAttribute(attribute).trim();
		} catch (NoSuchElementException e) {
			try {
				moveToElement(by);
				WebElement element = driver.findElement(by);
				((JavascriptExecutor) driver).executeScript(
						"window.scrollTo(" + element.getLocation().x + "," + element.getLocation().y + ")");
				((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
				return element.getAttribute(attribute).trim();
			} catch (Exception e2) {
				logger.info(e.getMessage());
				Assert.assertTrue(false, "Fail to get text value from : " + by + " on page : " + e.getMessage());
			}
		}
		return null;
	}

	public void clickOnLinkText(String linkText) {
		By by = By.linkText(linkText);
		try {
			moveToElement(by);
			sleep(1);
			driver.findElement(by).click();
		} catch (Exception e) {
			logger.info(e.getMessage());
		}
	}

	public boolean isLinkTextDisplay(String linkText) {
		return isDisplayed(By.linkText(linkText));
	}

	public void moveToElement(By by) {
		build = new Actions(driver);
		try {
			getHighlightElement(by);
			build.moveToElement(driver.findElement(by)).build().perform();
		} catch (Exception e) {
			logger.info(e.getMessage());
		}
	}

	public List<WebElement> findElements(By by) {
		try {
			List<WebElement> elements = driver.findElements(by);
			int size = elements.size();
			for (int i = 0; i < size; i++) {
				((JavascriptExecutor) driver).executeScript("arguments[0].setAttribute('style',arguments[1]);",
						elements.get(i), "border: 2px solid yellow;");
			}

		} catch (Exception e) {
			logger.info(e.getMessage());
		}
		return driver.findElements(by);
	}

	public WebElement findElement(By by) {
		try {
			moveToElement(by);
		} catch (Exception e) {
			logger.info(e.getMessage());
		}
		return driver.findElement(by);
	}
}
