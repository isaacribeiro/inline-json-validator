package dev.isaacribeiro.inlinejsonvalidator.integration.domain;

import dev.isaacribeiro.inlinejsonvalidator.annotation.Json;

public class MultipleMandatoryParameter extends Base {

  @Json(message = "Input string must be a JSON object.", mandatoryFields = {"keyA", "keyB"})
  private String value;

  public MultipleMandatoryParameter() {
  }

  public String getValue() {
    return value;
  }

  public void setValue(String value) {
    this.value = value;
  }

}
