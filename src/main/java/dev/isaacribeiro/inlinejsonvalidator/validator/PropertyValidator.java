package dev.isaacribeiro.inlinejsonvalidator.validator;

import com.fasterxml.jackson.databind.node.JsonNodeType;
import dev.isaacribeiro.inlinejsonvalidator.annotation.Property;
import dev.isaacribeiro.inlinejsonvalidator.helper.JsonHelper;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class PropertyValidator implements ConstraintValidator<Property, String> {

  private String name;
  private JsonNodeType type;

  @Override
  public void initialize(Property constraintAnnotation) {
    this.name = constraintAnnotation.name();
    this.type = constraintAnnotation.type();
  }

  @Override
  public boolean isValid(String json, ConstraintValidatorContext context) {
    return JsonHelper.hasProperty(json, name, type);
  }
}
