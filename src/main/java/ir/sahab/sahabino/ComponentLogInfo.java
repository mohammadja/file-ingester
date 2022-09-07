package ir.sahab.sahabino;

import java.util.Date;

import java.text.ParseException;
import java.text.SimpleDateFormat;

public class ComponentLogInfo {
    String timeFormat = "uuuu_MM_dd-HH_mm_ss";
    SimpleDateFormat format ;
    String componentName = "";
    Date date;
    public ComponentLogInfo(String fileName) throws ParseException {
        format = new SimpleDateFormat(timeFormat);
        componentName = fileName.substring(0, fileName.length() - timeFormat.length());
        String dateString = fileName.substring(fileName.length() - timeFormat.length());
        date = format.parse(dateString);
    }

    public ComponentLogInfo(String fileName, String timeFormat) throws ParseException {
        this.timeFormat = timeFormat;
        format = new SimpleDateFormat(timeFormat);
        componentName = fileName.substring(0, fileName.length() - timeFormat.length());
        String dateString = fileName.substring(fileName.length() - timeFormat.length());
        date = format.parse(dateString);
    }
}
