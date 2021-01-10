package dev.isaacribeiro.inlinejsonvalidator.integration.domain;

import dev.isaacribeiro.inlinejsonvalidator.annotation.Property;
import dev.isaacribeiro.inlinejsonvalidator.integration.customvalidators.SingleCustomValidator;

public class SingleFieldWithCustomValidator extends Base {

  @Property(name = "customProperty", customValidator = {
      SingleCustomValidator.class}, message = "JSON must contain 'stringProperty' of type String.")
  private String value;

  @Override
  public void setValue(String value) {
    this.value = value;
  }

}
