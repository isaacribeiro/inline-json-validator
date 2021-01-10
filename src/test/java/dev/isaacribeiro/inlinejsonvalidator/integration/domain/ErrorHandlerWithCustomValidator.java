package dev.isaacribeiro.inlinejsonvalidator.integration.domain;

import dev.isaacribeiro.inlinejsonvalidator.annotation.Property;
import dev.isaacribeiro.inlinejsonvalidator.integration.customvalidators.CustomValidatorWithError;

public class ErrorHandlerWithCustomValidator extends Base {

  @Property(name = "property", customValidator = {
      CustomValidatorWithError.class}, message = "Error must be thrown.")
  private String value;

  @Override
  public void setValue(String value) {
    this.value = value;
  }

}
