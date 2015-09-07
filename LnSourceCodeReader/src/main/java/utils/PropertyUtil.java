package utils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class PropertyUtil {
	private Properties ppt;
	
	public PropertyUtil() {
		ppt = new Properties();
		try {
			FileInputStream fis = new FileInputStream("config.properties");
			ppt.load(fis);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public String getValidProperty(String key) {
		return ppt.getProperty(key);
	}
}
