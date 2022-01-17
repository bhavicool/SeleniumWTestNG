package test;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;
import pages.HomePage;
import pages.MobilesPage;

public class AmazonAutomationTest {

	private WebDriver driver;

	HomePage homePage;

	MobilesPage mobilesPage;

	@BeforeMethod()
	public void navigateToApplication() throws IOException
	{
		WebDriverManager.chromedriver().setup();

		driver=new ChromeDriver();

		driver.get(getURLDetails("applicationURL"));

		driver.manage().window().maximize();

		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

		homePage=new HomePage(driver);

		mobilesPage=new MobilesPage(driver);

	}

	//Test 1 scenario
	@Test(priority=1)	
	public void getSamsungMobiles() throws InterruptedException{

		homePage.selectMobile(driver).click();;

		mobilesPage.searchBoxEntry(driver).sendKeys("Samsung");

		mobilesPage.searchBoxSelect(driver).click();

		mobilesPage.waitForElementList(mobilesPage.mobileNames(driver));

		List<WebElement> mobileNames=mobilesPage.mobileNames(driver);

		for(WebElement mobile: mobileNames)
		{
			System.out.println("Mobiles are:"+mobile.getText());
		}

		/*String URL = driver.getCurrentUrl();
		//System.out.println("URL is:"+URL);
		Assert.assertTrue(URL.contains("blog"));

		int searchCount=connectCOOBlog.getSearchResults();
		//System.out.println("Search Count is:"+searchCount);
		Assert.assertEquals(searchCount, 6);*/

	}

	@AfterMethod
	public void quitDriver() throws Exception {
		driver.quit();
	}

	public static String getURLDetails(String key) throws IOException
	{
		Properties prop = new Properties();
		String fileName = "\\SeleniumWTestNG\\SeleniumTestNGWCustomReport\\src\\test\\java\\resources\\data.config";
		try (FileInputStream fis = new FileInputStream(fileName)) {
			prop.load(fis);
		} catch (FileNotFoundException ex) {

		} 
		return prop.getProperty(key);
	}

}
