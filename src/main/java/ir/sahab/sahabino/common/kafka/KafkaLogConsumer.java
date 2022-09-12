package ir.sahab.sahabino.common.kafka;

import ir.sahab.sahabino.common.log.Log;
import ir.sahab.sahabino.common.log.LogDeserializer;
import org.apache.kafka.clients.consumer.*;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import java.util.Collections;
import java.util.Properties;

import static ir.sahab.sahabino.common.config.Config.*;

public class KafkaLogConsumer extends Thread {
    static private final Logger LOGGER = LoggerFactory.getLogger(KafkaLogConsumer.class);
    private final Consumer<String, Log> consumer;
    private boolean stopped = false;

    public KafkaLogConsumer(String topic) {
        Properties properties = makeKafkaProperties();
        this.consumer = new KafkaConsumer<>(properties);
        consumer.subscribe(Collections.singletonList(topic));
        Runtime.getRuntime().addShutdownHook(new Thread(consumer::close));
    }

    private Properties makeKafkaProperties() {
        Properties kafkaProperties = new Properties();
        kafkaProperties.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, KAFKA_BOOTSTRAP_SERVERS);
        kafkaProperties.put(ConsumerConfig.CLIENT_ID_CONFIG, KAFKA_CLIENT_ID);
        kafkaProperties.put(ConsumerConfig.GROUP_ID_CONFIG, KAFKA_GROUP_ID);
        kafkaProperties.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        kafkaProperties.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, LogDeserializer.class.getName());
        return kafkaProperties;
    }

    @Override
    public void run() {
        LOGGER.info("Consumer started");
        super.run();
        while (!stopped) {
            final ConsumerRecords<String, Log> consumerRecords =
                    consumer.poll(1000);
            consumerRecords.forEach(this::doActionPerRecord);
            consumer.commitAsync();
        }
        consumer.close();
    }

    public void doActionPerRecord(ConsumerRecord<String, Log> record) {
        LOGGER.info("log with key = '" + record.key() + "' received");
    }

    public void close() {
        stopped = true;
    }
}
