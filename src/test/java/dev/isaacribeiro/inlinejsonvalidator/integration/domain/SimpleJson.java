package dev.isaacribeiro.inlinejsonvalidator.integration.domain;

import dev.isaacribeiro.inlinejsonvalidator.annotation.Json;

public class SimpleJson extends Base {

  @Json(message = "Input string must be a JSON object.")
  private String value;

}
