@B220-238 @B220-243
Feature: Select amount of books to be displayed

  Background:
    Given user is on the landing page
    And user logs in as a "Student"
    And user is on Book Management page
    And user clicks on show records drop down menu

  @B220-237
  Scenario: User should be able to select the number of books displayed on one page
    Then user should see the available options
      |5|
      |10|
      |15|
      |50|
      |100|
      |200|
      |500|

  Scenario: User should be able to see the number of books displayed per page
    When user selects 500 records per page
    Then user should see 500 books per page

  @Test
  Scenario Outline: User should be able to see the selected amount of books per page on every page
    And when user selects <number> records per page
    Then user should see <number> books per page in every page
    Examples:
    |number|
    |5|
    |10|
    |15|
    |50|
    |100|
    |200|
    |500|

  Scenario Outline: User should be able to sort the books by clicking on the header cells
    When user click on header "<table header options>"
    Then the header "<table header options>" should be sorted in ascending order
    Examples:
    |table header options|
    |ISBN                |
    |Name                |
    |Author              |
    |Category            |
    |Year                |
    |Borrowed By         |
















