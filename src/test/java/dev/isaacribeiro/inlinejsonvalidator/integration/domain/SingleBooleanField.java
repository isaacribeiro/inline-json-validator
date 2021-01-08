package dev.isaacribeiro.inlinejsonvalidator.integration.domain;

import com.fasterxml.jackson.databind.node.JsonNodeType;
import dev.isaacribeiro.inlinejsonvalidator.annotation.Property;

public class SingleBooleanField extends Base {

  @Property(name = "booleanProperty", type = JsonNodeType.BOOLEAN, message = "JSON must contain 'booleanProperty' of type Boolean.")
  private String value;

  @Override
  public void setValue(String value) {
    this.value = value;
  }

}
