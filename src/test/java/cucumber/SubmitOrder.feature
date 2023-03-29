@tag
  Feature: Submit a purchase order

    Background:
      Given I am on the landing page

    @Smoke
    Scenario Outline: Submit an order with valid data
      Given I am logged in with <email> and <password>
      When I add <product> to the cart
      And I verify that <product> has been added to the cart
      And I submit the order <country>
      Then I verify that correct <confirmationMsg> is displayed
      Examples:
        | email            | password   | product         | country      | confirmationMsg          |
        | alex@test.com    | Test1234!  | ZARA COAT 3     | Australia    | THANKYOU FOR THE ORDER.  |
        | alex2@test.com   | Test1234!2 | ADIDAS ORIGINAL | Canada       | THANKYOU FOR THE ORDER.  |



