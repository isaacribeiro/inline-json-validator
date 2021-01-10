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
    assertTrue(JsonHelper.hasProperty("{\"key\": \"value\"}", "key", JsonNodeType.STRING));
    assertTrue(JsonHelper
        .hasProperty("{\"key\": { \"subkey\": \"value\"}}", "key.subkey", JsonNodeType.STRING));
    assertTrue(
        JsonHelper.hasProperty("{\"key\": { \"subkey\": 1.0}}", "key.subkey", JsonNodeType.NUMBER));
    assertTrue(JsonHelper
        .hasProperty("{\"key\": { \"subkey\": { \"deepkey\": 1.0}}}", "key.subkey.deepkey",
            JsonNodeType.NUMBER));
    assertTrue(JsonHelper.hasProperty("{\"key\": { \"subkey\": { \"deepkey\": 1.0}}}", "key.subkey",
        JsonNodeType.OBJECT));
    assertTrue(JsonHelper
        .hasProperty("{\"key\": { \"subkey\": { \"deepkey\": 1.0}}}", "key", JsonNodeType.OBJECT));
    assertTrue(JsonHelper
        .hasProperty("{\"key\": { \"subkey\": { \"deepkey\": [ 1.0 ]}}}", "key.subkey.deepkey",
            JsonNodeType.ARRAY));
    assertTrue(JsonHelper
        .hasProperty("{\"key\": { \"subkey\": { \"deepkey\": [ 1.0, 1.1 ]}}}", "key.subkey.deepkey",
            JsonNodeType.ARRAY));
    assertTrue(JsonHelper
        .hasProperty("{\"key\": { \"subkey\": { \"deepkey\": [ 1.0, 1,1, \"thirdValue\" ]}}}",
            "key.subkey.deepkey", JsonNodeType.ARRAY));
    assertTrue(JsonHelper
        .hasProperty("{\"key\": { \"subkey\": { \"deepkey\": false }}}", "key.subkey.deepkey",
            JsonNodeType.BOOLEAN));
    assertTrue(JsonHelper
        .hasProperty("{\"key\": { \"subkey\": { \"deepkey\": true }}}", "key.subkey.deepkey",
            JsonNodeType.BOOLEAN));
    assertTrue(JsonHelper
        .hasProperty("{\"key\": { \"subkey\": true }}", "key.subkey", JsonNodeType.BOOLEAN));
  }

  @Test
  public void shouldReturnFalseWhenJsonIsInvalidOrJsonDoesNotContainExpectedPropertyOrExpectedPropertyTypeDoesNotMatch() {
    assertFalse(JsonHelper.hasProperty("", "key", JsonNodeType.STRING));
    assertFalse(JsonHelper.hasProperty("{\"key\"}", "key", JsonNodeType.STRING));
    assertFalse(JsonHelper.hasProperty("{\"key\": \"value\"}", "wrongKey", JsonNodeType.NUMBER));
    assertFalse(JsonHelper.hasProperty("{\"key\": { \"subKey\": \"value\"}", "key.wrongSubKey", JsonNodeType.STRING));
    assertFalse(JsonHelper.hasProperty("{\"key\": { \"subKey\": { \"deepKey\": \"value\"}", "key.subKey.wrongDeepKey", JsonNodeType.STRING));
    assertFalse(JsonHelper
        .hasProperty("{\"key\": { \"subkey\": \"value\"}}", "key.subkey", JsonNodeType.OBJECT));
    assertFalse(
        JsonHelper.hasProperty("{\"key\": { \"subkey\": 1.0}}", "key.subkey", JsonNodeType.STRING));
    assertFalse(JsonHelper
        .hasProperty("{\"key\": { \"subkey\": { \"deepkey\": 1.0}}}", "key.subkey.deepkey",
            JsonNodeType.STRING));
    assertFalse(JsonHelper.hasProperty("{\"key\": { \"subkey\": { \"deepkey\": 1.0}}}", "key.subkey",
        JsonNodeType.ARRAY));
    assertFalse(JsonHelper
        .hasProperty("{\"key\": { \"subkey\": { \"deepkey\": 1.0}}}", "key", JsonNodeType.STRING));
    assertFalse(JsonHelper
        .hasProperty("{\"key\": { \"subkey\": { \"deepkey\": [ 1.0 ]}}}", "key.subkey.deepkey",
            JsonNodeType.BOOLEAN));
    assertFalse(JsonHelper
        .hasProperty("{\"key\": { \"subkey\": { \"deepkey\": [ 1.0, 1.1 ]}}}", "key.subkey.deepkey",
            JsonNodeType.NUMBER));
    assertFalse(JsonHelper
        .hasProperty("{\"key\": { \"subkey\": { \"deepkey\": [ 1.0, 1,1, \"thirdValue\" ]}}}",
            "key.subkey.deepkey", JsonNodeType.STRING));
    assertFalse(JsonHelper
        .hasProperty("{\"key\": { \"subkey\": { \"deepkey\": false }}}", "key.subkey.deepkey",
            JsonNodeType.OBJECT));
    assertFalse(JsonHelper
        .hasProperty("{\"key\": { \"subkey\": { \"deepkey\": true }}}", "key.subkey.deepkey",
            JsonNodeType.STRING));
    assertFalse(JsonHelper
        .hasProperty("{\"key\": { \"subkey\": true }}", "key.subkey", JsonNodeType.ARRAY));
  }
}