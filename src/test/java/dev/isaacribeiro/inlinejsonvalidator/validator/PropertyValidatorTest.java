package dev.isaacribeiro.inlinejsonvalidator.validator;

import static org.junit.jupiter.api.Assertions.assertTrue;

import com.fasterxml.jackson.databind.node.JsonNodeType;
import dev.isaacribeiro.inlinejsonvalidator.helper.AnnotationHelper;
import org.junit.jupiter.api.Test;

class PropertyValidatorTest {

  private PropertyValidator cut = new PropertyValidator();

  @Test
  public void shoudReturnTrueWhenInputContainsExpectedPropertyAndMatchesExpectedType() {
    cut.initialize(AnnotationHelper.createPropertyAnnotation("key", JsonNodeType.STRING));
    assertTrue(cut.isValid("{\"key\": \"value\"}", null));
  }

}