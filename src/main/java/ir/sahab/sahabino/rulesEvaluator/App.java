package ir.sahab.sahabino.rulesEvaluator;

import ir.sahab.sahabino.utility.Log;
import org.apache.kafka.clients.consumer.ConsumerRecord;

import static ir.sahab.sahabino.fileIngester.Config.KAFKA_TOPIC;
import static java.lang.Thread.sleep;

public class App {
    public static void main(String[] args) throws InterruptedException {
        KafkaLogConsumer consumer = new KafkaLogConsumer(Config.KAFKA_PROPERTIES, KAFKA_TOPIC){
            int a = 0;

            @Override
            public void doActionPerRecord(ConsumerRecord<String, Log> record) {
                super.doActionPerRecord(record);
                System.out.println(a++);
            }
        };
        consumer.start();

        MySqlHandler mySqlHandler = new MySqlHandler("jdbc:mysql://localhost/", "root", "root", "Notification");
        mySqlHandler.insert(new SQLRecord("test", "test", "test"));
        System.out.println(mySqlHandler.getLogList().size());
    }

}
