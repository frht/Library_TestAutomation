@B220-238 @B220-243
Feature: As a student, a should be able to borrow a book

  Background:
    Given user is on the landing page
    And user logs in as a "Student"
    And user is on Book Management page

  @B220-237
  Scenario: User should be able to select the number of books displayed on one page
    When user clicks on show records drop down menu
    Then user should see the available options
      |5|
      |10|
      |15|
      |50|
      |100|
      |200|
      |500|


  Scenario: User should be able to see the numbers of books displayed per page
    And user clicks on show records drop down menu
    When user selects 500 records
    Then user should see 500 books per page

  @Test
  Scenario: User should be able to see different book categories
    When user clicks on Book Categories drop down menu
    Then user should be able to see all the categories available
      |ALL|
      |Action and Adventure|
      |Anthology|
      |Classic|
      |Comic and Graphic Novel|
      |Crime and Detective|
      |Drama|
      |Fable|
      |Fairy Tale|
      |Fan-Fiction|
      |Fantasy|
      |Historical Fiction|
      |Horror|
      |Science Fiction|
      |Biography/Autobiography|
      |Humor|
      |Romance|
      |Short Story|
      |Essay|
      |Memoir|
      |Poetry|

  @Test
  Scenario Outline: User should be able to choose from different book categories
    And user clicks on Book Categories drop down menu
    When user selects category "<Categories>" option
    Then user should see only category "<Categories>" books displayed

    Examples:
      |Categories|
      |ALL       |
      |Action and Adventure|
      |Anthology|
      |Classic|
      |Comic and Graphic Novel|
      |Crime and Detective|
      |Drama|
      |Fable|
      |Fairy Tale|
      |Fan-Fiction|
      |Fantasy|
      |Historical Fiction|
      |Horror|
      |Science Fiction|
      |Biography/Autobiography|
      |Humor|
      |Romance|
      |Short Story|
      |Essay|
      |Memoir|
      |Poetry|









