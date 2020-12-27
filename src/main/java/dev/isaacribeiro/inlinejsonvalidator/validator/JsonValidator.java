package dev.isaacribeiro.inlinejsonvalidator.validator;

import dev.isaacribeiro.inlinejsonvalidator.annotation.Json;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * Inline JSON validator.
 */
public class JsonValidator implements ConstraintValidator<Json, String> {

  private Set<String> mandatoryFields;

  public void initialize(Json constraintAnnotation) {
    this.mandatoryFields = new HashSet<>(constraintAnnotation.mandatoryFields().length);
    this.mandatoryFields.addAll(Arrays.asList(constraintAnnotation.mandatoryFields()));
  }

  /**
   * Validates a @Json annotated property.
   *
   * @param value @Json annotated String attribute.
   * @param constraintValidatorContext Validation context.
   */
  public boolean isValid(String value, ConstraintValidatorContext constraintValidatorContext) {
    if (!JsonHelper.isJson(value)) {
      return false;
    }

    return JsonHelper.containsAll(value, this.mandatoryFields);
  }
}
