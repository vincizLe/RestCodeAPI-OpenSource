Feature: AddingSpecialtyOnConsultant


  Scenario: Add new specialty to Consultant
    Given the consultant wants to add on specialty endpoint
      | Name                    |
      | Microbusiness expert    |
    When consultant add a new specialty
    Then the specialty will be added successfully
