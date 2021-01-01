package dev.isaacribeiro.inlinejsonvalidator.integration.domain;

import dev.isaacribeiro.inlinejsonvalidator.annotation.Json;

public class MultipleMandatoryParameterWithSubNodes extends Base {

  @Json(message = "Input string must be a JSON object.", mandatoryFields = {"key", "key.subkey"})
  private String value;

  public MultipleMandatoryParameterWithSubNodes() {
  }

  public String getValue() {
    return value;
  }

  public void setValue(String value) {
    this.value = value;
  }

}
