package com.library.step_definitions;

import com.library.pages.BooksManagementPage;
import com.library.pages.BorrowingBooksPage;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;

public class BorrowBooks_StepDefinitions {

    BooksManagementPage booksManagementPage = new BooksManagementPage();
    BorrowingBooksPage borrowingBooksPage = new BorrowingBooksPage();


    @When("user clicks on “Borrow Book” for first available book")
    public void user_clicks_on_borrow_book() {
        booksManagementPage.borrowFirstBookAvailable();
    }

    @Then("user should see “The book has been borrowed” pop up alert on the top right of the page")
    public void user_should_see_the_book_has_been_borrowed_pop_up_alert_on_the_top_right_of_the_page() {
        Assert.assertTrue(booksManagementPage.alertIsDisplayed());
    }

    private String bookBorrowedInfo;
    @When("user clicks on “Borrow Book” for first available book and user navigates to “Borrowing Books” Tab")
    public void user_clicks_on_borrow_book_for_first_available_book_and_user_navigates_to_borrowing_books_tab() {
        bookBorrowedInfo = booksManagementPage.borrowAndGetNameFirstBookAvailable();
        booksManagementPage.openBorrowingBooksInNewTab();
        booksManagementPage.goToBorrowingBooksTab();
    }

    @Then("user should see the borrowed book displayed as “Not returned” and “Null” returned date")
    public void user_should_see_the_borrowed_book_displayed_as_not_returned_and_null_returned_date() {
        Assert.assertTrue(borrowingBooksPage.lastBorrowedBookIsDisplayed(bookBorrowedInfo));
    }

}
