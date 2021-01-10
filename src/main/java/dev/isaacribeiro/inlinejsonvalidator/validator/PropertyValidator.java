package dev.isaacribeiro.inlinejsonvalidator.validator;

import com.fasterxml.jackson.databind.node.JsonNodeType;
import dev.isaacribeiro.inlinejsonvalidator.annotation.Property;
import dev.isaacribeiro.inlinejsonvalidator.custom.CustomPropertyValidator;
import dev.isaacribeiro.inlinejsonvalidator.helper.JsonHelper;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class PropertyValidator implements ConstraintValidator<Property, String> {

  private String name;
  private JsonNodeType type;
  private Class<? extends CustomPropertyValidator>[] customValidator;

  @Override
  public void initialize(Property constraintAnnotation) {
    this.name = constraintAnnotation.name();
    this.type = constraintAnnotation.type();
    this.customValidator = constraintAnnotation.customValidator();
  }

  @Override
  public boolean isValid(String json, ConstraintValidatorContext context) {
    return JsonHelper.matches(json, name, type, customValidator);
  }
}
