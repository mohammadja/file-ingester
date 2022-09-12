package ir.sahab.sahabino.common.config;

import ir.sahab.sahabino.rulesEvaluator.Rules.*;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Properties;

public class KafkaConfig {
    public static Properties PROPS = new Properties();
    static {
        try {
            InputStream input = new FileInputStream("src/main/resources/kafka.properties");
            PROPS.load(input);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public static final String KAFKA_TOPIC = PROPS.getProperty("KAFKA_TOPIC");
    public static final String KAFKA_BOOTSTRAP_SERVERS = PROPS.getProperty("KAFKA_BOOTSTRAP_SERVERS");
    public static final String KAFKA_CLIENT_ID = PROPS.getProperty("KAFKA_CLIENT_ID");
    public static final String KAFKA_GROUP_ID = PROPS.getProperty("KAFKA_GROUP_ID");
    public static final String WATCHING_FOLDER_ADDRESS = PROPS.getProperty("WATCHING_FOLDER_ADDRESS");
    public static final String RULE_FILE_ADDRESS = PROPS.getProperty("RULE_FILE_ADDRESS");

}
