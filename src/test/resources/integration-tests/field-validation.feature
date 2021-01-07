Feature: Validate a specific JSON parameter
  Any attribute annotated with @JSON must be a valid JSON object.

  # Scenario A: Checks if a given JSON string contains a specific string parameter
  Scenario: A Base entity has a single @Property-annotated attribute that should contain a Non-empty String property called 'stringProperty''.
    Given a Base instance with a @Property-annotated value attribute equals to <inputValue>
    When it is validated
    Then <expectedViolationNumber> violations should be raised

    Examples:
      |inputValue|expectedViolationNumber|
      |""|1|
      |"\"key\": \"value\""|1|
      |"{\"key\": \"value\"}"|1|
      |"{\"stringProperty\": [ \"value\" ]}"|1|
      |"{\"stringProperty\": [ \"valueA\", \"valueB\" ]}"|1|
      |"{\"stringProperty\": { \"subKey\": \"subValue\"}}"|1|
      |"{\"stringProperty\": \"\"}"|1|
      |"{\"stringProperty\": \"value\"}"|0|