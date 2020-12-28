package dev.isaacribeiro.inlinejsonvalidator.integration;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import dev.isaacribeiro.inlinejsonvalidator.integration.domain.SimpleBase;
import io.cucumber.datatable.DataTable;
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
  private SimpleBase baseEntity;
  private Set<ConstraintViolation<SimpleBase>> actualViolations;

  @Before
  public static void classSetup() {
    validator = Validation.buildDefaultValidatorFactory().getValidator();
  }

  @Given("a Base instance with a @Json-annotated value attribute equals to {string}")
  public void a_base_instance_with_a_json_annotated_value_attribute_equals_to(String inputValue) {
    baseEntity = new SimpleBase();
    baseEntity.setValue(inputValue);
  }

  @Given("the following parameters")
  public void the_following_parameters(DataTable dataTable) {
    System.out.println(dataTable.asList());
  }

  @When("it is validated")
  public void it_is_validated() {
    actualViolations = validator.validate(baseEntity);
  }

  @Then("{int} violations should be raised")
  public void violations_should_be_raised(Integer expectedViolationNumber) {
    Set<String> expectedViolationMessagesD = new HashSet();
    expectedViolationMessagesD.add("Input string must be a JSON object.");

    assertEquals(expectedViolationNumber, actualViolations.size());

    Iterator<ConstraintViolation<SimpleBase>> actualViolationsIterator = actualViolations
        .iterator();
    while (actualViolationsIterator.hasNext()) {
      ConstraintViolationImpl<SimpleBase> violation = (ConstraintViolationImpl<SimpleBase>) actualViolationsIterator
          .next();
      assertTrue(expectedViolationMessagesD.contains(violation.getMessage()));
      expectedViolationMessagesD.remove(violation.getMessage());
    }
  }
}
