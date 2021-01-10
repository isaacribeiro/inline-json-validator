package dev.isaacribeiro.inlinejsonvalidator.integration.customvalidators;

import com.fasterxml.jackson.databind.JsonNode;
import dev.isaacribeiro.inlinejsonvalidator.custom.CustomPropertyValidator;

public class CustomValidatorA1 implements CustomPropertyValidator {

  @Override
  public boolean isValid(JsonNode value) {
    return value.asText().contains("EXPECTED_VALUE_A1");
  }
}
