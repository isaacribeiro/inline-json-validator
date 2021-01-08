package dev.isaacribeiro.inlinejsonvalidator.helper;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.JsonNodeType;
import dev.isaacribeiro.inlinejsonvalidator.custom.CustomPropertyValidator;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Collection;
import java.util.Iterator;
import org.apache.commons.lang3.StringUtils;

/**
 * Helper class to handle with basic JSON-inline operations.
 */
public class JsonHelper {

  /**
   * Checks if a given JSON object contains all expected properties.
   *
   * @param json       source object to be validated.
   * @param properties Collection of Strings with all parameters to be checked.
   */
  public static boolean isValid(String json, Collection<String> properties) {
    if (isEmpty(json)) {
      return false;
    }

    try {
      parseJson(json);

      if (properties.isEmpty()) {
        return true;
      }

      return hasAllProperties(json, properties);
    } catch (Exception e) {
      return false;
    }
  }

  /**
   * Checks if a given inline-JSON String contains a specific parameter.
   *
   * @param json     input JSON object.
   * @param property property to be validate.
   * @param type     expected property type.
   * @return
   */
  public static boolean hasProperty(String json, String property, JsonNodeType type) {
    try {
      return doesPropertyMatchType(json, property, type);
    } catch (JsonProcessingException e) {
      return false;
    }
  }

  /**
   * Custom property.
   *
   * @param json       input source string.
   * @param validators custom validators array.
   * @return
   */
  public static boolean matches(String json, String property, JsonNodeType type,
      Class<? extends CustomPropertyValidator>[] validators) {
    if (!hasProperty(json, property, type)) {
      return false;
    }

    try {
      JsonNode nodeValue = getJsonNodeAt(json, property);
      CustomPropertyValidator[] instances = new CustomPropertyValidator[validators.length];
      for (int i = 0; i < validators.length; i++) {
        instances[i] = validators[i].getDeclaredConstructor().newInstance();
      }
      return (boolean) validators[0].getDeclaredMethod("isValid", JsonNode.class)
          .invoke(instances[0], nodeValue);
    } catch (NoSuchMethodException
        | InstantiationException
        | IllegalAccessException
        | InvocationTargetException
        | JsonProcessingException e) {
      return false;
    }
  }

  private static boolean doesPropertyMatchType(String json, String property, JsonNodeType type)
      throws JsonProcessingException {
    return hasPropertyOf(json, property, type);
  }

  private static boolean hasAllProperties(String json, Collection<String> properties)
      throws JsonProcessingException {
    JsonNode jsonNode = new ObjectMapper().readTree(json);

    Iterator<String> iterator = properties.iterator();
    while (iterator.hasNext()) {
      JsonNode node = getJsonNode(jsonNode, iterator.next());
      if (!elementHasBeenFound(node)) {
        return false;
      }
    }
    return true;
  }

  private static boolean hasPropertyOf(String inputJson, String property, JsonNodeType type)
      throws JsonProcessingException {
    JsonNode node = getJsonNodeAt(inputJson, property);
    return isPropertyOfType(node, type);
  }

  private static JsonNode getJsonNodeAt(String inputJson, String property)
      throws JsonProcessingException {
    JsonNode json = new ObjectMapper().readTree(inputJson);
    return getJsonNode(json, property);
  }

  private static void parseJson(String value) throws IOException {
    JsonParser parser = new ObjectMapper().getFactory().createParser(value);
    while (parser.nextToken() != null) {
    }
  }

  private static boolean elementHasBeenFound(JsonNode node) {
    return !node.isMissingNode();
  }

  private static boolean isPropertyOfType(JsonNode node, JsonNodeType type) {
    return elementHasBeenFound(node) && node.getNodeType().equals(type);
  }

  private static boolean isEmpty(String value) {
    return StringUtils.isEmpty(value);
  }

  private static JsonNode getJsonNode(JsonNode jsonNode, String mandatoryField) {
    return jsonNode.at(parseJsonPath(mandatoryField));
  }

  private static String parseJsonPath(String mandatoryField) {
    return String.format("/%s", mandatoryField.replace('.', '/'));
  }
}
