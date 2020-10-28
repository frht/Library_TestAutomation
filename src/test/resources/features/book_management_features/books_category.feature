Feature: Select category of books to be displayed

  Background:
    Given user is on the landing page
    And user logs in as a "Student"
    And user is on Book Management page
    And user clicks on Book Categories drop down menu


  Scenario: User should be able to see different book categories
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


  Scenario Outline: User should be able to choose from different book categories
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