Feature: GivingPermitsToConsultantOnRestaurant

  Scenario: Give permission of access to Consultant on Restaurant
    Given the owner wants ti give on permission endpoint
      | dateAssignment |
      | 30-10-2020     |
    When  the owner give permission to consultant
    Then the permit will be completed
