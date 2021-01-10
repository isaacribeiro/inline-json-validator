package dev.isaacribeiro.inlinejsonvalidator.integration.customvalidators;

import com.fasterxml.jackson.databind.JsonNode;
import dev.isaacribeiro.inlinejsonvalidator.custom.CustomPropertyValidator;

public class CustomValidatorWithError implements CustomPropertyValidator {

  @Override
  public boolean isValid(JsonNode value) throws Exception {
    throw new Exception();
  }
}
