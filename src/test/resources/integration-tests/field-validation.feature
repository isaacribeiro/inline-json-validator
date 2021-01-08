Feature: Validate a specific JSON parameter
  Any attribute annotated with @JSON must be a valid JSON object.

  # Scenario A: Checks if a given JSON string contains a specific non-empty string property
  Scenario: A Base entity has a single @Property-annotated attribute that should contain a String property called 'stringProperty''.
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
      |"{\"stringProperty\": 1.0 }"|1|
      |"{\"stringProperty\": false }"|1|
      |"{\"stringProperty\": true }"|1|
      |"{\"stringProperty\": \"\"}"|0|
      |"{\"stringProperty\": \"value\"}"|0|

   # Scenario B: Checks if a given JSON string contains a specific numeric property.
    Scenario: A Base entity has a single @Property-annotated attribute that should contain a Numeric property called 'numericProperty''.
      Given a Base instance with a Numeric @Property-annotated value attribute equals to <inputValue>
      When it is validated
      Then <expectedViolationNumber> violations should be raised

      Examples:
        |inputValue|expectedViolationNumber|
        |""|1|
        |"\"key\": \"value\""|1|
        |"{\"key\": \"value\"}"|1|
        |"{\"numericProperty\": [ \"value\" ]}"|1|
        |"{\"numericProperty\": [ \"valueA\", \"valueB\" ]}"|1|
        |"{\"numericProperty\": { \"subKey\": \"subValue\"}}"|1|
        |"{\"numericProperty\": false }"|1|
        |"{\"numericProperty\": true }"|1|
        |"{\"numericProperty\": \"\"}"|1|
        |"{\"numericProperty\": \"value\"}"|1|
        |"{\"numericProperty\": 1.0 }"|0|
        |"{\"numericProperty\": 0.05 }"|0|
        |"{\"numericProperty\": 1e4 }"|0|

  # Scenario C: Checks if a given JSON string contains a specific boolean property.
      Scenario: A Base entity has a single @Property-annotated attribute that should contain a Boolean property called 'booleanProperty''.
        Given a Base instance with a Boolean @Property-annotated value attribute equals to <inputValue>
        When it is validated
        Then <expectedViolationNumber> violations should be raised

        Examples:
          |inputValue|expectedViolationNumber|
          |""|1|
          |"\"key\": \"value\""|1|
          |"{\"key\": \"value\"}"|1|
          |"{\"booleanProperty\": [ \"value\" ]}"|1|
          |"{\"booleanProperty\": [ \"valueA\", \"valueB\" ]}"|1|
          |"{\"booleanProperty\": { \"subKey\": \"subValue\"}}"|1|
          |"{\"booleanProperty\": \"\"}"|1|
          |"{\"booleanProperty\": \"value\"}"|1|
          |"{\"booleanProperty\": 1.0 }"|1|
          |"{\"booleanProperty\": 0.05 }"|1|
          |"{\"booleanProperty\": 1e4 }"|1|
          |"{\"booleanProperty\": false }"|0|
          |"{\"booleanProperty\": true }"|0|

  # Scenario D: Checks if a given JSON string has a specific property that contains an array.
      Scenario: A Base entity has a single @Property-annotated attribute that should contain an Array property called 'arrayProperty''.
        Given a Base instance with an Array @Property-annotated value attribute equals to <inputValue>
        When it is validated
        Then <expectedViolationNumber> violations should be raised

        Examples:
          |inputValue|expectedViolationNumber|
          |""|1|
          |"\"key\": \"value\""|1|
          |"{\"key\": \"value\"}"|1|
          |"{\"arrayProperty\": { \"subKey\": \"subValue\"}}"|1|
          |"{\"arrayProperty\": \"\"}"|1|
          |"{\"arrayProperty\": \"value\"}"|1|
          |"{\"arrayProperty\": 1.0 }"|1|
          |"{\"arrayProperty\": 0.05 }"|1|
          |"{\"arrayProperty\": 1e4 }"|1|
          |"{\"arrayProperty\": false }"|1|
          |"{\"arrayProperty\": true }"|1|
          |"{\"arrayProperty\": []}"|0|
          |"{\"arrayProperty\": [ \"value\" ]}"|0|
          |"{\"arrayProperty\": [ \"valueA\", \"valueB\" ]}"|0|
          |"{\"arrayProperty\": [ \"valueA\", \"valueB\", 1 ]}"|0|
          |"{\"arrayProperty\": [ \"valueA\", \"valueB\", 1, false ]}"|0|
          |"{\"arrayProperty\": [ \"valueA\", \"valueB\", 1, false, { \"subKey\": \"subValue\"} ]}"|0|

  # Scenario E: Checks if a given JSON string has a specific property that contains an object.
      Scenario: A Base entity has a single @Property-annotated attribute that should contain an object property called 'objectProperty''.
        Given a Base instance with an object @Property-annotated value attribute equals to <inputValue>
        When it is validated
        Then <expectedViolationNumber> violations should be raised

        Examples:
          |inputValue|expectedViolationNumber|
          |""|1|
          |"\"key\": \"value\""|1|
          |"{\"key\": \"value\"}"|1|
          |"{\"objectProperty\": \"\"}"|1|
          |"{\"objectProperty\": \"value\"}"|1|
          |"{\"objectProperty\": 1.0 }"|1|
          |"{\"objectProperty\": 0.05 }"|1|
          |"{\"objectProperty\": 1e4 }"|1|
          |"{\"objectProperty\": false }"|1|
          |"{\"objectProperty\": true }"|1|
          |"{\"objectProperty\": []}"|1|
          |"{\"objectProperty\": [ \"value\" ]}"|1|
          |"{\"objectProperty\": [ \"valueA\", \"valueB\" ]}"|1|
          |"{\"objectProperty\": [ \"valueA\", \"valueB\", 1 ]}"|1|
          |"{\"objectProperty\": [ \"valueA\", \"valueB\", 1, false ]}"|1|
          |"{\"objectProperty\": [ \"valueA\", \"valueB\", 1, false, { \"subKey\": \"subValue\"} ]}"|1|
          |"{\"objectProperty\": { \"subKey\": \"subValue\"}}"|0|

  # Scenario F: Checks if a given JSON string complies with a Custom validator.
      Scenario: A Base entity has a single @Property-annotated customProperty attribute that has its own validator.
        Given a Base instance with a @Property-annotated value attribute equals to <inputValue> has its own validator
        When it is validated
        Then <expectedViolationNumber> violations should be raised

        Examples:
          |inputValue|expectedViolationNumber|
          |"{\"customProperty\": \"UNEXPECTED_VALUE\"}"|1|
          |"{\"customProperty\": \"EXPECTED_VALUE\"}"|0|