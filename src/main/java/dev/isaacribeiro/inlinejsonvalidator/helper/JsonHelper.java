package dev.isaacribeiro.inlinejsonvalidator.helper;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.JsonNodeType;
import dev.isaacribeiro.inlinejsonvalidator.custom.CustomPropertyValidator;
import java.io.IOException;
import java.util.Collection;
import java.util.Iterator;
import org.apache.commons.lang3.StringUtils;

/**
 * Helper class to handle with basic JSON-inline operations.
 */
public class JsonHelper {

  /**
   * TBD.
   *
   * @param json Input String.
   * @return
   */
  public static boolean isJson(String json) {
    if (isEmpty(json)) {
      return false;
    }

    try {
      JsonParser parser = new ObjectMapper().getFactory().createParser(json);
      while (parser.nextToken() != null) {
      }
      return true;
    } catch (IOException e) {
      return false;
    }
  }

  /**
   * TDB.
   *
   * @param json       input JSON String
   * @param properties Collection of properties to be checked.
   * @return
   */
  public static boolean containsAll(String json, Collection<String> properties) {
    Iterator<String> iterator = properties.iterator();
    while (iterator.hasNext()) {
      JsonNode node = getNodeAt(json, iterator.next());
      if (node.isMissingNode()) {
        return false;
      }
    }
    return true;
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
    return (validators != null && validators.length > 0) ? callCustomValidator(json, property,
        validators) : true;
  }

  private static boolean callCustomValidator(String json, String property,
      Class<? extends CustomPropertyValidator>[] validators) {
    try {
      JsonNode nodeValue = getNodeAt(json, property);
      CustomPropertyValidator[] instances = new CustomPropertyValidator[validators.length];
      for (int i = 0; i < validators.length; i++) {
        instances[i] = validators[i].getDeclaredConstructor().newInstance();
      }
      return (boolean) validators[0].getDeclaredMethod("isValid", JsonNode.class)
          .invoke(instances[0], nodeValue);
    } catch (Exception e) {
      e.printStackTrace();
      return false;
    }
  }

  private static JsonNode getJsonTree(String json) {
    try {
      return new ObjectMapper().readTree(json);
    } catch (JsonProcessingException | NullPointerException e) {
      return null;
    }
  }

  private static JsonNode getNodeAt(String inputJson, String property) {
    JsonNode jsonTree = getJsonTree(inputJson);

    if (jsonTree != null) {
      return jsonTree.at(translatePath(property));
    }
    return null;
  }

  private static boolean hasProperty(String json, String property, JsonNodeType type) {
    JsonNode node = getNodeAt(json, property);
    if (node == null) {
      return false;
    }
    return !node.isMissingNode() && node.getNodeType().equals(type);
  }

  private static boolean isEmpty(String value) {
    return StringUtils.isEmpty(value);
  }

  private static String translatePath(String mandatoryField) {
    return String.format("/%s", mandatoryField.replace('.', '/'));
  }
}
