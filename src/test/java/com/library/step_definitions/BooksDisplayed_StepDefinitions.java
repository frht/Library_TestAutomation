package com.library.step_definitions;

import com.library.pages.BooksManagementPage;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import java.util.List;

public class BooksDisplayed_StepDefinitions {

    BooksManagementPage booksManagementPage = new BooksManagementPage();

    @Given("user is on Book Management page")
    public void user_is_on_book_management_page() {
        booksManagementPage.bookManagementSubtitleIsDisplayed();
    }

    @When("user clicks on show records drop down menu")
    public void user_clicks_on_show_records_drop_down_menu() {
        booksManagementPage.clickShowNumberOfRecordDropdown();
    }

    @Then("user should see the available options")
    public void user_should_see_the_available_options(List<String> actualList) {
        List<String> expectedList = booksManagementPage.getNumberOfBooksToDisplayOptionList();
        Assert.assertEquals(actualList, expectedList);
    }


    @When("user selects {int} records per page")
    public void user_selects_records(Integer optionValue) {
        booksManagementPage.selectShowNumberOfRecordsDisplayed(optionValue);
    }


    @Then("user should see {int} books per page")
    public void user_should_see_books_per_page(Integer numberOfBooksDisplayed) {
        Integer actualNumberOfBook = booksManagementPage.currentNumberOfBooksDisplayed();
        Assert.assertEquals(actualNumberOfBook, numberOfBooksDisplayed);

    }

    @Given("when user selects {int} records per page")
    public void when_user_selects_records_per_page(Integer booksPerPage) {
       booksManagementPage.selectShowNumberOfRecordsDisplayed(booksPerPage);
    }

    @Then("user should see {int} books per page in every page")
    public void user_should_see_books_per_page_in_every_page(Integer expectedNumberOfBookPerPage) {
       booksManagementPage.allPagesContain(expectedNumberOfBookPerPage);
    }
}
