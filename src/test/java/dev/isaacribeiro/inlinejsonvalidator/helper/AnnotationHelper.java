package dev.isaacribeiro.inlinejsonvalidator.helper;

import dev.isaacribeiro.inlinejsonvalidator.annotation.Json;
import org.hibernate.validator.internal.util.annotation.AnnotationDescriptor;

public class AnnotationHelper {

  public static Json createAnnotation(String... mandatoryFields) {
    AnnotationDescriptor.Builder<Json> annotationDescriptor = new AnnotationDescriptor.Builder<>(
        Json.class);
    annotationDescriptor.setAttribute("mandatoryFields", mandatoryFields);
    return annotationDescriptor.build().getAnnotation();
  }
}
