package ConfigReader;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class ConfigPropReader {

	private Properties prop;
	private FileInputStream ip;

	public Properties initProp() throws IOException {
		prop = new Properties();
		try {
			ip = new FileInputStream("./src/main/resources/data.properties");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		prop.load(ip);
		return prop;
	}
}
