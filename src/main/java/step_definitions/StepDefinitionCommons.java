package step_definitions;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import manager.RootInitializer;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class StepDefinitionCommons {
    private RootInitializer rootInitializer;
    private WebDriver driver;

    private static final Logger LOG = LoggerFactory.getLogger(StepDefinitionCommons.class);

    public StepDefinitionCommons(RootInitializer rootInitializer) {
        this.rootInitializer = rootInitializer;
        driver = rootInitializer.getWebDriver();
    }

    @Before
    public void setup(Scenario scenario) {
        LOG.info("****************    Starting Scenario: {}    ****************", scenario.getName());
    }

    @After
    public void tearDown(Scenario scenario) {
        LOG.info("****************   Scenario: {} completed with STATUS: {}   ****************",
                scenario.getName(), scenario.getStatus());
        if (scenario.isFailed()) {
            LOG.info("****** Taking Screen shot for failed Scenario  ******");
            byte[] screenShot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
            scenario.attach(screenShot, "image/png", scenario.getName());
        }
        if (driver != null) {
            driver.quit();
        }
    }
}
