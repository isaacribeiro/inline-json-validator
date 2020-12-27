Feature: Validate a JSON string
  Any attribute annotated with @JSON must be a valid JSON object.

  Background:
    Given a BaseInstance class with a single property t

  Scenario: Empty string is not a valid JSON object.
    Given a Base Entity instance that contains a single attribute
    Given an empty string
    When I create a new Base Entity instance
    Then a violation should be raised.