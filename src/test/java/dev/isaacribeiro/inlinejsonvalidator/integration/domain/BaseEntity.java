package dev.isaacribeiro.inlinejsonvalidator.integration.domain;

import dev.isaacribeiro.inlinejsonvalidator.annotation.Json;

public class BaseEntity {

  @Json(message = "Input string must be a JSON object.")
  private String value;

  public BaseEntity() {
  }

  public BaseEntity(String value) {
    this.value = value;
  }

  public String getValue() {
    return value;
  }

  public void setValue(String value) {
    this.value = value;
  }

}
