package dev.isaacribeiro.inlinejsonvalidator.integration.customvalidators;

import com.fasterxml.jackson.databind.JsonNode;
import dev.isaacribeiro.inlinejsonvalidator.custom.CustomPropertyValidator;

public class CustomValidatorA implements CustomPropertyValidator {

  @Override
  public boolean isValid(JsonNode value) {
    return value.asText().equals("EXPECTED_VALUE_A");
  }
}
