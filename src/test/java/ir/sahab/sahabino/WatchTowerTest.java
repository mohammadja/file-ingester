package ir.sahab.sahabino;


import ir.sahab.sahabino.fileIngester.WatchTower;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import static junit.framework.Assert.*;

public class WatchTowerTest {
    static final String
            BASE_ADDRESS = System.getProperty("user.dir"),
            WATCH_TOWER_TEST_FOLDER_RELATIVE = "src/test/WatchTowerTestingSandBox",
            WATCH_TOWER_TEST_FOLDER = BASE_ADDRESS + '/' +  WATCH_TOWER_TEST_FOLDER_RELATIVE;
    @Test
    public void openNoneExistingFileTest(){
        try {
            WatchTower watchTower = new WatchTower(WATCH_TOWER_TEST_FOLDER_RELATIVE + "Not_A");
        } catch (Exception e){
            assertTrue(true);
            return;
        }
        fail();
    }
    @Test
    public void openExistingFileTest(){
        try {
            WatchTower watchTower = new WatchTower(WATCH_TOWER_TEST_FOLDER_RELATIVE);
        } catch (Exception e){
            fail();
            e.printStackTrace();;
            return;
        }
        assertTrue(true);
    }
    WatchTower buildExceptionFreeWatchTower(){
        try {
            return new WatchTower(WATCH_TOWER_TEST_FOLDER_RELATIVE);
        } catch (Exception e){
            //checked before
        }
        return null;
    }
    @Test
    public void emptyFolderTest(){
        WatchTower watchTower = buildExceptionFreeWatchTower();
        ArrayList<String> filesList = watchTower.getLogFiles();
        assertEquals(filesList.size(), 0);
    }
    @Test
    public void folderWithOneExistingFileTest(){
        File logFile  = makeTestLogFile("log.txt");
        WatchTower watchTower = buildExceptionFreeWatchTower();
        ArrayList<String> filesList = watchTower.getLogFiles();
        assertEquals(filesList.size(), 1);
        removeTestLogFile(logFile);
    }

    private File makeTestLogFile(String  fileName) {
        File logFile = new File(WATCH_TOWER_TEST_FOLDER + '/' + fileName);
        try {
            boolean isCreated = logFile.createNewFile();
            if(!isCreated){
                System.out.println("tester cannot create " + fileName + " file");
                fail();
            }
        } catch (IOException e){
            System.out.println("tester cannot create file");
            e.printStackTrace();
            fail();
        }
        return logFile;
    }

    private void removeTestLogFile(File logFile) {
        boolean deleted  = logFile.delete();
        logFile.deleteOnExit();
    }

    @Test
    public void watchTowerBehaviorTestOnSimpleFile() throws Exception {
        final boolean[] passed = {false};
        WatchTower watchTower = new WatchTower(WATCH_TOWER_TEST_FOLDER_RELATIVE){
            @Override
            void behave(String fileName) {
                passed[0] = true;
            }
        };
        watchTower.start();
        File logFile  = makeTestLogFile("log.txt");
        Thread.sleep(1000);
        assertTrue(passed[0]);
        watchTower.kill();
        removeTestLogFile(logFile);

    }
    @Test
    public void watchTowerBehaviorTestMultiSimpleFile() throws Exception {
        final int[] passed = {0};
        WatchTower watchTower = new WatchTower(WATCH_TOWER_TEST_FOLDER_RELATIVE){
            @Override
            void behave(String fileName) {
                passed[0] ++;
            }
        };
        watchTower.start();
        File logFile1  = makeTestLogFile("log1.txt");
        File logFile2  = makeTestLogFile("log2.txt");
        Thread.sleep(1000);
        assertEquals(passed[0], 2);
        watchTower.kill();
        removeTestLogFile(logFile1);
        removeTestLogFile(logFile2);
    }
}
