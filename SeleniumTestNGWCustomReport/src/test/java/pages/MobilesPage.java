package pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class MobilesPage extends Page {

	public MobilesPage(WebDriver driver) {
        super(driver);
    }
	
	public WebElement searchBoxEntry(WebDriver driver)
	{

		return (driver.findElement(By.xpath("//input[@id='twotabsearchtextbox']")));
	}
	
	public WebElement searchBoxSelect(WebDriver driver)
	{

		return (driver.findElement(By.xpath("//input[@id='nav-search-submit-button']")));
	}
	
	public List<WebElement> mobileNames(WebDriver driver)
	{

		return (driver.findElements(By.xpath("//span[@class='a-size-medium a-color-base a-text-normal']")));
	}
}
