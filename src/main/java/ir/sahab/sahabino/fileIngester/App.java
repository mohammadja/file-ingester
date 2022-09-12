package ir.sahab.sahabino.fileIngester;

import ir.sahab.sahabino.common.config.Config;

import static java.lang.Thread.sleep;

public class App {
    public static void main(String[] args) throws InterruptedException {
        LogReader logReader;
        try {
            logReader = new LogReader(Config.WATCHING_FOLDER_ADDRESS);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        logReader.start();
    }
}
