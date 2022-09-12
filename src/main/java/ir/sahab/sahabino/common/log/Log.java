package ir.sahab.sahabino.common.log;


import com.google.gson.Gson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.regex.Matcher;


import static ir.sahab.sahabino.common.config.LogConfig.LOG_DATE_PATTERN;
import static ir.sahab.sahabino.common.config.LogConfig.LOG_PATTERN_STRING;
import static ir.sahab.sahabino.common.log.Parser.getDateFromString;
import static ir.sahab.sahabino.common.log.Parser.getMatcher;

public class Log {
    private static final Logger LOGGER = LoggerFactory.getLogger(Log.class);
    private final Date date;
    private final LogType logType;
    private final String message, threadName, className;
    private final ComponentLogInfo component;

    public Log(ComponentLogInfo componentLogInfo, String sampleLog) throws ParseException {
        component = componentLogInfo;
        Matcher matcher = getMatcher(sampleLog, LOG_PATTERN_STRING);
        this.date = getDateFromString(matcher.group("date"), LOG_DATE_PATTERN);
        this.threadName = matcher.group("threadName");
        this.logType = LogType.valueOf(matcher.group("logType"));
        this.className = matcher.group("className");
        this.message = matcher.group("message");
    }


    static public ArrayList<Log> listTranslator(ComponentLogInfo component, ArrayList<String> logs) {
        ArrayList<Log> result = new ArrayList<>();
        for (String log : logs) {
            try {
                result.add(new Log(component, log));
            } catch (ParseException e) {
                LOGGER.error("cannot read this log:" + log);
            }
        }
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

    public String getJson() {
        Gson gson = new Gson();
        return gson.toJson(this);
    }
}
