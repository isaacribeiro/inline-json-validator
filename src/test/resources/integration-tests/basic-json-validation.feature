Feature: Validate a JSON string
  Any attribute annotated with @JSON must be a valid JSON object.

  # Basic Scenario: checks a Json value
  Scenario: A Base entity has a single @Json-annotated attribute and there are no mandatory parameters.
    Given a Base instance with a @Json-annotated value attribute equals to <inputValue>
    When it is validated
    Then <expectedViolationNumber> violations should be raised

    Examples:
      |inputValue|expectedViolationNumber|
      |""|1|
      |"\"key\": \"value\""|1|
      |"{\"key\": \"value\"}"|0|