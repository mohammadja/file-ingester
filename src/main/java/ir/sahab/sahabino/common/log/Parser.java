package ir.sahab.sahabino.common.log;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;



public class Parser {

    public static Date getDateFromString(String date, String timeFormat) throws ParseException {
        SimpleDateFormat formatter = new SimpleDateFormat(timeFormat);
        return formatter.parse(date);
    }
    public static Matcher getMatcher(String sampleLog, String patternString) throws ParseException {
        Pattern pattern = Pattern.compile(patternString);
        return getExceptionOrMatcher(sampleLog, pattern);
    }
    private static Matcher getExceptionOrMatcher(String sampleLog, Pattern pattern) throws ParseException {
        Matcher matcher = pattern.matcher(sampleLog);
        if(!matcher.find()) throw new ParseException("bad pattern format (ignore index)", 0);
        return matcher;
    }
}
