package Test.TestRunner;
import io.cucumber.junit.platform.engine.Constants;
import org.junit.platform.suite.api.ConfigurationParameter;
import org.junit.platform.suite.api.IncludeEngines;
import org.junit.platform.suite.api.SelectClasspathResource;
import org.junit.platform.suite.api.Suite;

@Suite
@IncludeEngines("cucumber")
@SelectClasspathResource("com/mycompany/features") // Directory containing .feature files
@ConfigurationParameter(key = Constants.GLUE_PROPERTY_NAME, value = "com.mycompany.stepdefinitions") // Package for step definitions
@ConfigurationParameter(key = Constants.FILTER_TAGS_PROPERTY_NAME, value = "@smoke and not @wip") // Scenario filter
@ConfigurationParameter(key = Constants.PLUGIN_PROPERTY_NAME, value = "pretty, html:target/cucumber-reports/index.html") // Report format
@ConfigurationParameter(key = Constants.EXECUTION_DRY_RUN_PROPERTY_NAME, value = "false") // Set to true to check for missing steps
public class BDD_TestRunner {
        // This class remains empty. It serves solely as an entry point.
}
