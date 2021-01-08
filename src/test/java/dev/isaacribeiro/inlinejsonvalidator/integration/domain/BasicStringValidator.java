package dev.isaacribeiro.inlinejsonvalidator.integration.domain;

import com.fasterxml.jackson.databind.JsonNode;
import dev.isaacribeiro.inlinejsonvalidator.custom.CustomPropertyValidator;

public class BasicStringValidator implements CustomPropertyValidator {

  @Override
  public boolean isValid(JsonNode value) {
    return value.asText().equals("EXPECTED_VALUE");
  }
}
