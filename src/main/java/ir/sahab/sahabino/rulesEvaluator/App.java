package ir.sahab.sahabino.rulesEvaluator;

import ir.sahab.sahabino.common.database.MySqlHandler;


import static ir.sahab.sahabino.common.config.Config.KAFKA_TOPIC;

public class App {
    static MySqlHandler sql = new MySqlHandler("jdbc:mysql://localhost/","root","root","Notification");
    public static void main(String[] args) {
        RuleManager ruleManager = new RuleManager(KAFKA_TOPIC, sql);
        ruleManager.start();
    }

}
