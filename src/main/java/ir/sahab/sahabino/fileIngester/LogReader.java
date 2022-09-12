package ir.sahab.sahabino.fileIngester;

import ir.sahab.sahabino.common.config.KafkaConfig;
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

import static ir.sahab.sahabino.common.config.RulesConfig.DELETE_AFTER_READ;

public class LogReader extends WatchTower {
    static final private Logger LOGGER = LoggerFactory.getLogger(WatchTower.class);

    private final KafkaLogProducer producer;
    public LogReader(String directoryAddress) throws Exception {
        super(directoryAddress);
        this.producer = new KafkaLogProducer(KafkaConfig.KAFKA_TOPIC);
    }

    @Override
    void behave(String fileName) {
        super.behave(fileName);
        processFile(fileName);
    }

    void deleteFile(File file){
        if(file.delete())
            LOGGER.info("file deleted");
        else
            LOGGER.info("cant delete file" + file.getName());
    }


    void processFile(String fileName){
        LOGGER.info("Processing file (" + fileName + ")");
        try {
            ComponentLogInfo component = new ComponentLogInfo(fileName);
            ArrayList<String> logs = readAndExtractLogStrings(fileName);
            producer.sendList(Log.listTranslator(component, logs));
        } catch (ParseException e) {
            LOGGER.error("bad file name " + fileName + '\t' + e);
        }
    }
    private ArrayList<String> readAndExtractLogStrings(String fileName){
        File file  = new File(directoryAddress + '/' + fileName);
        try {
            Scanner scanner = new Scanner(file);
            ArrayList<String> logsStr = readLineByLine(scanner);
            try{
                if(DELETE_AFTER_READ)
                    deleteFile(file);
            } catch (Exception e){
                LOGGER.error("cannot delete file " + fileName + '\t' + e);
            }
            return logsStr;
        } catch (FileNotFoundException e) {
            LOGGER.error("file not found" + fileName);
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
