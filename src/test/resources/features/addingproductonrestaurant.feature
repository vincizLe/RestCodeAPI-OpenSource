Feature: AddingProductToRestaurant


  Scenario: Add new product to my restaurant
    Given the owner wants to add on product endpoint
      | Name              |
      | arroz con pollo   |
    When owner add a new product
    Then the product will be added successfully