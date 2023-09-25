package lambdaTest_Automation;

import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.ITestContext;
import org.testng.annotations.BeforeTest;

public class CapabilitiesConfig {
	RemoteWebDriver remoteDriver = null;
	private String gridURL = "@hub.lambdatest.com/wd/hub";
	private String username = "sathish.loganathan";
	private String accessKey = "SOAsYGUwiZE43ucOmUm2ojiw7JwcBjl0vW4gePrdcJFNHPtCQT";
	private String browserName, browserVersion, platformName;

	@BeforeTest
	public void configValueFromXLM(ITestContext context) {
		browserName = context.getCurrentXmlTest().getParameter("browser");
		browserVersion = context.getCurrentXmlTest().getParameter("version");
		platformName = context.getCurrentXmlTest().getParameter("platform");
	}

	public RemoteWebDriver desiredCapabilitiesConfig() {
		DesiredCapabilities capabilities = new DesiredCapabilities();
		capabilities.setCapability("browserName", browserName);
		capabilities.setCapability("version", browserVersion);
		capabilities.setCapability("plaform", platformName);
		capabilities.setCapability("visual", true);
		capabilities.setCapability("video", true);
		capabilities.setCapability("selenium_version", "4.8.0");
		capabilities.setCapability("w3c", true);
		try {
			remoteDriver = new RemoteWebDriver(new URL("https://" + username + ":" + accessKey + gridURL), capabilities);
		} catch (MalformedURLException exception) {
			System.out.println("Unable to lanuch the URL");
		}
		return remoteDriver;

	}

}
