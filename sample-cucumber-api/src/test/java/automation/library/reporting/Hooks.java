package automation.library.reporting;

import automation.library.common.TestContext;
import io.cucumber.core.api.Scenario;
import io.cucumber.java8.En;

import java.io.File;

import static automation.library.reporting.Reporter.getCurrentStep;
import static automation.library.reporting.Reporter.*;

public class Hooks implements En{

	public Hooks(){
		After(10, (Scenario scenario) -> {
			if (scenario.isFailed()){
				getCurrentStep().fail("Scenario Failed");
				if (TestContext.getInstance().getFwSpecificData("fw.screenshotRelativePath") !=null) {
					addScreenCaptureFromPath((String) TestContext.getInstance().getFwSpecificData("fw.screenshotRelativePath"));
				}
			}else{
				getCurrentStep().pass("Scenario Successful");
			}
		});
	}
}
