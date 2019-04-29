package pageFactory;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import junit.framework.Assert;

public class RedBusBusTicketsRepo {

	private WebDriver driver;
	private final int implicitWaitTime = 20;
	private WebDriverWait wait;

	@FindBy(id = "src")
	private WebElement source;

	@FindBy(xpath = "//input[@id= \"src\"]/following-sibling::ul/li[1]")
	private WebElement sourceAutoFill;

	@FindBy(id = "dest")
	private WebElement destination;

	@FindBy(xpath = "//input[@id= \"dest\"]/following-sibling::ul/li[1]")
	private WebElement destinationAutoFill;

	@FindBy(xpath = "//label[@for = \"onward_cal\"]")
	private WebElement date;

	@FindBy(xpath = "//div[@class = \"rb-calendar\"]//td[@class=\"current day\"]")
	private WebElement currentDate;

	@FindBy(id = "search_btn")
	private WebElement searchBuses;

	@FindBy(xpath = "//label[@for = \"dtAfter 6 pm\" and @class = \"custom-checkbox\"]")
	private WebElement departureTimeAfter6pm;

	@FindBy(xpath = "//label[@for = \"bt_NONAC\" and @class = \"custom-checkbox\"]")
	private WebElement busTypeNonAC;

	@FindBy(xpath = "//div[@class = \"clearfix m-top-16\"]/div[contains(text(), \"View Seats\")]")
	private List<WebElement> viewSeats;

	@FindBy(xpath = "//canvas[@data-type=\"lower\"]")
	private WebElement seatsCanvas;

	@FindBy(xpath = "//*[*[*[@title = \"Dadar-(E) Dolphin HO, Irani Mension\"]]]/div[1]/div")
	private WebElement boardingPoint;

	@FindBy(xpath = "//*[*[span[@title = \"Nashik\"]]]/div[1]/div")
	private WebElement dropPoint;

	@FindBy(xpath = "//button[text() = \"Proceed to book\"]")
	private WebElement proceedToBook;

	@FindBy(xpath = "//button[contains(text(), \"continue\")]")
	private WebElement continueButton;

	@FindBy(xpath = "//label[contains(text(), \"No, I don't want insurance\")]/span")
	private WebElement dontWantInsurance;

	@FindBy(xpath = "//*[span[contains(@class, \"fr fare-summary-value\")]]/span[2]/span[2]")
	private WebElement totalAmountOfBAndDPoint;

	@FindBy(xpath = "//div[contains(text(), \"Total Amount\")]/following-sibling::div")
	private WebElement totalAmountOfPassengerDetail;

	private String URL;

	public RedBusBusTicketsRepo(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		setURL();
		this.driver.manage().timeouts().implicitlyWait(implicitWaitTime, TimeUnit.SECONDS);
		wait = new WebDriverWait(driver, implicitWaitTime);
	}

	public void setSource(String sourceName) {
		source.sendKeys(sourceName);
		sourceAutoFill.click();
	}

	public void setDestination(String destinationName) {
		destination.sendKeys(destinationName);
		destinationAutoFill.click();
	}

	public void setDate(String date) throws InterruptedException {
		wait.until(ExpectedConditions.elementToBeClickable(currentDate));
		currentDate.click();

	}

	public void clickButtons(String buttonName) {
		switch (buttonName.toUpperCase()) {
		case "SEARCH BUSES":
			searchBuses.click();
			break;
		case "AFTER 6 PM":
			if (!departureTimeAfter6pm.isSelected()) {
				departureTimeAfter6pm.click();
			}
			break;
		case "NONAC":
			if (!departureTimeAfter6pm.isSelected()) {
				busTypeNonAC.click();
			}
			break;
		case "VIEW SEATS":
			for (WebElement webElement : viewSeats) {
				webElement.click();
				break;
			}
			break;
		case "DADAR":
			boardingPoint.click();
			break;
		case "NASHIK":
			dropPoint.click();
			break;
		case "PROCEED TO BOOK":
			proceedToBook.click();
			break;
		case "CONTINUE":
			continueButton.click();
			break;
		case "NO, I DON'T WANT INSURANCE":
			if (!dontWantInsurance.isSelected()) {
				dontWantInsurance.click();
			}
			break;
		default:
			Assert.fail("no switch cases is matched");
			break;
		}
	}

	public void openRedBus() throws InterruptedException {
		driver.get(URL);
		Thread.sleep(6000);
	}

	public void selectAvailableSeats() throws InterruptedException {
		Actions builder = new Actions(driver);
		Action drawAction = builder.moveToElement(seatsCanvas, 30, 30).clickAndHold(seatsCanvas).moveByOffset(80, 80)
				.moveByOffset(50, 20).release(seatsCanvas).build();
		drawAction.perform();
	}

	public double getTotalAmountOnPassengetDetailPage() {
		String amount = totalAmountOfPassengerDetail.getText().trim();
		double realAmount = Double.parseDouble(amount.substring(amount.indexOf("INR") + 3, amount.length()).trim());
		return realAmount;
	}

	public double getTotalAmountOnBoardingAndDropPage() throws InterruptedException {
		return Double.parseDouble(totalAmountOfBAndDPoint.getText().trim());
	}

	public void waitForElementToBeLoaded(String locator) {
		WebDriverWait wait = new WebDriverWait(driver, implicitWaitTime);
		switch (locator.toUpperCase()) {
		case "NONAC":
			wait.until(ExpectedConditions.visibilityOf(busTypeNonAC));
			break;
		default:
			Assert.fail("no switch cases is matched");
			break;
		}
	}
	
	private void setURL(){
		URL = System.getProperty("URL");
		if (URL == null || URL.trim().isEmpty()) {
			URL = "https://www.redbus.in";
		}
	}
}