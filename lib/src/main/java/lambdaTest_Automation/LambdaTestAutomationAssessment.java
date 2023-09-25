package lambdaTest_Automation;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;

public class LambdaTestAutomationAssessment extends CapabilitiesConfig {
	boolean status = false;
	private String websiteURL = "https://www.lambdatest.com";
	private String expectedURL = "https://www.lambdatest.com/integrations";

	@Test
	public void testSimple() throws Exception {
		try {
			RemoteWebDriver driver = desiredCapabilitiesConfig();
			driver.get(websiteURL);
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("__next")));
			WebElement seeAllIntegrationLinkText = driver.findElement(By.cssSelector("div.clearfix div.text-center a"));
			((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);",
					seeAllIntegrationLinkText);
			// To open the See All Integration Link in new tab
			String clicklnk = Keys.chord(Keys.CONTROL,Keys.ENTER);
			seeAllIntegrationLinkText.sendKeys(clicklnk);
			
			//Commented below line because it's opening in the same tab
			//((JavascriptExecutor) driver).executeScript("arguments[0].click();", seeAllIntegrationLinkText);
			List<String> totalWindowsOpened = new ArrayList<String>(driver.getWindowHandles());
			System.out.println("Total Number of windows" + totalWindowsOpened.size());
			driver.switchTo().window(totalWindowsOpened.get(1));
			Assert.assertEquals(driver.getCurrentUrl(), expectedURL,
					"Failed! Actual URL is not matched with Expected URL");
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	@AfterClass
	public void tearDown() throws Exception {
		if (remoteDriver != null) {
			((JavascriptExecutor) remoteDriver).executeScript("lambda-status=" + status);
			remoteDriver.quit();
		}
	}

}
