package dev.isaacribeiro.inlinejsonvalidator.helper;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import org.junit.jupiter.api.Test;

class JsonHelperTest {

  @Test
  public void shouldReturnTrueWhenJsonContainsAllRequiredProperty() {
    assertTrue(JsonHelper.isValid("{}", Collections.EMPTY_SET));
    assertTrue(JsonHelper.isValid("{\"key\": \"value\"}", Collections.EMPTY_LIST));
    assertTrue(JsonHelper.isValid("{\"key\": \"value\"}", new HashSet<>(Arrays.asList("key"))));
    assertTrue(JsonHelper.isValid("{\"key\": \"value\"}", Arrays.asList("key")));
    assertTrue(JsonHelper
        .isValid("{\"keyA\": \"value\", \"keyB\": {\"subkeyB\": \"subValueB\"}}",
            Arrays.asList("keyA")));
    assertTrue(JsonHelper
        .isValid("{\"keyA\": \"value\", \"keyB\": {\"subkeyB\": \"subValueB\"}}",
            Arrays.asList("keyB")));
    assertTrue(JsonHelper
        .isValid("{\"keyA\": \"value\", \"keyB\": {\"subkeyB\": \"subValueB\"}}",
            Arrays.asList("keyA", "keyB")));
    assertTrue(JsonHelper
        .isValid("{\"keyA\": \"value\", \"keyB\": {\"subkeyB\": \"subValueB\"}}",
            Arrays.asList("keyA", "keyB", "keyB.subkeyB")));
  }

  @Test
  public void shouldReturnFalseWhenJsonIsInvalidOrDoesNotContainsAnyRequiredProperty() {
    assertFalse(JsonHelper.isValid(null, Collections.EMPTY_SET));
    assertFalse(JsonHelper.isValid("", Collections.emptyList()));
    assertFalse(JsonHelper.isValid("\"key\": \"value\"", Collections.EMPTY_LIST));
//    assertFalse(JsonHelper.isValid("\"key\" \"value\"", Collections.EMPTY_SET));
    assertFalse(
        JsonHelper.isValid("{\"key\": \"value\"}", new HashSet<>(Arrays.asList("wrongKey"))));
    assertFalse(JsonHelper.isValid("{\"key\": \"value\"}", Arrays.asList("wrongKey")));
    assertFalse(JsonHelper
        .isValid("{\"keyA\": \"value\", \"keyB\": {\"subkeyB\": \"subValueB\"}}",
            Arrays.asList("keyA", "wrongKey")));
    assertFalse(JsonHelper
        .isValid("{\"keyA\": \"value\", \"keyB\": {\"subkeyB\": \"subValueB\"}}",
            Arrays.asList("keyB", "wrongKey")));
    assertFalse(JsonHelper
        .isValid("{\"keyA\": \"value\", \"keyB\": {\"subkeyB\": \"subValueB\"}}",
            Arrays.asList("keyA", "keyB", "wrongKey")));
    assertFalse(JsonHelper
        .isValid("{\"keyA\": \"value\", \"keyB\": {\"subkeyB\": \"subValueB\"}}",
            Arrays.asList("keyA", "keyB", "keyB.subkeyB", "wrongKey")));
  }

}