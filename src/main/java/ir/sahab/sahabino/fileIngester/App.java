package ir.sahab.sahabino.fileingester;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main(String[] args) {
        LogReader logReader;
        try {
            logReader = new LogReader(Config.WATCHING_FOLDER_ADDRESS);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        logReader.start();
    }
}
