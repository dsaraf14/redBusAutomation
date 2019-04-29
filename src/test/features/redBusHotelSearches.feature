@RedBus_Hotels
Feature: Search Hotel and its full functionality
	Here we will test searching of the Hotel with domain www.redbus.in
	
@RedBus_SearchHotels
Scenario: Scenario_01 : It will search Hotels
	Given user is on the HOME page of RedBus app
	Then user click on "Hotels" button on the page
	When user enter "Andheri" in the city field
	Then user click on "Search" button on the page
	And print the results for the searching