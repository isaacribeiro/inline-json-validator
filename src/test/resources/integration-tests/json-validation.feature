Feature: Validate a JSON string
  Any attribute annotated with @JSON must be a valid JSON object.

  Scenario: Empty string is not a valid JSON object.
    Given a BaseEntity instance whose value is <inputValue>
    And a list of <listOfMandatoryFields> to be checked
    When it is validated
    Then <expectedViolationNumber> violations should be raised.

    Examples:
      |inputValue|listOfMandatoryFields|expectedViolationNumber|
      |"abc"|a,b|1|
      |""||1|
      |"{}"||0|
      |"{}"||0|