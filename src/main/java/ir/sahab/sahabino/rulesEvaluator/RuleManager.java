package ir.sahab.sahabino.rulesEvaluator;

import ir.sahab.sahabino.utility.Log;
import org.apache.kafka.clients.consumer.ConsumerRecord;

import java.util.Properties;

public class RuleManager extends KafkaLogConsumer{
    private final RulePool rulePool = RulePool.getInstance();
    private final MySqlHandler sqlHandler;

    public RuleManager(Properties properties, String topic, MySqlHandler sqlHandler) {
        super(properties, topic);
        this.sqlHandler = sqlHandler;
    }

    @Override
    public void doActionPerRecord(ConsumerRecord<String, Log> record) {
        super.doActionPerRecord(record);
        updateRules(record);
    }
    private void updateRules(ConsumerRecord<String, Log> record) {
        for(Rule rule:rulePool.getRules()) {
            SQLRecord result = rule.update(record.value());
            if(result != null)
                sqlHandler.insert(result);;
        }
    }
}
