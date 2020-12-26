package dev.isaacribeiro.inlinejsonvalidator.validator;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import org.junit.jupiter.api.Test;

class JsonHelperTest {

  @Test
  public void shouldReturnTrueWhenJsonIsValid() {
    assertTrue(JsonHelper.isJson("{}"));
    assertTrue(JsonHelper.isJson("{\"key\": \"value\"}"));
    assertTrue(JsonHelper.isJson("{\"keyA\": \"value\", \"keyB\": {\"subkeyB\": \"subValueB\"}}"));
    assertTrue(
        JsonHelper.isJson("{\"keyA\": \"value\", \"keyB\": [\"arrayValueA\", \"arrayValueB\"]}"));
  }

  @Test
  public void shoudReturnFalseWhenJsonIsNotValid() {
    assertFalse(JsonHelper.isJson(null));
    assertFalse(JsonHelper.isJson(""));
    assertFalse(JsonHelper.isJson("\"key\": \"value\""));
    assertFalse(JsonHelper.isJson("{\"key\": value}"));
    assertFalse(JsonHelper.isJson("abc{\"key\": \"value\"}"));
    assertFalse(JsonHelper.isJson("{\"key\": \"value\"}abc"));
    assertFalse(JsonHelper.isJson("{\"keyA\": \"value\", \"keyB\": {\"subkeyB\": \"subValueB\"}"));
    assertFalse(
        JsonHelper.isJson("{\"keyA\": \"value\", \"keyB\": [\"arrayValueA\", \"arrayValueB\"}"));
  }

  @Test
  public void shouldReturnTrueWhenJsonContainsAllRequiredProperty() {
    assertTrue(JsonHelper.containsAll("{\"key\": \"value\"}", new HashSet<>(Arrays.asList("key"))));
    assertTrue(JsonHelper.containsAll("{\"key\": \"value\"}", Arrays.asList("key")));
    assertTrue(JsonHelper
        .containsAll("{\"keyA\": \"value\", \"keyB\": {\"subkeyB\": \"subValueB\"}}",
            Arrays.asList("keyA")));
    assertTrue(JsonHelper
        .containsAll("{\"keyA\": \"value\", \"keyB\": {\"subkeyB\": \"subValueB\"}}",
            Arrays.asList("keyB")));
    assertTrue(JsonHelper
        .containsAll("{\"keyA\": \"value\", \"keyB\": {\"subkeyB\": \"subValueB\"}}",
            Arrays.asList("keyA", "keyB")));
    assertTrue(JsonHelper
        .containsAll("{\"keyA\": \"value\", \"keyB\": {\"subkeyB\": \"subValueB\"}}",
            Arrays.asList("keyA", "keyB", "keyB.subkeyB")));
  }

  @Test
  public void shouldReturnFalseWhenJsonIsInvalidDoesNotContainsAnyRequiredProperty() {
    assertFalse(JsonHelper.containsAll(null, Collections.emptySet()));
    assertFalse(JsonHelper.containsAll("", Collections.emptyList()));
    assertFalse(JsonHelper.containsAll("\"key\": \"value\"", Collections.emptyList()));
    assertFalse(
        JsonHelper.containsAll("{\"key\": \"value\"}", new HashSet<>(Arrays.asList("wrongKey"))));
    assertFalse(JsonHelper.containsAll("{\"key\": \"value\"}", Arrays.asList("wrongKey")));
    assertFalse(JsonHelper
        .containsAll("{\"keyA\": \"value\", \"keyB\": {\"subkeyB\": \"subValueB\"}}",
            Arrays.asList("keyA", "wrongKey")));
    assertFalse(JsonHelper
        .containsAll("{\"keyA\": \"value\", \"keyB\": {\"subkeyB\": \"subValueB\"}}",
            Arrays.asList("keyB", "wrongKey")));
    assertFalse(JsonHelper
        .containsAll("{\"keyA\": \"value\", \"keyB\": {\"subkeyB\": \"subValueB\"}}",
            Arrays.asList("keyA", "keyB", "wrongKey")));
    assertFalse(JsonHelper
        .containsAll("{\"keyA\": \"value\", \"keyB\": {\"subkeyB\": \"subValueB\"}}",
            Arrays.asList("keyA", "keyB", "keyB.subkeyB", "wrongKey")));
  }

}