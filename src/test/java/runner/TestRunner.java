package runner;
import org.testng.annotations.Listeners;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import utils.TestListener;

@CucumberOptions(
  features = "src/test/resources/featureFile",  // point to your folder
  glue     = {"StepDefinitions", "utils"},
  plugin   = {"pretty", "html:target/cucumber.html"},
  monochrome = true
)
@Listeners(TestListener.class)
public class TestRunner extends AbstractTestNGCucumberTests {
	
}
