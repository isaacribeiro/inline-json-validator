package dev.isaacribeiro.inlinejsonvalidator.integration.domain;

import com.fasterxml.jackson.databind.node.JsonNodeType;
import dev.isaacribeiro.inlinejsonvalidator.annotation.Property;

public class SingleArrayField extends Base {

  @Property(name = "arrayProperty", type = JsonNodeType.ARRAY, message = "JSON must contain 'arrayProperty' of type Array.")
  private String value;

  @Override
  public void setValue(String value) {
    this.value = value;
  }

}
