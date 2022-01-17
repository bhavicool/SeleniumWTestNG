package pages;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Page {

	protected WebDriver driver;
	protected WebDriverWait wait;

	protected Page(WebDriver driver) {
		this.driver = driver;	
		wait = new WebDriverWait(driver, 25);
	}

	public void waitForElement(WebElement element) {
		wait.until(ExpectedConditions.visibilityOf(element));

	}

	protected void waitForElementToBeClickable(WebElement element) {
		wait.until(ExpectedConditions.elementToBeClickable(element));

	}

	public void waitForElementList(List<WebElement> element) {
		wait.until(ExpectedConditions.visibilityOfAllElements(element));
	}

	protected void selectElement(WebElement element) {
		wait.until(ExpectedConditions.elementToBeClickable(element));
		element.click();
	}

	protected void sendValue(WebElement element, String text) {
		waitForElement(element);
		element.clear();
		element.sendKeys(text);
	}

	protected void sendValue(WebElement element, int text) {
		waitForElement(element);
		element.clear();
		element.sendKeys(String.valueOf(text));
	}

	protected String getElementText(WebElement element)
	{
		return element.getText();
	}

}
