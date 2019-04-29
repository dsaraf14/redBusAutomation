package com.redbus.automation.hotelSearches;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import pageFactory.RedBusHotelsRepo;

public class HotelSearchesTest {

	WebDriver driver;
	RedBusHotelsRepo redBusHotelsRepo;
	double boardingAndDropPageAmount;

	@Given("^user is on the HOME page of RedBus app$")
	public void user_is_on_the_home_page_of_redbus_app() throws Throwable {
		System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		redBusHotelsRepo = new RedBusHotelsRepo(driver);
		redBusHotelsRepo.openRedBus();
	}

	@Then("^user click on \"([^\"]*)\" button on the page$")
	public void user_click_on_something_button_on_the_page(String button) throws Throwable {
		redBusHotelsRepo.clickButtons(button);
	}

	@When("^user enter \"([^\"]*)\" in the city field$")
	public void user_enter_something_in_the_city_field(String place) throws Throwable {
		redBusHotelsRepo.setArea(place);
	}

	@And("^print the results for the searching$")
	public void print_the_results_for_the_searching() throws Throwable {
		redBusHotelsRepo.printResults();
	}

	@After("@RedBus_Hotels")
	public void afterClass(Scenario scenario) {
		driver.quit();
	}
}
