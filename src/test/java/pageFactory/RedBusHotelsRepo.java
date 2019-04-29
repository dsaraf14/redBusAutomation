package pageFactory;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import junit.framework.Assert;

public class RedBusHotelsRepo {
	private WebDriver driver;
	private final int implicitWaitTime = 20;
	private WebDriverWait wait;

	@FindBy(xpath = "//a[contains(text(), \"HOTELS\")]")
	private WebElement hotels;

	@FindBy(id = "search_key")
	private WebElement city;

	@FindBy(xpath = "//span[text() = \"Andheri\"]")
	private WebElement cityAutoFill;

	@FindBy(id = "search_hotel")
	private WebElement search;

	@FindBy(xpath = "//button[text() = \"No Thanks\"]")
	private WebElement noThanks;

	@FindBy(xpath = "//*[span[text()=\"found\"]]//span[1]")
	private WebElement results;

	private String URL;

	public RedBusHotelsRepo(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		setURL();
		this.driver.manage().timeouts().implicitlyWait(implicitWaitTime, TimeUnit.SECONDS);
		wait = new WebDriverWait(driver, implicitWaitTime);
	}

	public void openRedBus() throws InterruptedException {
		driver.get(URL);
		Thread.sleep(6000);
	}

	public void clickButtons(String buttonName) {
		switch (buttonName.toUpperCase()) {
		case "HOTELS":
			hotels.click();
			break;
		case "SEARCH":
			search.click();
			break;
		default:
			Assert.fail("no switch cases is matched");
			break;
		}
	}

	public void setArea(String city) throws InterruptedException {
		wait.until(ExpectedConditions.visibilityOf(noThanks));
		noThanks.click();
		this.city.sendKeys(city);
		wait.until(ExpectedConditions.elementToBeClickable(cityAutoFill));
		cityAutoFill.click();
	}

	public void printResults() {
		System.out.println(results.getText() + " Found with the searched results");
	}
	
	private void setURL(){
		URL = System.getProperty("URL");
		if (URL == null || URL.trim().isEmpty()) {
			URL = "https://www.redbus.in";
		}
	}
}
