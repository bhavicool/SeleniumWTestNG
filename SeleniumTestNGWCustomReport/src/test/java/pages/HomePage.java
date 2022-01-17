package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class HomePage extends Page {
 
	public HomePage(WebDriver driver) {
        super(driver);
    }
	
	public WebElement selectMobile(WebDriver driver)
	{

		return (driver.findElement(By.xpath("//a[text()='Mobiles']")));
	}
}
