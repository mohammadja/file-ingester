package ir.sahab.sahabino;

import org.junit.jupiter.api.Test;

import static junit.framework.Assert.*;

public class LogTest {
    @Test
    public void badConstructorFormatTest(){
        String logStr = "bad format log :D";
        try {
            Log log = new Log(null, logStr);
            fail();
        } catch (Exception e) {
            assertTrue(true);
        }
    }
    Log exceptionFreeBuild(String logStr){
        try {
            return new Log(null, logStr);
        } catch (Exception e) {
            fail();
        }
        return null;
    }
    @Test
    public void timeTest() {
        String logStr = "2021-07-12 01:22:42,114 [ThreadName] INFO package.name.ClassName – Something is wrong";
        Log log = exceptionFreeBuild(logStr);
        assertEquals(log.getDate().getTime(), 1626036762114L);
    }
    @Test
    public void threadTest() {
        String logStr = "2021-07-12 01:22:42,114 [ThreadName] INFO package.name.ClassName – Something is wrong";
        Log log = exceptionFreeBuild(logStr);
        assertEquals(log.getThreadName(), "ThreadName");
    }
    @Test
    public void logTypeTest() {
        String logStr = "2021-07-12 01:22:42,114 [ThreadName] INFO package.name.ClassName – Something is wrong";
        Log log = exceptionFreeBuild(logStr);
        assertEquals(log.getLogType(), LogType.INFO);
    }
    @Test
    public void classNameTest() {
        String logStr = "2021-07-12 01:22:42,114 [ThreadName] INFO package.name.ClassName – Something is wrong";
        Log log = exceptionFreeBuild(logStr);
        assertEquals(log.getClassName(), "package.name.ClassName");
    }

    @Test
    public void messageTest() {
        String logStr = "2021-07-12 01:22:42,114 [ThreadName] INFO package.name.ClassName – Something is wrong";
        Log log = exceptionFreeBuild(logStr);
        assertEquals(log.getMessage(), "Something is wrong");
    }
}
