package com.library.step_definitions;

import com.library.pages.BooksManagementPage;
import com.utils.BrowserUtils;
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
        List<String> expectedList = booksManagementPage.getShowNumberOfRecordsOptionsList();
        Assert.assertEquals(actualList, expectedList);
    }

    @When("user selects {int} records")
    public void user_selects_records(Integer optionValue) {
        booksManagementPage.selectShowNumberOfRecordsDisplayed(optionValue);
    }


    @Then("user should see {int} books per page")
    public void user_should_see_books_per_page(Integer numberOfBooksDisplayed) {
        Integer actualNumberOfBook = booksManagementPage.currentNumberOfBooksDisplayed();
        Assert.assertEquals(actualNumberOfBook, numberOfBooksDisplayed);

    }

    @When("user clicks on Book Categories drop down menu")
    public void user_clicks_on_book_categories_drop_down_menu() {
        booksManagementPage.clickCategoryDropDownButton();
    }


    @Then("user should be able to see all the categories available")
    public void user_should_be_able_to_see_all_the_categories_available(List<String> expectedCategories) {
        Assert.assertEquals(expectedCategories, booksManagementPage.getCategoryOptionsList());
    }



    @When("user selects category {string} option")
    public void user_selects_option(String category) {
        booksManagementPage.selectCategory(category);
    }

    //TODO: get all the books display and assert they all are the given category
    @Then("user should see only category {string} books displayed")
    public void user_should_see_only_books_displayed(String category) {
        booksManagementPage.booksDisplayCategoryIsCorrect(category);
    }


}
