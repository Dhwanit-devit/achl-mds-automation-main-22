package managers;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ApplicationProperties extends Properties {
	
	private static final String APPLICATION_PRO_PATH = "config/application.properties";
	private static final long serialVersionUID = 1L;
	private static ApplicationProperties props = null;

	private ApplicationProperties() throws IOException {
		load(new FileInputStream(APPLICATION_PRO_PATH));
	}

	public static synchronized ApplicationProperties getInstance() {
		if (props == null) {
			try {
				props = new ApplicationProperties();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return props;
	}
}