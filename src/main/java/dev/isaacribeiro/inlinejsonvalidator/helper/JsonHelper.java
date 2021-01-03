package dev.isaacribeiro.inlinejsonvalidator.helper;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
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

  private static void parseJson(String value) throws IOException {
    JsonParser parser = new ObjectMapper().getFactory().createParser(value);
    while (parser.nextToken() != null) {
    }
  }

  private static boolean elementHasBeenFound(JsonNode node) {
    return !node.isMissingNode();
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
