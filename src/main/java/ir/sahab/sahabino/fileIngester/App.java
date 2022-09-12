package ir.sahab.sahabino.fileIngester;

import ir.sahab.sahabino.common.config.KafkaConfig;

import static java.lang.Thread.sleep;

public class App {
    public static void main(String[] args) {
        LogReader logReader;
        try {
            logReader = new LogReader(KafkaConfig.WATCHING_FOLDER_ADDRESS);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        logReader.start();
    }
}
