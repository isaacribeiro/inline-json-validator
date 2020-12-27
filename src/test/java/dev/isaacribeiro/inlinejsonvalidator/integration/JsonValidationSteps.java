package dev.isaacribeiro.inlinejsonvalidator.integration;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import dev.isaacribeiro.inlinejsonvalidator.integration.domain.BaseEntity;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import org.hibernate.validator.internal.engine.ConstraintViolationImpl;

public class JsonValidationSteps {

  private static Validator validator;
  private Set<ConstraintViolation<BaseEntity>> actualViolations;

  @Before
  public static void classSetup() {
    validator = Validation.buildDefaultValidatorFactory().getValidator();
  }

  @Given("an empty string")
  public void an_empty_string() {
  }

  @When("I create a new Base Entity instance")
  public void i_create_a_new_base_entity_instance() {
    BaseEntity actualEntity = new BaseEntity();
    actualViolations = validator.validate(actualEntity);
  }

  @Then("a violation should be raised.")
  public void a_violation_should_be_raised() {
    Set<String> expectedViolationMessagesD = new HashSet();
    expectedViolationMessagesD.add("Input string must be a JSON object.");

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
