@B220-238 @B220-243
Feature: Select amount of books to be displayed

  Background:
    Given user is on the landing page
    And user logs in as a "Student"
    And user is on Book Management page


  Scenario: Books should be sort in ascending order base on ISBN number
    Then user should see books sorted in descending order by "ISBN" number

  @B220-237
  Scenario: User should be able to select the number of books displayed on one page
    And user clicks on show records drop down menu
    Then user should see the available options
      |5|
      |10|
      |15|
      |50|
      |100|
      |200|
      |500|

  Scenario: User should be able to see the number of books displayed per page
    And user clicks on show records drop down menu
    When user selects 500 records per page
    Then user should see 500 books per page


  Scenario Outline: User should be able to see the selected amount of books per page on every page
    And user clicks on show records drop down menu
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

 @bug
  Scenario Outline: User should be able to sort the books in ascending by clicking on the header cells
    When user click on the header "<table header options>"
    Then the column "<table header options>" should be sorted in ascending order
    Examples: #TODO bug -> Name tab ascending order has bug
    |table header options|
    |Name                |
    |Author              |
    |Category            |
    |Year                |
    |Borrowed By         |


  Scenario Outline: User should be able to sort the books in descending order by double-clicking on the header cells
    When user double clicks on the header "<table header options>"
    Then the column "<table header options>" should be sorted in descending order
    Examples:
      |table header options|
      |Name                |
      |Author              |
      |Category            |
      |Year                |
      |Borrowed By         |
















