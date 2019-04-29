package com.redbus.automation.busSearches;

import java.util.Date;

import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import pageFactory.RedBusBusTicketsRepo;

public class BusSearchesTest {

	WebDriver driver;
	RedBusBusTicketsRepo redBusBusTicketsRepo;
	double boardingAndDropPageAmount;

	@Given("^user is on the HOME page of RedBus web app$")
	public void user_is_on_the_home_page_of_redbus_web_app() throws Throwable {

		System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		redBusBusTicketsRepo = new RedBusBusTicketsRepo(driver);
		redBusBusTicketsRepo.openRedBus();
	}

	@Then("^Enter \"([^\"]*)\" in the from field and \"([^\"]*)\" in the to field$")
	public void enter_something_in_the_from_field_and_something_in_the_to_field(String from, String to)
			throws Throwable {
		redBusBusTicketsRepo.setSource(from);
		redBusBusTicketsRepo.setDestination(to);
	}

	@Then("^user click on \"([^\"]*)\" button$")
	public void user_click_on_something_button(String button) throws Throwable {
		redBusBusTicketsRepo.clickButtons(button);
	}

	@Then("^click on \"([^\"]*)\" from the first bus in results$")
	public void click_on_something_from_the_first_bus_in_results(String button) throws Throwable {
		redBusBusTicketsRepo.clickButtons(button);
	}

	@Then("^select \"([^\"]*)\" as boading point and \"([^\"]*)\" as dropping point$")
	public void select_something_as_boading_point_and_something_as_dropping_point(String boardingPoint,
			String dropPoint) throws Throwable {
		redBusBusTicketsRepo.clickButtons(boardingPoint);
		redBusBusTicketsRepo.clickButtons(dropPoint);
		boardingAndDropPageAmount = redBusBusTicketsRepo.getTotalAmountOnBoardingAndDropPage();
	}

	@And("^Enter today date in the onward date field$")
	public void enter_todays_date_in_the_onward_date_field() throws Throwable {
		Date todayDate = new Date();
		redBusBusTicketsRepo.setDate(todayDate.toString());
	}

	@And("^select departure time as \"([^\"]*)\" and bus type as \"([^\"]*)\"$")
	public void select_departure_time_as_something_and_bus_type_as_something(String departureTime, String busType)
			throws Throwable {
		redBusBusTicketsRepo.clickButtons(departureTime);
		redBusBusTicketsRepo.waitForElementToBeLoaded(busType);
		redBusBusTicketsRepo.clickButtons(busType);
	}

	@And("^select (\\d+) \"([^\"]*)\" in that bus$")
	public void select_something_in_that_bus(int seatNumbers, String strArg1) throws Throwable {
		redBusBusTicketsRepo.selectAvailableSeats();
	}

	@And("^user select \"([^\"]*)\"$")
	public void user_select_something(String button) throws Throwable {
		redBusBusTicketsRepo.clickButtons(button);
	}

	@And("^verify that total amount dispplayed on passenger detail is same as displayed on select boarding and dropping point$")
	public void verify_that_total_amount_dispplayed_on_passenger_detail_is_same_as_displayed_on_select_boarding_and_dropping_point()
			throws Throwable {
		double passengetePageAmount = redBusBusTicketsRepo.getTotalAmountOnPassengetDetailPage();
		String errorMsg = "Passenger page amount and Boarding Drop page amounts are not equal, boardingAndDropPageAmount is: "
				+ boardingAndDropPageAmount + ", passengetePageAmount is: " + passengetePageAmount;

		Assert.assertTrue(errorMsg, boardingAndDropPageAmount == passengetePageAmount);
	}
	
	@After("@RedBus_Buses")
	public void afterClass(Scenario scenario) {
		driver.quit();
	}
}
