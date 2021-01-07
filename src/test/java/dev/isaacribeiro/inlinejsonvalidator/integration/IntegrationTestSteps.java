package dev.isaacribeiro.inlinejsonvalidator.integration;

import static org.junit.jupiter.api.Assertions.assertEquals;

import dev.isaacribeiro.inlinejsonvalidator.integration.domain.Base;
import dev.isaacribeiro.inlinejsonvalidator.integration.domain.MultipleMandatoryParameter;
import dev.isaacribeiro.inlinejsonvalidator.integration.domain.MultipleMandatoryParameterWithSubNodes;
import dev.isaacribeiro.inlinejsonvalidator.integration.domain.SimpleJson;
import dev.isaacribeiro.inlinejsonvalidator.integration.domain.SingleMandatoryParameter;
import dev.isaacribeiro.inlinejsonvalidator.integration.domain.SingleStringField;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import java.util.HashSet;
import java.util.Set;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import org.hibernate.validator.HibernateValidator;

public class IntegrationTestSteps {

  private static Validator validator;
  private Base baseEntity;
  private Set<ConstraintViolation<Base>> actualViolations;

  @Before
  public static void classSetup() {
    validator = Validation.byProvider(HibernateValidator.class)
        .configure()
        .buildValidatorFactory()
        .getValidator();
  }

  @Given("a Base instance with a @Json-annotated value attribute equals to {string}")
  public void a_base_instance_with_a_json_annotated_value_attribute_equals_to(String inputValue) {
    baseEntity = new SimpleJson();
    baseEntity.setValue(inputValue);
  }

  @Given("a Base instance with a @Json-annotated value attribute equals to {string} that expects the mandatory parameter \\'key\\'")
  public void a_base_instance_with_a_json_annotated_value_attribute_equals_to_that_expects_the_mandatory_parameter_key(
      String inputValue) {
    baseEntity = new SingleMandatoryParameter();
    baseEntity.setValue(inputValue);
  }

  @Given("a Base instance with a @Json-annotated value attribute equals to {string} that expects the mandatory parameter \\'keyA\\' and \\'keyB\\'")
  public void a_base_instance_with_a_json_annotated_value_attribute_equals_to_that_expects_the_mandatory_parameter_key_a_and_key_b(
      String inputValue) {
    baseEntity = new MultipleMandatoryParameter();
    baseEntity.setValue(inputValue);
  }

  @Given("a Base instance with a @Json-annotated value attribute equals to {string} that expects the mandatory parameter \\'key\\' and \\'key.subkey\\'")
  public void a_base_instance_with_a_json_annotated_value_attribute_equals_to_that_expects_the_mandatory_parameter_key_and_key_subkey(String inputValue) {
    baseEntity = new MultipleMandatoryParameterWithSubNodes();
    baseEntity.setValue(inputValue);
  }

  @Given("a Base instance with a @Property-annotated value attribute equals to {string}")
  public void a_base_instance_with_a_property_annotated_value_attribute_equals_to(
      String inputValue) {
    baseEntity = new SingleStringField();
    baseEntity.setValue(inputValue);
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

//    Iterator<ConstraintViolation<Base>> actualViolationsIterator = actualViolations.iterator();
//    while (actualViolationsIterator.hasNext()) {
//      ConstraintViolation<? extends Base> violation = actualViolationsIterator.next();
//      assertTrue(expectedViolationMessagesD.contains(violation.getMessage()));
//      expectedViolationMessagesD.remove(violation.getMessage());
//    }
  }
}
