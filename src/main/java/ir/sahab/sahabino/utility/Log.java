package ir.sahab.sahabino.utility;


import com.google.gson.Gson;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Log {
    final transient private String logPatternString = "(?<date>\\d{4}-\\d{2}-\\d{2} \\d{2}:\\d{2}:\\d{2},\\d+) \\[(?<threadName>\\w+)]" +
            " (?<logType>[\\w.]+) (?<className>[\\w.]+) – (?<message>.*)$";
    final transient private String datePattern = "yyyy-MM-dd HH:mm:ss,SSS";
    private  Date date;
    private LogType logType;
    private String message, threadName, className;
    private final ComponentLogInfo component;
    public Log(ComponentLogInfo componentLogInfo, String sampleLog) throws ParseException {
        component = componentLogInfo;
        Pattern regexPattern = Pattern.compile(logPatternString);
        fillFields(sampleLog, regexPattern);
    }
    private void fillFields(String sampleLog, Pattern pattern) throws ParseException {
        Matcher matcher = getExceptionFreeMatcher(sampleLog, pattern);
        this.setDate(matcher.group("date"));
        this.threadName = matcher.group("threadName");
        this.logType = LogType.valueOf(matcher.group("logType"));
        this.className = matcher.group("className");
        this.message = matcher.group("message");
    }

    private static Matcher getExceptionFreeMatcher(String sampleLog, Pattern pattern) throws ParseException {
        Matcher matcher = pattern.matcher(sampleLog);
        if(!matcher.find()) throw new ParseException("bad log format (ignore index)", 0);
        return matcher;
    }

    private void setDate(String date) throws ParseException {
        SimpleDateFormat formatter = new SimpleDateFormat(datePattern);
        this.date = formatter.parse(date);
    }

    static public ArrayList<Log> listTranslator(ComponentLogInfo component, ArrayList<String> logs) throws ParseException {
        ArrayList<Log> result = new ArrayList<>();
        for(String log:logs)
            result.add(new Log(component, log));
        return result;
    }

    public Date getDate() {
        return date;
    }

    public LogType getLogType() {
        return logType;
    }

    public String getMessage() {
        return message;
    }

    public String getThreadName() {
        return threadName;
    }

    public String getClassName() {
        return className;
    }

    public ComponentLogInfo getComponent() {
        return component;
    }

    public String getJson(){
        Gson gson = new Gson();
        return gson.toJson(this);
    }
}
