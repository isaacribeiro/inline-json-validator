package dev.isaacribeiro.inlinejsonvalidator.integration.domain;

import com.fasterxml.jackson.databind.node.JsonNodeType;
import dev.isaacribeiro.inlinejsonvalidator.annotation.Property;

public class SingleStringField extends Base {

  @Property(name = "stringProperty", type = JsonNodeType.STRING, message = "JSON must contain 'stringProperty' of type String.")
  private String value;

}
