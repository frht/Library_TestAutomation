package com.library.step_definitions;

import com.library.pages.BooksManagementPage;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;

import java.util.List;

public class BooksCategory_StepDefinitions {

    BooksManagementPage booksManagementPage = new BooksManagementPage();


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

    @Then("user should see only category {string} books displayed")
    public void user_should_see_only_books_displayed(String category) {
        booksManagementPage.booksDisplayCategoryIsCorrect(category);
    }

}
