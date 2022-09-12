package ir.sahab.sahabino.common.config;

import ir.sahab.sahabino.rulesEvaluator.Rules.*;

import java.util.ArrayList;

public class KafkaConfig {
    public static final String KAFKA_TOPIC = "logQueue";
    public static final String KAFKA_BOOTSTRAP_SERVERS = "localhost:9092";
    public static final String KAFKA_CLIENT_ID = "FileBeatLogQueue";
    public static final String WATCHING_FOLDER_ADDRESS = "/home/mojak/Desktop/job/sahab/log-test-folder";
    public static final String RULE_FILE_ADDRESS = "src/main/java/ir/sahab/sahabino/rulesEvaluator/Rules/rules.json";
    public static final String KAFKA_GROUP_ID = "FileBeatLogQueue";

}
