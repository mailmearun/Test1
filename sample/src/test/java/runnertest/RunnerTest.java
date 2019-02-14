package runnertest;

import org.junit.runner.RunWith;
import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;

@RunWith(Cucumber.class)
@CucumberOptions(
		dryRun = false,
        monochrome = true,
        plugin = { "pretty","html:target/cucumber" },
        features = "src/test/resources/Features",
        tags = {"@Regression"},
        glue = {"stepdefs"}

		)


public class RunnerTest {

}
