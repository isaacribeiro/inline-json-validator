package dev.isaacribeiro.inlinejsonvalidator.integration.domain;

import dev.isaacribeiro.inlinejsonvalidator.annotation.Json;

public class SimpleBase {

  @Json(message = "Input string must be a JSON object.")
  private String value;

  public SimpleBase() {
  }

  public SimpleBase(String value) {
    this.value = value;
  }

  public String getValue() {
    return value;
  }

  public void setValue(String value) {
    this.value = value;
  }

}
