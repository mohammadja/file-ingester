package ir.sahab.sahabino;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

public class WatchTower extends Thread {
    final Path watchingAddress;
    final Map<String, Boolean> filesCheckList;
    final String address;
    private boolean killed = false;


    public WatchTower(String address) throws Exception {
        this.address = address;
        this.watchingAddress = Paths.get(address);
        if (!Files.isDirectory(watchingAddress))
            throw new Exception();
        filesCheckList = new HashMap<>();
    }


    public ArrayList<String> getLogFiles() {
        return new ArrayList<>(Arrays.asList(Objects.requireNonNull(new File(address).list())));
    }

    void behave(String fileName) {
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
        while (true) {
            if (killed)
                break;
            try {
                sleep(100);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            ArrayList<String> files = getNewLogFiles();
            for (String file : files) {
                behave(file);
                check(file);
            }
        }

    }

    private void check(String file) {
        filesCheckList.put(file, true);
    }

    public void kill() {
        killed = true;
    }
}
