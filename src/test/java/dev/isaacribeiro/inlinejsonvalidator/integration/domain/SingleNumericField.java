package dev.isaacribeiro.inlinejsonvalidator.integration.domain;

import com.fasterxml.jackson.databind.node.JsonNodeType;
import dev.isaacribeiro.inlinejsonvalidator.annotation.Property;

public class SingleNumericField extends Base {

  @Property(name = "numericProperty", type = JsonNodeType.NUMBER, message = "JSON must contain 'numericProperty' of type Numeric.")
  private String value;

  @Override
  public void setValue(String value) {
    this.value = value;
  }

}
