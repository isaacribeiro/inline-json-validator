package dev.isaacribeiro.inlinejsonvalidator.helper;

import com.fasterxml.jackson.databind.node.JsonNodeType;
import dev.isaacribeiro.inlinejsonvalidator.annotation.Json;
import dev.isaacribeiro.inlinejsonvalidator.annotation.Property;
import org.hibernate.validator.internal.util.annotation.AnnotationDescriptor;
import org.hibernate.validator.internal.util.annotation.AnnotationDescriptor.Builder;

public class AnnotationHelper {

  public static Json createJsonAnnotation(String... mandatoryFields) {
    AnnotationDescriptor.Builder<Json> annotationDescriptor = new Builder<>(
        Json.class);
    annotationDescriptor.setAttribute("mandatoryFields", mandatoryFields);
    return annotationDescriptor.build().getAnnotation();
  }

  public static Property createPropertyAnnotation(String name, JsonNodeType type) {
    AnnotationDescriptor.Builder<Property> annotationDescriptor = new Builder<>(
        Property.class);
    annotationDescriptor.setAttribute("name", name);
    annotationDescriptor.setAttribute("type", type);
    return annotationDescriptor.build().getAnnotation();
  }
}
