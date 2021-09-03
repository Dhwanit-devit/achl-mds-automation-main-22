package runners;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.filefilter.TrueFileFilter;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
				features = "src/test/resources/FeatureFiles", 
				glue = {"helper", "stepDefinitions" }, 
				plugin ={ 	"pretty", 	
							"json:target/cucumber-reports/Cucumber.json",
							"junit:target/cucumber-reports/Cucumber.xml", 
							"html:target/cucumber-reports/Cucumber.html" 
						},
				dryRun = false,
				tags = "@F1"
				)

public class TestRunner extends AbstractTestNGCucumberTests {
	@AfterTest(alwaysRun = true)
	public void closeDriver() throws IOException {
		
		if (System.getProperty("os.name").contains("Windows")) {
			Runtime.getRuntime().exec("taskkill /F /IM chromedriver.exe /T");
		} else if (System.getProperty("os.name").contains("Linux")) {
			String[] cmd = new String[] { "sh", "killchrome.sh" };
			Runtime.getRuntime().exec(cmd);
		}
		
	}

	@AfterSuite(alwaysRun = true)
	public void generateReport() {
		String filename = null;
		String fileDestination = System.getProperty("user.dir") + "/report_generation";
		System.out.println("Path is---->>" + fileDestination);
		ProcessBuilder pb;

		if (System.getProperty("os.name").contains("Windows")) {
			filename = "execution.bat";
			pb = new ProcessBuilder("cmd", "/c", filename);
		} else {
			filename = "execution.sh";
			pb = new ProcessBuilder("sh", filename);
		}
		File dir = new File(fileDestination);
		pb.directory(dir);
		try {
			Process p = pb.start();
			p.waitFor();
			System.out.println("Creating Report...");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
