package dev.isaacribeiro.inlinejsonvalidator.integration;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(features = "src/test/resources/integration-tests", tags = "not @ignore")
public class CucumberIntegrationEntryPoint {

}
