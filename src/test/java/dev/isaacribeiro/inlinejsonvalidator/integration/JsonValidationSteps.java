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

  private BaseEntity baseEntity;
  private static Validator validator;
  private Set<ConstraintViolation<BaseEntity>> actualViolations;

  @Before
  public static void classSetup() {
    validator = Validation.buildDefaultValidatorFactory().getValidator();
  }

  @Given("a BaseEntity instance whose value is {string}")
  public void a_base_entity_instance_whose_value_is(String inputValue) {
    baseEntity = new BaseEntity();
    baseEntity.setValue(inputValue);
  }

  @When("it is validated")
  public void it_is_validated() {
    actualViolations = validator.validate(baseEntity);
  }

  @Then("{int} violations should be raised.")
  public void violations_should_be_raised(Integer expectedViolationNumber) {
    Set<String> expectedViolationMessagesD = new HashSet();
    expectedViolationMessagesD.add("Input string must be a JSON object.");

    assertEquals(expectedViolationNumber, actualViolations.size());

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
