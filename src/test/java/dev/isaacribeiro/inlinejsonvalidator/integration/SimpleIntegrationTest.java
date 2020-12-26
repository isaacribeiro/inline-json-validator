package dev.isaacribeiro.inlinejsonvalidator.integration;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import dev.isaacribeiro.inlinejsonvalidator.integration.domain.BaseEntity;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import org.hibernate.validator.internal.engine.ConstraintViolationImpl;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class SimpleIntegrationTest {

  private static Validator validator;

  @BeforeAll
  public static void classSetup() {
    validator = Validation.buildDefaultValidatorFactory().getValidator();
  }

  @Test
  public void shouldRaiseAViolationWhenPropertyIsAnnotatedButItsValueIsNotAJsonObject() {
    // Given & When
    Set<String> expectedViolationMessagesD = new HashSet();
    expectedViolationMessagesD.add("Input string must be a JSON object.");

    BaseEntity actualEntity = new BaseEntity();

    Set<ConstraintViolation<BaseEntity>> actualViolations = validator.validate(actualEntity);

    assertEquals(1, actualViolations.size());

    Iterator<ConstraintViolation<BaseEntity>> actualViolationsIterator = actualViolations
        .iterator();
    while (actualViolationsIterator.hasNext()) {
      ConstraintViolationImpl<BaseEntity> violation = (ConstraintViolationImpl<BaseEntity>) actualViolationsIterator
          .next();
      assertTrue(expectedViolationMessagesD.contains(violation.getMessage()));
      expectedViolationMessagesD.remove(violation.getMessage());
    }
  }

}
