package TestRunners;

import org.junit.runner.RunWith;

import io.cucumber.junit.CucumberOptions;
import io.cucumber.junit.Cucumber;

@RunWith(Cucumber.class)
@CucumberOptions(
		monochrome = true, // better console output
		strict = true, // undefined and pending steps should be treated as errors
		features = "src/test/resources/features", // path to feature files
		glue  = "src.test.java.steps"
)

public class RunDBBTests {}