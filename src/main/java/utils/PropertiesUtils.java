package utils;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Created by olena.kolesnyk on 16/02/2018.
 */
public class PropertiesUtils {

    private static final String PROPERTIES_FILE_NAME = "data.properties";

    public Properties getPropertiesFile() throws IOException {
        Properties properties = null;
        InputStream inputStream = null;
        try {
            properties = new Properties();
            inputStream = getClass().getClassLoader().getResourceAsStream(PROPERTIES_FILE_NAME);

            if (inputStream != null) {
                properties.load(inputStream);
            } else {
                throw new FileNotFoundException("Property file '" + PROPERTIES_FILE_NAME + "' is not found");
            }
        } catch (Exception e) {
            System.out.println("Exception: " + e);
        } finally {
            if (inputStream != null) {
                inputStream.close();
            }
        }
        return properties;
    }

}
