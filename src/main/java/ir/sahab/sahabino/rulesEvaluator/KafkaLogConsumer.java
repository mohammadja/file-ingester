package ir.sahab.sahabino.rulesEvaluator;

import ir.sahab.sahabino.utility.Log;
import org.apache.kafka.clients.consumer.*;


import java.util.Collections;
import java.util.Properties;

public class KafkaLogConsumer extends Thread{
    final Consumer<String, Log> consumer;

    public KafkaLogConsumer(Properties properties, String topic){
        this.consumer = new KafkaConsumer<String, Log>(properties);
        consumer.subscribe(Collections.singletonList(topic));
        Runtime.getRuntime().addShutdownHook(new Thread(consumer::close));
    }

    @Override
    public void run() {
        super.run();
        while (true) {
            final ConsumerRecords<String, Log> consumerRecords =
                    consumer.poll(1000);
            consumerRecords.forEach(this::doActionPerRecord);
            consumer.commitAsync();
        }
    }

    public void doActionPerRecord(ConsumerRecord<String, Log> record) {}
    public void close(){consumer.close();}
}
