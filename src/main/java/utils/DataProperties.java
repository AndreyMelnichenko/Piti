package utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import static utils.PropList.propertiesFile;

public class DataProperties {
    private static DataProperties INSTANCE = new DataProperties();
    private static Properties configProp = new Properties();

    private DataProperties() {
        InputStream in = this.getClass().getClassLoader().getResourceAsStream(propertiesFile().get("local"));
        try {
            configProp.load(in);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String dataProperty(String key) {
        return INSTANCE.configProp.getProperty(key);
    }

    public static void main(String[] args) {
        DataProperties dataProperties = new DataProperties();
        InputStream in = dataProperties.getClass().getClassLoader().getResourceAsStream("test.properties");

    }
}