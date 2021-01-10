package dev.isaacribeiro.inlinejsonvalidator.custom;

import com.fasterxml.jackson.databind.JsonNode;

public interface CustomPropertyValidator {

  boolean isValid(JsonNode value) throws Exception;

}
