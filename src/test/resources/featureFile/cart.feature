Feature: Cart Functionality

  Scenario: Add and remove a product from the cart
    Given the user is on the SauceDemo login page
    When the user logs in with valid credentials
    And the user adds a product to the cart
    And the user opens the cart page
    Then the product should be visible in the cart
    When the user removes the product
    Then the cart should be empty
