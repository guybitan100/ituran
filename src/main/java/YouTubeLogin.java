import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.ie.InternetExplorerOptions;
import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import java.util.Collections;

import Pages.YouTubeLoginPage;

public class YouTubeLogin
{
	private WebDriver driver;
	WebElement element = null;
	YouTubeLoginPage youTubeLoginPage = null;

	@BeforeSuite public void setProperty(ITestContext context) throws InterruptedException
	{
		String seleniumBrowser = context.getCurrentXmlTest().getParameter("selenium.browser");
		String seleniumUrl = context.getCurrentXmlTest().getParameter("selenium.url");

		ClassLoader loader = YouTubeLogin.class.getClassLoader();
		if (seleniumBrowser.equalsIgnoreCase("chrome"))
		{
			String path2ChromeDriver = loader.getResource("chromedriver.exe").getPath();
			System.setProperty("webdriver.chrome.driver", path2ChromeDriver);
			ChromeOptions options = new ChromeOptions();
			options.setExperimentalOption("useAutomationExtension", false);
			options.setExperimentalOption("excludeSwitches", Collections.singletonList("enable-automation"));
			this.driver = new ChromeDriver(options);

		}
		else if (seleniumBrowser.equalsIgnoreCase("firefox"))
		{
			String path2FFDriver = loader.getResource("geckodriver.exe").getPath();
			System.setProperty("webdriver.gecko.driver", path2FFDriver);
			this.driver = new FirefoxDriver();
		}
		else if (seleniumBrowser.equalsIgnoreCase("ie"))
		{
			String path2FFDriver = loader.getResource("IEDriverServer.exe").getPath();
			System.setProperty("webdriver.ie.driver", path2FFDriver);
			InternetExplorerOptions capabilities = new InternetExplorerOptions();
			capabilities.ignoreZoomSettings();
			driver = new InternetExplorerDriver(capabilities);
		}
		driver.get(seleniumUrl);
		youTubeLoginPage = new YouTubeLoginPage(driver, context);
	}

	@Test public void login2Youtube() throws InterruptedException
	{
		element = youTubeLoginPage.getLoginButtonElement();
		Assert.assertNotNull(element);
		element.click();
		element = youTubeLoginPage.getEmailTextBoxElement();
		element.sendKeys(youTubeLoginPage.getEmail());
		element = youTubeLoginPage.getEmailNextElement();
		Assert.assertNotNull(element);
		element.click();
		Thread.sleep(2000);
		element = youTubeLoginPage.getPasswordTextBoxElement();
		element.sendKeys(youTubeLoginPage.getPassword());
		element = youTubeLoginPage.getPasswordNextElement();
		Assert.assertNotNull(element);
		element.click();
		driver.close();
	}

}
