package dev.isaacribeiro.inlinejsonvalidator.validator;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.ObjectMapper;
import dev.isaacribeiro.inlinejsonvalidator.annotation.Json;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import org.apache.commons.lang3.StringUtils;

public class JsonValidator implements ConstraintValidator<Json, String> {

  private Set<String> mandatoryFields;

  public void initialize(Json constraintAnnotation) {
    this.mandatoryFields = new HashSet<>(constraintAnnotation.mandatoryFields().length);
    this.mandatoryFields.addAll(Arrays.asList(constraintAnnotation.mandatoryFields()));
  }

  public boolean isValid(String value, ConstraintValidatorContext constraintValidatorContext) {
    return isJsonValid(value);
  }

  private boolean isJsonValid(String value) {
    if (isEmpty(value)) {
      return false;
    }

    if (!isJson(value)) {
      return false;
    }

    if (!this.mandatoryFields.isEmpty()) {
    }

    return true;
  }

  private boolean isEmpty(String value) {
    return StringUtils.isEmpty(value);
  }

  private boolean isJson(String value) {
    try {
      JsonParser parser = new ObjectMapper().getFactory().createParser(value);
      while (parser.nextToken() != null) {
      }
      return true;
    } catch (IOException e) {
      return false;
    }
  }
}
