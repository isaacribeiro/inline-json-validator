package dev.isaacribeiro.inlinejsonvalidator.validator;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import dev.isaacribeiro.inlinejsonvalidator.helper.AnnotationHelper;
import org.junit.jupiter.api.Test;

class JsonValidatorTest {

  private JsonValidator cut = new JsonValidator();

  @Test
  public void shouldReturnFalseWhenInputIsEmptyOrNull() {
    cut.initialize(AnnotationHelper.createJsonAnnotation(new String[]{}));
    assertFalse(cut.isValid("", null));
    assertFalse(cut.isValid(null, null));
  }

  @Test
  public void shouldReturnFalseWhenInputIsNotAValidJsonObject() {
    cut.initialize(AnnotationHelper.createJsonAnnotation(new String[]{}));
    assertFalse(cut.isValid("token", null));
    assertFalse(cut.isValid("token{}", null));
    assertFalse(cut.isValid("{}token", null));
    assertFalse(cut.isValid("{\"key\": token}", null));
    assertFalse(cut.isValid("{\"key\": \"token\",}", null));
    assertFalse(cut.isValid("{\"key\": \"token\",\"secondKey\": {\"tokenB\"}}", null));
    assertFalse(cut.isValid("{\"key\": \"token\",\"secondKey\": [\"tokenB\",]}", null));
  }

  @Test
  public void shouldReturnTrueWhenInputIsAValidJsonAndThereAreNoMandatoryParameters() {
    cut.initialize(AnnotationHelper.createJsonAnnotation(new String[]{}));
    assertTrue(cut.isValid("{\"key\": \"value\"}", null));
    assertTrue(cut.isValid("{\"keyA\": \"valueA\",\"keyB\": {\"subkeyB\": \"subvalueB\"}}", null));
    assertTrue(cut.isValid("{\"keyA\": \"valueA\",\"keyB\": [\"arrayA\"]}", null));
  }

  @Test
  public void shouldReturnFalseWhenInputIsAValidJsonButMandatoryParametersAreNotPresent() {
    cut.initialize(AnnotationHelper.createJsonAnnotation(new String[]{"inexistentKey"}));
    assertFalse(cut.isValid("{\"key\": \"value\"}", null));
  }

  @Test
  public void shouldReturnFalseWhenInputIsAValidJsonAndMandatoryParametersArePresent() {
    cut.initialize(AnnotationHelper.createJsonAnnotation(new String[]{"key"}));
    assertTrue(cut.isValid("{\"key\": {\"subkey\": \"value\"}}", null));

    cut.initialize(AnnotationHelper.createJsonAnnotation(new String[]{"key", "key.subkey"}));
    assertTrue(cut.isValid("{\"key\": {\"subkey\": \"value\"}}", null));
  }

  @Test
  public void shouldReturnTrueWhenInputIsAValidJsonAndMandatoryParametersArePresent() {
    cut.initialize(AnnotationHelper.createJsonAnnotation(new String[]{"key"}));
    assertTrue(cut.isValid("{\"key\": {\"subkey\": \"value\"}}", null));

    cut.initialize(AnnotationHelper.createJsonAnnotation(new String[]{"key", "key.subkey"}));
    assertTrue(cut.isValid("{\"key\": {\"subkey\": \"value\"}}", null));
  }

}