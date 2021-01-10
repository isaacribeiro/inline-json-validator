package dev.isaacribeiro.inlinejsonvalidator.integration.domain;

import dev.isaacribeiro.inlinejsonvalidator.annotation.Property;
import dev.isaacribeiro.inlinejsonvalidator.integration.customvalidators.CustomValidatorA;
import dev.isaacribeiro.inlinejsonvalidator.integration.customvalidators.CustomValidatorB;

public class SingleFieldWithMultipleValidators extends Base {

  @Property(name = "propertyA", customValidator = {CustomValidatorA.class}, message = "Error A")
  @Property(name = "propertyB", customValidator = {CustomValidatorB.class}, message = "Error B")
  private String value;

  @Override
  public void setValue(String value) {
    this.value = value;
  }

}
