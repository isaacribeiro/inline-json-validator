package dev.isaacribeiro.inlinejsonvalidator.validator;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.util.Collection;
import java.util.Iterator;
import org.apache.commons.lang3.StringUtils;

public class JsonHelper {

  public static boolean isJson(String value) {
    if (isEmpty(value)) {
      return false;
    }

    try {
      parseJson(value);
      return true;
    } catch (IOException e) {
      return false;
    }
  }

  public static boolean containsAll(String json, Collection<String> properties) {
    if (isEmpty(json)) {
      return false;
    }

    try {
      parseJson(json);

      if (!hasAllProperties(json, properties)) {
        return false;
      }
      return true;
    } catch (IOException e) {
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
    return !(node.isEmpty() && node.size() == 0 && node.asText().equals(""));
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
