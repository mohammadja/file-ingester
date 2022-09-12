package ir.sahab.sahabino.common.kafka;

import ir.sahab.sahabino.common.log.Log;
import ir.sahab.sahabino.common.log.LogSerializer;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Properties;


import static ir.sahab.sahabino.common.config.KafkaConfig.KAFKA_BOOTSTRAP_SERVERS;
import static ir.sahab.sahabino.common.config.KafkaConfig.KAFKA_CLIENT_ID;

public class KafkaLogProducer {
    static private final Logger LOGGER = LoggerFactory.getLogger(KafkaLogProducer.class);
    private final KafkaProducer<String, Log> producer;
    private final String topic;
    private int logID = 0;
    public KafkaLogProducer(String topic){
        Properties properties = makeKafkaProperties();
        producer = new KafkaProducer<>(properties);
        this.topic = topic;
        Runtime.getRuntime().addShutdownHook(new Thread(producer::close));
    }

    private Properties makeKafkaProperties() {
        Properties kafkaProperties = new Properties();
        kafkaProperties.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, KAFKA_BOOTSTRAP_SERVERS);
        kafkaProperties.put(ProducerConfig.CLIENT_ID_CONFIG, KAFKA_CLIENT_ID);
        kafkaProperties.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        kafkaProperties.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, LogSerializer.class.getName());
        return kafkaProperties;
    }

    public void send(Log log){
        String key = "log number " + logID++;
        producer.send(new ProducerRecord<>(topic, key, log));
        LOGGER.info("log with key = '" + key + "' sent");
    }

    public void sendList(ArrayList<Log> logs) {
        for(Log log:logs)
            send(log);
    }
}