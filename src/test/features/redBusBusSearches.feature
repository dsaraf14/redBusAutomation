@RedBus_Buses
Feature: Search bus and its full functionality
	Here we will test searching of the bus with domain www.redbus.in

Background: User is on Home Page
Given user is on the HOME page of RedBus web app

@RedBus_SearchBus
Scenario: Scenario_01 : It will search bus
	Then Enter "Mumbai (All Locations)" in the from field and "Nasik" in the to field
	And Enter today date in the onward date field
	Then user click on "Search Buses" button
	And select departure time as "After 6 pm" and bus type as "NonAC"
	Then click on "view seats" from the first bus in results
	And select 2 "Available Seats" in that bus
	Then select "Dadar" as boading point and "Nashik" as dropping point
	Then user click on "Proceed To Book" button
	And user select "No, I don't want insurance"
	And verify that total amount dispplayed on passenger detail is same as displayed on select boarding and dropping point
	
