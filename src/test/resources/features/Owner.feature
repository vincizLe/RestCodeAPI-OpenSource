Feature:  Look up Owner by names
  Scenario: Successfully look up Owner
    Given User needs to look up a names
    When User writes down names
    Then Owner with names shows up
