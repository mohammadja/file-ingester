package ir.sahab.sahabino;

import java.util.Date;

import java.text.ParseException;
import java.text.SimpleDateFormat;

public class ComponentLogInfo {
    String timeFormat = "yyyy_MM_dd-HH_mm_ss";
    transient SimpleDateFormat format ;
    private String componentName;
    private Date date;
    public ComponentLogInfo(String fileName) throws ParseException {
        // TODO: 9/9/22
        format = new SimpleDateFormat(timeFormat);
        componentName = fileName.substring(0, fileName.length() - timeFormat.length() - 4);
        String dateString = fileName.substring(fileName.length() - timeFormat.length() - 4, fileName.length() - 4);
        date = format.parse(dateString);
    }

    public ComponentLogInfo(String fileName, String timeFormat) throws ParseException {
        this.timeFormat = timeFormat;
        format = new SimpleDateFormat(timeFormat);
        componentName = fileName.substring(0, fileName.length() - timeFormat.length());
        String dateString = fileName.substring(fileName.length() - timeFormat.length());
        date = format.parse(dateString);
    }

    public String getComponentName() {
        return componentName;
    }

    public Date getDate() {
        return date;
    }
}
