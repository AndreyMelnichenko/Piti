package utils;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.Properties;

public class DataProperties {
    private static DataProperties INSTANCE = new DataProperties();
    private final Properties configProp = new Properties();
    private String fileName = "data.properties";

    private DataProperties() {
        InputStream in = this.getClass().getClassLoader().getResourceAsStream(fileName);
        try {
            configProp.load(in);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String dataProperty(String key) {
        return INSTANCE.configProp.getProperty(key);
    }
}
