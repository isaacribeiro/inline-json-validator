package dev.isaacribeiro.inlinejsonvalidator.integration.domain;

import dev.isaacribeiro.inlinejsonvalidator.annotation.Json;

public class SingleMandatoryParameter extends Base {

  @Json(message = "Input string must be a JSON object.", mandatoryFields = {"key"})
  private String value;

  public SingleMandatoryParameter() {
  }

  public String getValue() {
    return value;
  }

  public void setValue(String value) {
    this.value = value;
  }

}
