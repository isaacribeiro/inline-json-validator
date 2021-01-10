package dev.isaacribeiro.inlinejsonvalidator.annotation;

import com.fasterxml.jackson.databind.node.JsonNodeType;
import dev.isaacribeiro.inlinejsonvalidator.custom.CustomPropertyValidator;
import dev.isaacribeiro.inlinejsonvalidator.validator.PropertyValidator;
import java.lang.annotation.ElementType;
import java.lang.annotation.Repeatable;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import javax.validation.Constraint;
import javax.validation.Payload;

@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = PropertyValidator.class)
@Repeatable(Properties.class)
public @interface Property {

  /**
   * Property name.
   *
   * @return
   */
  String name() default "";

  /**
   * Property type.
   *
   * @return
   */
  JsonNodeType type() default JsonNodeType.STRING;

  /**
   * Message.
   */
  String message() default "";

  /**
   * TDB.
   *
   * @return
   */
  Class<? extends CustomPropertyValidator>[] customValidator() default {};

  /**
   * JSR-303 based parameter.
   */
  Class<? extends Payload>[] payload() default {};

  /**
   * groups() parameter.
   */
  Class<?>[] groups() default {};
}
