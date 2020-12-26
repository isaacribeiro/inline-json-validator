package dev.isaacribeiro.inlinejsonvalidator.validator;

import dev.isaacribeiro.inlinejsonvalidator.annotation.Json;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class JsonValidator implements ConstraintValidator<Json, String> {

  private Set<String> mandatoryFields;

  public void initialize(Json constraintAnnotation) {
    this.mandatoryFields = new HashSet<>(constraintAnnotation.mandatoryFields().length);
    this.mandatoryFields.addAll(Arrays.asList(constraintAnnotation.mandatoryFields()));
  }

  public boolean isValid(String value, ConstraintValidatorContext constraintValidatorContext) {
    if (!JsonHelper.isJson(value)) {
      return false;
    }

    return JsonHelper.containsAll(value, this.mandatoryFields);
  }
}
