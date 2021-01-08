package dev.isaacribeiro.inlinejsonvalidator.integration.domain;

import com.fasterxml.jackson.databind.node.JsonNodeType;
import dev.isaacribeiro.inlinejsonvalidator.annotation.Property;

public class SingleObjectField extends Base {

  @Property(name = "objectProperty", type = JsonNodeType.OBJECT, message = "JSON must contain 'objectProperty' of type Object.")
  private String value;

  @Override
  public void setValue(String value) {
    this.value = value;
  }

}
