package dev.isaacribeiro.inlinejsonvalidator.validator;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import dev.isaacribeiro.inlinejsonvalidator.helper.AnnotationHelper;
import org.junit.jupiter.api.Test;

class JsonValidatorTest {

  private JsonValidator cut = new JsonValidator();

  @Test
  public void shouldReturnFalseWhenInputIsEmptyOrNull() {
    cut.initialize(AnnotationHelper.createAnnotation(new String[]{}));
    assertFalse(cut.isValid("", null));
    assertFalse(cut.isValid(null, null));
  }

  @Test
  public void shouldReturnFalseWhenInputIsNotAValidJsonObject() {
    cut.initialize(AnnotationHelper.createAnnotation(new String[]{}));
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
    cut.initialize(AnnotationHelper.createAnnotation(new String[]{}));
    assertTrue(cut.isValid("{\"key\": \"value\"}", null));
    assertTrue(cut.isValid("{\"keyA\": \"valueA\",\"keyB\": {\"subkeyB\": \"subvalueB\"}}", null));
    assertTrue(cut.isValid("{\"keyA\": \"valueA\",\"keyB\": [\"arrayA\"]}", null));
  }

  @Test
  public void shouldReturnFalseWhenInputIsAValidJsonButMandatoryParametersAreNotPresent() {
    cut.initialize(AnnotationHelper.createAnnotation(new String[]{"key"}));
    assertTrue(cut.isValid("{\"key\": \"value\"}", null));
  }

}