package test;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.Properties;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.*;

import io.github.bonigarcia.wdm.WebDriverManager;
import pages.HomePage;
import pages.MobilesPage;

public class AmazonAutomationTest {

    private WebDriver driver;

    HomePage homePage;

    MobilesPage mobilesPage;

    ExtentReports extentReports;

    ExtentTest logger;

    ExtentHtmlReporter extentHtmlReporter;

    @BeforeTest
    public void startReport() {
        extentHtmlReporter=new ExtentHtmlReporter("\\TestNGCustomReport\\SeleniumWTestNG\\SeleniumTestNGWCustomReport\\test-output\\STMExtentReport.html");

        extentReports=new ExtentReports();
        extentReports.attachReporter(extentHtmlReporter);
        extentReports.setSystemInfo("OS", "Windows");
        extentReports.setSystemInfo("AUT", "Amazon.in");
        extentReports.setSystemInfo("Tester", "Bhavesh Dilip");
    }

    @BeforeMethod()
    public void navigateToApplication() throws IOException {
        WebDriverManager.chromedriver().setup();

        driver = new ChromeDriver();

        driver.get(getURLDetails("applicationURL"));

        driver.manage().window().maximize();

        homePage = new HomePage(driver);

        mobilesPage = new MobilesPage(driver);

    }

    //Positive Samsung mobile count test scenario
    @Test(priority = 1)
    public void getSamsungMobiles() throws InterruptedException {

        ExtentTest logger=extentReports.createTest("Positive Scenario for total number of Samsung Mobiles on Amazon");

        logger.log(Status.INFO,"Selecting Mobiles Section");

        homePage.selectMobile(driver).click();

        mobilesPage.searchBoxEntry(driver).sendKeys("Samsung");

        mobilesPage.searchBoxSelect(driver).click();

        logger.log(Status.INFO,"Searching Samsung Mobiles");

        mobilesPage.waitForElementList(mobilesPage.mobileNames(driver));

        List<WebElement> mobileNames = mobilesPage.mobileNames(driver);

        for (WebElement mobile : mobileNames) {
            System.out.println("Mobiles are:" + mobile.getText());
        }

        int mobileCount = mobileNames.size();
        System.out.println("Search Count is:" + mobileCount);
        Assert.assertEquals(mobileCount, 32);
        logger.log(Status.PASS,"Samsung mobile count is as expected");

    }

    //Negative Samsung mobile count test scenario
    @Test(priority = 2)
    public void getSamsungMobilesNegative() throws InterruptedException {

        ExtentTest logger=extentReports.createTest("Negative Scenario for total number of Samsung Mobiles on Amazon");

        logger.log(Status.INFO,"Selecting Mobiles Section");

        homePage.selectMobile(driver).click();

        mobilesPage.searchBoxEntry(driver).sendKeys("Samsung");

        mobilesPage.searchBoxSelect(driver).click();

        logger.log(Status.INFO,"Searching Samsung Mobiles");

        mobilesPage.waitForElementList(mobilesPage.mobileNames(driver));

        List<WebElement> mobileNames = mobilesPage.mobileNames(driver);

        for (WebElement mobile : mobileNames) {
            System.out.println("Mobiles are:" + mobile.getText());
        }

        int mobileCount = mobileNames.size();
        System.out.println("Search Count is:" + mobileCount);
        logger.log(Status.FAIL,"Samsung mobile count is not as expected");
        Assert.assertEquals(mobileCount, 30);
    }

    @AfterMethod
    public void quitDriver() throws Exception {
        driver.quit();
    }

    @AfterTest
    public void cleanUp()
    {
        extentReports.flush();
    }

    public static String getURLDetails(String key) throws IOException {
        Properties prop = new Properties();
        String fileName = "\\SeleniumWTestNG\\SeleniumTestNGWCustomReport\\src\\test\\java\\resources\\data.config";
        try (FileInputStream fis = new FileInputStream(fileName)) {
            prop.load(fis);
        } catch (FileNotFoundException ex) {

        }
        return prop.getProperty(key);
    }

}
