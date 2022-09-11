package ir.sahab.sahabino.fileIngester;

import ir.sahab.sahabino.utility.LogSerializer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;

import java.util.Properties;

public class Config {
    public static final String KAFKA_TOPIC = "streams-plaintext-input";
    public static final String KAFKA_BOOTSTRAP_SERVERS = "localhost:9092";
    static final Properties KAFKA_PROPERTIES;
    static {
        KAFKA_PROPERTIES = new Properties();
        KAFKA_PROPERTIES.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, KAFKA_BOOTSTRAP_SERVERS);
        KAFKA_PROPERTIES.put(ProducerConfig.CLIENT_ID_CONFIG, "FileBeatLogQueue");
        KAFKA_PROPERTIES.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        KAFKA_PROPERTIES.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, LogSerializer.class.getName());
    }
    static String WATCHING_FOLDER_ADDRESS = "/home/mojak/Desktop/job/sahab/log-test-folder";
}
