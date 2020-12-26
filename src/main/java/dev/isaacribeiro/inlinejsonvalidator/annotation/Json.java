package dev.isaacribeiro.inlinejsonvalidator.annotation;

import dev.isaacribeiro.inlinejsonvalidator.validator.JsonValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = JsonValidator.class)
public @interface Json {

  String message() default "";

  String[] mandatoryFields() default {};

  Class<? extends Payload>[] payload() default {};

  Class<?>[] groups() default {};
}
