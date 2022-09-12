package ir.sahab.sahabino.fileIngester;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.ParseException;
import java.util.*;

public class WatchTower extends Thread {
    private static final Logger LOGGER = LoggerFactory.getLogger(WatchTower.class);
    final Map<String, Boolean> filesCheckList;
    final String directoryAddress;
    private boolean killed = false;


    public WatchTower(String directoryAddress) throws ParseException {
        Path watchingAddress = Paths.get(directoryAddress);
        if (!Files.isDirectory(watchingAddress))
            throw new ParseException("bad directory address (ignore index)", 0);

        this.directoryAddress = directoryAddress;
        filesCheckList = new HashMap<>();
    }


    public ArrayList<String> getLogFiles() {
        return new ArrayList<>(Arrays.asList(Objects.requireNonNull(new File(directoryAddress).list())));
    }

    void behave(String fileName) {
        LOGGER.info("Watchtower finds new file with name = '" + fileName + "'");
    }

    boolean isLogFileChecked(String fileName) {
        return filesCheckList.containsKey(fileName);
    }

    private ArrayList<String> getNewLogFiles() {
        ArrayList<String> newFiles = new ArrayList<>();
        for (String file : getLogFiles()) {
            if (!isLogFileChecked(file)) {
                newFiles.add(file);
            }
        }
        return newFiles;
    }

    @Override
    public void run() {
        while (!killed) {
            try {
                sleep(100);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            ArrayList<String> files = getNewLogFiles();
            for (String file : files) {
                behave(file);
                markAsOldFile(file);
            }
        }
    }

    private void markAsOldFile(String file) {
        filesCheckList.put(file, true);
    }

    public void kill() {
        killed = true;
    }
}
