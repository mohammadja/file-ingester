package ir.sahab.sahabino.rulesEvaluator;

import ir.sahab.sahabino.common.kafka.KafkaLogConsumer;
import ir.sahab.sahabino.common.log.Log;
import org.apache.kafka.clients.consumer.ConsumerRecord;

import static ir.sahab.sahabino.common.config.Config.KAFKA_TOPIC;

public class App {
    public static void main(String[] args) {
        KafkaLogConsumer consumer = new KafkaLogConsumer(KAFKA_TOPIC) {
            int a = 0;

            @Override
            public void doActionPerRecord(ConsumerRecord<String, Log> record) {
                super.doActionPerRecord(record);
                System.out.println(a++);
            }
        };
        consumer.start();
    }

}
