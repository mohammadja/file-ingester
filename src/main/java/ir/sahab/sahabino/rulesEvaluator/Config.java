package ir.sahab.sahabino.rulesEvaluator;

import ir.sahab.sahabino.rulesEvaluator.Rules.TopLimitRule;
import ir.sahab.sahabino.utility.LogDeserializer;
import ir.sahab.sahabino.utility.LogSerializer;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.apache.kafka.common.serialization.StringSerializer;

import java.util.ArrayList;
import java.util.Properties;

public class Config {
    static final ArrayList<Class> RULES = new ArrayList<>();
    static final String RULE_FILE_ADDRESS = "src/main/java/ir/sahab/sahabino/rulesEvaluator/Rules/rules.json";
    static {
        RULES.add(TopLimitRule.class);
    }
    static final String KAFKA_BOOTSTRAP_SERVERS = "localhost:9092";
    public static final Properties KAFKA_PROPERTIES;
    static {
        KAFKA_PROPERTIES = new Properties();
        KAFKA_PROPERTIES.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, KAFKA_BOOTSTRAP_SERVERS);
        KAFKA_PROPERTIES.put(ConsumerConfig.CLIENT_ID_CONFIG, "FileBeatLogQueue");
        KAFKA_PROPERTIES.put("group.id", "FileBeatLogQueue");
        KAFKA_PROPERTIES.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        KAFKA_PROPERTIES.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, LogDeserializer.class.getName());
    }
}
