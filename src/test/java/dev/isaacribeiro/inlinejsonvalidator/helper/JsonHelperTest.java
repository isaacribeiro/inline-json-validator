package dev.isaacribeiro.inlinejsonvalidator.helper;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.fasterxml.jackson.databind.node.JsonNodeType;
import org.junit.jupiter.api.Test;

class JsonHelperTest {

  @Test
  public void shouldReturnTrueWhenJsonContainsAllRequiredProperty() {
    assertTrue(JsonHelper.isJson("{}"));
    assertTrue(JsonHelper.isJson("{\"key\": \"value\"}"));
    assertTrue(JsonHelper.isJson("{\"key\": \"value\"}"));
    assertTrue(JsonHelper.isJson("{\"key\": \"value\"}"));
    assertTrue(JsonHelper.isJson("{\"keyA\": \"value\", \"keyB\": {\"subkeyB\": \"subValueB\"}}"));
  }

  @Test
  public void shouldReturnFalseWhenJsonIsInvalidOrDoesNotContainsAnyRequiredProperty() {
    assertFalse(JsonHelper.isJson(null));
    assertFalse(JsonHelper.isJson(""));
    assertFalse(JsonHelper.isJson("\"key\": \"value\""));
    assertFalse(JsonHelper.isJson("{\"key\": {\"value\"}}"));
    assertFalse(JsonHelper.isJson("{\"key\": \"value\" \"subvalue\"}"));
    assertFalse(JsonHelper.isJson("{\"keyA\": \"value\", \"keyB\": [\"subkeyB\": \"subValueB\"]}"));
  }

  @Test
  public void shouldReturnTrueWhenJsonContainsExpectedPropertyAndMatchesTheExpectedJsonType() {
    assertTrue(JsonHelper.matches("{\"key\": \"value\"}", "key", JsonNodeType.STRING, null));
    assertTrue(JsonHelper
        .matches("{\"key\": { \"subkey\": \"value\"}}", "key.subkey", JsonNodeType.STRING, null));
    assertTrue(
        JsonHelper
            .matches("{\"key\": { \"subkey\": 1.0}}", "key.subkey", JsonNodeType.NUMBER, null));
    assertTrue(JsonHelper
        .matches("{\"key\": { \"subkey\": { \"deepkey\": 1.0}}}", "key.subkey.deepkey",
            JsonNodeType.NUMBER, null));
    assertTrue(JsonHelper.matches("{\"key\": { \"subkey\": { \"deepkey\": 1.0}}}", "key.subkey",
        JsonNodeType.OBJECT, null));
    assertTrue(JsonHelper
        .matches("{\"key\": { \"subkey\": { \"deepkey\": 1.0}}}", "key", JsonNodeType.OBJECT,
            null));
    assertTrue(JsonHelper
        .matches("{\"key\": { \"subkey\": { \"deepkey\": [ 1.0 ]}}}", "key.subkey.deepkey",
            JsonNodeType.ARRAY, null));
    assertTrue(JsonHelper
        .matches("{\"key\": { \"subkey\": { \"deepkey\": [ 1.0, 1.1 ]}}}", "key.subkey.deepkey",
            JsonNodeType.ARRAY, null));
    assertTrue(JsonHelper
        .matches("{\"key\": { \"subkey\": { \"deepkey\": [ 1.0, 1,1, \"thirdValue\" ]}}}",
            "key.subkey.deepkey", JsonNodeType.ARRAY, null));
    assertTrue(JsonHelper
        .matches("{\"key\": { \"subkey\": { \"deepkey\": false }}}", "key.subkey.deepkey",
            JsonNodeType.BOOLEAN, null));
    assertTrue(JsonHelper
        .matches("{\"key\": { \"subkey\": { \"deepkey\": true }}}", "key.subkey.deepkey",
            JsonNodeType.BOOLEAN, null));
    assertTrue(JsonHelper
        .matches("{\"key\": { \"subkey\": true }}", "key.subkey", JsonNodeType.BOOLEAN, null));
  }

  @Test
  public void shouldReturnFalseWhenJsonIsInvalidOrJsonDoesNotContainExpectedPropertyOrExpectedPropertyTypeDoesNotMatch() {
    assertFalse(JsonHelper.matches("", "key", JsonNodeType.STRING, null));
    assertFalse(JsonHelper.matches("{\"key\"}", "key", JsonNodeType.STRING, null));
    assertFalse(JsonHelper.matches("{\"key\": \"value\"}", "wrongKey", JsonNodeType.NUMBER, null));
    assertFalse(JsonHelper
        .matches("{\"key\": { \"subKey\": \"value\"}", "key.wrongSubKey", JsonNodeType.STRING,
            null));
    assertFalse(JsonHelper
        .matches("{\"key\": { \"subKey\": { \"deepKey\": \"value\"}", "key.subKey.wrongDeepKey",
            JsonNodeType.STRING, null));
    assertFalse(JsonHelper
        .matches("{\"key\": { \"subkey\": \"value\"}}", "key.subkey", JsonNodeType.OBJECT, null));
    assertFalse(
        JsonHelper
            .matches("{\"key\": { \"subkey\": 1.0}}", "key.subkey", JsonNodeType.STRING, null));
    assertFalse(JsonHelper
        .matches("{\"key\": { \"subkey\": { \"deepkey\": 1.0}}}", "key.subkey.deepkey",
            JsonNodeType.STRING, null));
    assertFalse(JsonHelper.matches("{\"key\": { \"subkey\": { \"deepkey\": 1.0}}}", "key.subkey",
        JsonNodeType.ARRAY, null));
    assertFalse(JsonHelper
        .matches("{\"key\": { \"subkey\": { \"deepkey\": 1.0}}}", "key", JsonNodeType.STRING,
            null));
    assertFalse(JsonHelper
        .matches("{\"key\": { \"subkey\": { \"deepkey\": [ 1.0 ]}}}", "key.subkey.deepkey",
            JsonNodeType.BOOLEAN, null));
    assertFalse(JsonHelper
        .matches("{\"key\": { \"subkey\": { \"deepkey\": [ 1.0, 1.1 ]}}}", "key.subkey.deepkey",
            JsonNodeType.NUMBER, null));
    assertFalse(JsonHelper
        .matches("{\"key\": { \"subkey\": { \"deepkey\": [ 1.0, 1,1, \"thirdValue\" ]}}}",
            "key.subkey.deepkey", JsonNodeType.STRING, null));
    assertFalse(JsonHelper
        .matches("{\"key\": { \"subkey\": { \"deepkey\": false }}}", "key.subkey.deepkey",
            JsonNodeType.OBJECT, null));
    assertFalse(JsonHelper
        .matches("{\"key\": { \"subkey\": { \"deepkey\": true }}}", "key.subkey.deepkey",
            JsonNodeType.STRING, null));
    assertFalse(JsonHelper
        .matches("{\"key\": { \"subkey\": true }}", "key.subkey", JsonNodeType.ARRAY, null));
  }
}