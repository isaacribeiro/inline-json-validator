package dev.isaacribeiro.inlinejsonvalidator.annotation;

import dev.isaacribeiro.inlinejsonvalidator.validator.JsonValidator;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import javax.validation.Constraint;
import javax.validation.Payload;

@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = JsonValidator.class)
public @interface Json {

  /**
   * Error message to be displayed.
   */
  String message() default "";

  /**
   * Mandatory fields to be validated.
   */
  String[] mandatoryFields() default {};

  /**
   * JSR-303 based parameter.
   */
  Class<? extends Payload>[] payload() default {};

  /**
   * groups() parameter.
   */
  Class<?>[] groups() default {};
}
