package com.library.step_definitions;

import com.library.pages.LoginPage;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;


public class LoginStepDefinitions {

    LoginPage loginPage = new LoginPage();

    @Given("user is on the landing page")
    public void user_is_on_the_landing_page() {
       loginPage.getUrl();
    }

    @When("user logs in as a {string}")
    public void user_logs_in_as_a(String role) {
       loginPage.login(role);
    }

    @When("user verifies that  page title is {string}")
    public void user_verifies_that_page_title_is(String expectedPageTitle) {
        loginPage.verifyTitle(expectedPageTitle);
    }

}
