package com.redbus.automation.runner;

import org.junit.runner.RunWith;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;

@RunWith(Cucumber.class)
@CucumberOptions(features = { "src/test/features/redBusBusSearches.feature" },

		strict = true, plugin = { "pretty", "html:target/cucumber-htmlreport",
				"json:target/cucumber.json" }, monochrome = true,

		glue = { "com.redbus.automation.busSearches" })

public class RunBusesTest {

}