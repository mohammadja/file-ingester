package ir.sahab.sahabino.common.log;

import java.util.Date;

import java.text.ParseException;
import java.util.regex.Matcher;


import static ir.sahab.sahabino.common.config.LogConfig.*;
import static ir.sahab.sahabino.common.log.Parser.getDateFromString;
import static ir.sahab.sahabino.common.log.Parser.getMatcher;


public class ComponentLogInfo {

    private final String componentName;
    private final Date date;

    public ComponentLogInfo(String fileName) throws ParseException {
        Matcher matcher = getMatcher(fileName, COMPONENT_PATTERN_STRING);
        this.componentName = matcher.group("componentName");
        this.date = getDateFromString(matcher.group("date"), COMPONENT_TIME_FORMAT);
    }
    public String getComponentName() {
        return componentName;
    }

    public Date getDate() {
        return date;
    }
}
