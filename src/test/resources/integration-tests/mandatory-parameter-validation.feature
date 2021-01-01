Feature: Validate mandatory parameters from an input JSON string
  Any attribute annotated with @JSON must be a valid JSON object.

  # Complex Scenario A: checks a single, mandatory parameter.
  Scenario: A Base entity has a single @Json-annotated attribute that expects the mandatory parameter 'key'
    Given a Base instance with a @Json-annotated value attribute equals to <inputValue> that expects the mandatory parameter \'key\'
    When it is validated
    Then <expectedViolationNumber> violations should be raised

    Examples:
      |inputValue|expectedViolationNumber|
      |""|1|
      |"\"key\": \"value\""|1|
      |"{\"key\": \"value\"}"|0|
      |"{\"wrong_key\": \"value\"}"|1|

  # Complex Scenario B: checks multiple, mandatory parameters.
  Scenario: A Base entity has a single @Json-annotated attribute that expects the mandatory parameters 'keyA' and 'keyB'
    Given a Base instance with a @Json-annotated value attribute equals to <inputValue> that expects the mandatory parameter \'keyA\' and \'keyB\'
    When it is validated
    Then <expectedViolationNumber> violations should be raised

    Examples:
      |inputValue|expectedViolationNumber|
      |""|1|
      |"\"key\": \"value\""|1|
      |"{\"keyA\": \"value\"}"|1|
      |"{\"keyA\": \"value\", \"keyB\": \"value\" }"|0|
      |"{\"wrong_key\": \"value\"}"|1|

  # Complex Scenario C: checks multiple, mandatory parameters with subnodes.
  Scenario: A Base entity has a single @Json-annotated attribute that expects the mandatory parameters 'key' and 'key.subkey'
    Given a Base instance with a @Json-annotated value attribute equals to <inputValue> that expects the mandatory parameter \'key\' and \'key.subkey\'
    When it is validated
    Then <expectedViolationNumber> violations should be raised

    Examples:
      |inputValue|expectedViolationNumber|
      |""|1|
      |"\"key\": \"value\""|1|
      |"{\"keyA\": \"value\"}"|1|
      |"{\"keyA\": \"value\", \"keyB\": \"value\" }"|1|
      |"{\"key\": { \"subkey\": \"value\" }}"|0|
      |"{\"wrong_key\": \"value\"}"|1|