Feature: GeneratingSalesOnRestaurant

  Scenario: Generate a new sale on Restaurant
    Given the owner wants to add on salesrestaurant endpoint
      | datetime   | clientFullname
      | 28-10-2020 | Yorch Forsay
    When owner add a new sale on restaurant
    Then the sale will be added successfully