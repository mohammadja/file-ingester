package ir.sahab.sahabino.common.config;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class DBConfig {

    public static Properties PROPS = new Properties();
    static {
        try {
            InputStream input = new FileInputStream("src/main/resources/database.properties");
            PROPS.load(input);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public final static String DB_USER_NAME = PROPS.getProperty("DB_USER_NAME");
    public final static String DB_USER_PASS = PROPS.getProperty("DB_USER_PASS");
    public final static String DB_URL = PROPS.getProperty("DB_URL");
    public final static String DB_NAME = PROPS.getProperty("DB_NAME");
}
