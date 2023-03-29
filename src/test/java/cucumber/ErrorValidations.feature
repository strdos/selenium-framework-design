@tag
Feature: Error Validations

  @ErrorValidation
  Scenario Outline: Submit an order with invalid data
    Given I am on the landing page
    Given I am logged in with <email> and <password>
    Then <errorMessage> text is correct

    Examples:
      | email            | password   | errorMessage                  |
      | alex@test.com    | invalid    | Incorrect email or password.  |
