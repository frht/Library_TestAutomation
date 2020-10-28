Feature: Borrowing books

  Background:
    Given user is on the landing page
    And user logs in as a "Student"
    And user is on Book Management page


  Scenario: User should be able to borrow available book and see it displayed under
  “Borrowing Books” as “not returned”

    When user clicks on “Borrow Book”
    Then user should see “The book has been borrowed” pop up alert on the top right of the page
    And user navigates to “Borrowing Books” Tab
    Then user should see the borrowed book displayed as “Not returned” and “Null” returned date

