package dev.isaacribeiro.inlinejsonvalidator.integration.domain;

import dev.isaacribeiro.inlinejsonvalidator.annotation.Property;

public class SingleFieldWithCustomValidator extends Base {

  @Property(name = "customProperty", customValidator = {
      BasicStringValidator.class}, message = "JSON must contain 'stringProperty' of type String.")
  private String value;

  @Override
  public void setValue(String value) {
    this.value = value;
  }

}
