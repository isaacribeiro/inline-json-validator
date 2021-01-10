package dev.isaacribeiro.inlinejsonvalidator.validator;

import dev.isaacribeiro.inlinejsonvalidator.annotation.Json;
import dev.isaacribeiro.inlinejsonvalidator.helper.JsonHelper;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

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
   * @param inputValue                 @Json annotated String attribute.
   * @param constraintValidatorContext Validation context.
   */
  public boolean isValid(String inputValue, ConstraintValidatorContext constraintValidatorContext) {
    return JsonHelper.isJson(inputValue) && JsonHelper.containsAll(inputValue, mandatoryFields);
  }
}
