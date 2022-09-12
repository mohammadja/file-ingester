package ir.sahab.sahabino.fileIngester;

import ir.sahab.sahabino.common.config.Config;
import ir.sahab.sahabino.common.kafka.KafkaLogProducer;
import ir.sahab.sahabino.common.log.ComponentLogInfo;
import ir.sahab.sahabino.common.log.Log;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileNotFoundException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Scanner;

public class LogReader extends WatchTower {
    static final private Logger LOGGER = LoggerFactory.getLogger(WatchTower.class);

    private final KafkaLogProducer producer;
    public LogReader(String directoryAddress) throws Exception {
        super(directoryAddress);
        this.producer = new KafkaLogProducer(Config.KAFKA_TOPIC);
    }

    @Override
    void behave(String fileName) {
        super.behave(fileName);
        processFile(fileName);
    }

    void deleteFile(File file){
        // TODO: 9/9/22
    }


    void processFile(String fileName){
        ComponentLogInfo component = null;
        try {
            component = new ComponentLogInfo(fileName);
            ArrayList<String> logs = fileHandler(fileName);
            producer.sendList(Log.listTranslator(component, logs));
        } catch (ParseException e) {
            System.out.println("bad file name or log format");
            throw new RuntimeException(e);
        }

    }
    ArrayList<String> fileHandler(String fileName){
        File file  = new File(directoryAddress + '/' + fileName);
        try {
            Scanner scanner = new Scanner(file);
            ArrayList<String> logsStr = readLineByLine(scanner);
            deleteFile(file);
            return logsStr;
        } catch (FileNotFoundException e) {
            System.out.println("file not found" + fileName);
        }
        return new ArrayList<>();
    }

    private ArrayList<String> readLineByLine(Scanner scanner) {
        ArrayList<String> result = new ArrayList<>();
        while(scanner.hasNext()){
            result.add(scanner.nextLine());
        }
        return result;
    }
}
