package runners;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/main/resources/features",
        glue = {"utils", "stepdefinitions"},
        plugin = {"pretty", "html:target/cucumber-html-report","json:target/cucumber.json"}

)
public final class TestRunner {

}

