package dev.isaacribeiro.inlinejsonvalidator.integration;

import static org.junit.jupiter.api.Assertions.assertEquals;

import dev.isaacribeiro.inlinejsonvalidator.integration.domain.Base;
import dev.isaacribeiro.inlinejsonvalidator.integration.domain.ErrorHandlerWithCustomValidator;
import dev.isaacribeiro.inlinejsonvalidator.integration.domain.MultipleMandatoryParameter;
import dev.isaacribeiro.inlinejsonvalidator.integration.domain.MultipleMandatoryParameterWithSubNodes;
import dev.isaacribeiro.inlinejsonvalidator.integration.domain.SimpleJson;
import dev.isaacribeiro.inlinejsonvalidator.integration.domain.SingleArrayField;
import dev.isaacribeiro.inlinejsonvalidator.integration.domain.SingleBooleanField;
import dev.isaacribeiro.inlinejsonvalidator.integration.domain.SingleFieldWithCustomValidator;
import dev.isaacribeiro.inlinejsonvalidator.integration.domain.SingleFieldWithMultipleValidators;
import dev.isaacribeiro.inlinejsonvalidator.integration.domain.SingleMandatoryParameter;
import dev.isaacribeiro.inlinejsonvalidator.integration.domain.SingleNumericField;
import dev.isaacribeiro.inlinejsonvalidator.integration.domain.SingleObjectField;
import dev.isaacribeiro.inlinejsonvalidator.integration.domain.SingleStringField;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
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

  @Given("a Base instance with a Numeric @Property-annotated value attribute equals to {string}")
  public void a_base_instance_with_a_numeric_property_annotated_value_attribute_equals_to(
      String inputValue) {
    baseEntity = new SingleNumericField();
    baseEntity.setValue(inputValue);
  }

  @Given("a Base instance with a Boolean @Property-annotated value attribute equals to {string}")
  public void a_base_instance_with_a_boolen_property_annotated_value_attribute_equals_to(
      String inputValue) {
    baseEntity = new SingleBooleanField();
    baseEntity.setValue(inputValue);
  }

  @Given("a Base instance with an Array @Property-annotated value attribute equals to {string}")
  public void a_base_instance_with_an_array_property_annotated_value_attribute_equals_to(
      String inputValue) {
    baseEntity = new SingleArrayField();
    baseEntity.setValue(inputValue);
  }

  @Given("a Base instance with an object @Property-annotated value attribute equals to {string}")
  public void a_base_instance_with_an_object_property_annotated_value_attribute_equals_to(
      String inputValue) {
    baseEntity = new SingleObjectField();
    baseEntity.setValue(inputValue);
  }

  @Given("a Base instance with a @Property-annotated value attribute equals to {string} has its own validator")
  public void a_base_instance_with_a_property_annotated_value_attribute_equals_to_has_its_own_validator(
      String inputValue) {
    baseEntity = new SingleFieldWithCustomValidator();
    baseEntity.setValue(inputValue);
  }

  @Given("a Base instance with a value attribute equals to {string} has its own validator that throws an Exception.")
  public void a_base_instance_with_a_value_attribute_equals_to_has_its_own_validator_that_throws_an_exception(
      String inputValue) {
    baseEntity = new ErrorHandlerWithCustomValidator();
    baseEntity.setValue(inputValue);
  }

  @Given("a Base instance with a value attribute equals to {string} that is assigned to two different custom Validators")
  public void a_base_instance_with_a_value_attribute_equals_to_that_is_assigned_to_two_different_custom_validators(
      String inputValue) {
    baseEntity = new SingleFieldWithMultipleValidators();
    baseEntity.setValue(inputValue);
  }

  @When("it is validated")
  public void it_is_validated() {
    actualViolations = validator.validate(baseEntity);
  }

  @Then("{int} violations should be raised")
  public void violations_should_be_raised(Integer expectedViolationNumber) {
    assertEquals(expectedViolationNumber, actualViolations.size());
  }
}
