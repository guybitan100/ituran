package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.ITestContext;

public class YouTubeLoginPage
{
	private WebDriver driver = null;
	private final String loginButtonSelector = "//ytd-button-renderer[@class='style-scope ytd-masthead style-suggestive size-small']/a[@class='yt-simple-endpoint style-scope ytd-button-renderer' and 1]/paper-button[@id='button' and @class='style-scope ytd-button-renderer style-suggestive size-small' and 1]/yt-formatted-string[@id='text' and @class='style-scope ytd-button-renderer style-suggestive size-small' and 1]";
	private final String emailTextBoxSelector = "identifierId";
	private final String passwordTextBoxSelector = "input[type=password]";
	private final String emailNextButtonSelector = ".RveJvd";
	private final String passwordNextButtonSelector = ".CwaK9";
	String email = "";
	String password = "";

	public YouTubeLoginPage(WebDriver driver, ITestContext context)
	{
		this.driver = driver;
		this.email = context.getCurrentXmlTest().getParameter("selenium.email");
		this.password = context.getCurrentXmlTest().getParameter("selenium.password");
	}

	public WebElement getLoginButtonElement()
	{
		return driver.findElement(By.xpath(loginButtonSelector));
	}

	public WebElement getEmailTextBoxElement()
	{
		return driver.findElement(By.id(emailTextBoxSelector));
	}

	public WebElement getPasswordTextBoxElement()
	{
		return driver.findElement(By.cssSelector(passwordTextBoxSelector));
	}

	public WebElement getEmailNextElement()
	{
		return driver.findElement(By.cssSelector(emailNextButtonSelector));
	}

	public WebElement getPasswordNextElement()
	{
		return driver.findElement(By.cssSelector(passwordNextButtonSelector));
	}

	public String getEmail()
	{
		return email;
	}

	public String getPassword()
	{
		return password;
	}
}
