package ir.sahab.sahabino.fileingester;

import ir.sahab.sahabino.utility.Log;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;

import java.util.ArrayList;
import java.util.Properties;

public class LogKafkaProducer{

    KafkaProducer<String, Log> producer;
    String topic;
    public LogKafkaProducer(Properties properties, String topic){
        producer = new KafkaProducer<>(properties);
        this.topic = topic;
    }
    public void send(Log log){
        String key = "file ingester data";
        producer.send(new ProducerRecord<>(topic, key, log));
    }

    public void sendList(ArrayList<Log> logs) {
        for(Log log:logs)
            send(log);
    }
}