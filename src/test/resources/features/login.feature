@login
Feature: login, As a user I want to login under different roles

  Background:
    Given user is on the landing page

  @librarian
  Scenario: Login as store manager
    When user logs in as a "librarian"
    And user verifies that  page title is "Library"


  @student
  Scenario: Login as a student
    When user logs in as a "student"
    And user verifies that  page title is "Library"

