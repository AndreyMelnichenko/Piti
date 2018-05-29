package utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class DataProperties {

    public static String dataProperty(String file, String key) {
    Properties configProp = new Properties();
        InputStream in = DataProperties.class.getClassLoader().getResourceAsStream(file);
        try {
            configProp.load(in);
            String value = new String(configProp.getProperty(key).getBytes("ISO8859-1"));
            return value;
        } catch (IOException e) {
            return "Exception!!!";
        }
    }
}