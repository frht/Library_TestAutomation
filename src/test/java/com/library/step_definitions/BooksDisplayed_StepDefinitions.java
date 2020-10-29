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

    @Then("user should see books sorted in descending order by {string} number")
    public void user_should_see_books_sorted_in_descending_order_by_number(String tableHeaderOption) {
        Assert.assertTrue( booksManagementPage.booksSortedInDescendingOrderBy(tableHeaderOption));
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
        booksManagementPage.selectNumberOfBooksToBeDisplay(optionValue);
    }

    @Then("user should see {int} books per page")
    public void user_should_see_books_per_page(Integer numberOfBooksDisplayed) {
        Integer actualNumberOfBook = booksManagementPage.currentNumberOfBooksDisplayed();
        Assert.assertEquals(actualNumberOfBook, numberOfBooksDisplayed);

    }

    @Given("when user selects {int} records per page")
    public void when_user_selects_records_per_page(Integer booksPerPage) {
       booksManagementPage.selectNumberOfBooksToBeDisplay(booksPerPage);
    }

    @Then("user should see {int} books per page in every page")
    public void user_should_see_books_per_page_in_every_page(Integer expectedNumberOfBookPerPage) {
       booksManagementPage.expectedNumberOfBookPerPage(expectedNumberOfBookPerPage);
    }

    @When("user click on the header {string}")
    public void user_click_on_the_header(String tableHeaderOption) {
        booksManagementPage.clickOnTableHeaderOption(tableHeaderOption);
    }

    @Then("the column {string} should be sorted in ascending order")
    public void the_header_should_be_sorted_in_ascending_order(String tableHeaderOption) {
        Assert.assertTrue(booksManagementPage.booksSortedInAscendingOrderBy(tableHeaderOption));
    }

    @When("user double clicks on the header {string}")
    public void user_double_clicks_on_the_header(String headerOption) {
        booksManagementPage.doubleClickOnHeader(headerOption);
    }

    @Then("the column {string} should be sorted in descending order")
    public void the_column_should_be_sorted_in_descending_order(String headerOption) {
       booksManagementPage.booksSortedInDescendingOrderBy(headerOption);
    }








}
