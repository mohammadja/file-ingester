package ir.sahab.sahabino.rulesEvaluator;

import ir.sahab.sahabino.common.database.MySqlHandler;


import static ir.sahab.sahabino.common.config.KafkaConfig.KAFKA_TOPIC;
import static ir.sahab.sahabino.common.config.DBConfig.*;


public class App {
    static MySqlHandler sql = new MySqlHandler(DB_URL,DB_USER_NAME,DB_USER_PASS,DB_NAME);
    public static void main(String[] args) {
        RuleManager ruleManager = new RuleManager(KAFKA_TOPIC, sql);
        ruleManager.start();
    }

}
