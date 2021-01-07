package dev.isaacribeiro.inlinejsonvalidator.integration.domain;

import dev.isaacribeiro.inlinejsonvalidator.annotation.Json;

public class MultipleMandatoryParameter extends Base {

  @Json(message = "Input string must be a JSON object.", mandatoryFields = {"keyA", "keyB"})
  private String value;

}
