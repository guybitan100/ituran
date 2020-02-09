import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.ie.InternetExplorerOptions;
import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.Reporter;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import java.util.Collections;

public class YouTubeLogin
{
	private static final Logger logger = Logger.getLogger(YouTubeLogin.class);
	private WebDriver driver;
	private String seleniumEmail;
	private String seleniumPassword ;
	@BeforeSuite
	public void setProperty(ITestContext context) throws InterruptedException
	{
		String seleniumBrowser = context.getCurrentXmlTest().getParameter("selenium.browser");
		String seleniumUrl = context.getCurrentXmlTest().getParameter("selenium.url");
		seleniumEmail = context.getCurrentXmlTest().getParameter("selenium.email");
		seleniumPassword = context.getCurrentXmlTest().getParameter("selenium.password");

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
	}

	@Test
	public void login2Youtube() throws InterruptedException
	{
		String loginButton = "//ytd-button-renderer[@class='style-scope ytd-masthead style-suggestive size-small']/a[@class='yt-simple-endpoint style-scope ytd-button-renderer' and 1]/paper-button[@id='button' and @class='style-scope ytd-button-renderer style-suggestive size-small' and 1]/yt-formatted-string[@id='text' and @class='style-scope ytd-button-renderer style-suggestive size-small' and 1]";
		String emailTextBox  = "identifierId";
		WebElement element = driver.findElement(By.xpath(loginButton));
		Reporter.log("Verify login button exist");
		Assert.assertNotNull(element);
		element.click();
		element = driver.findElement(By.id(emailTextBox));
		element.sendKeys(seleniumEmail);
		element = driver.findElement(By.cssSelector(".RveJvd"));
		Assert.assertNotNull(element);
		element.click();
		Thread.sleep(2000);
		element = driver.findElement(By.cssSelector("input[type=password]"));
		element.sendKeys(seleniumPassword);
		element = driver.findElement(By.cssSelector(".CwaK9"));
		Assert.assertNotNull(element);
		element.click();
		driver.close();
	}

}
