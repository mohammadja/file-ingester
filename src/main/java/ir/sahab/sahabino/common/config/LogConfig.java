package ir.sahab.sahabino.common.config;

public class LogConfig {
    public static final String LOG_PATTERN_STRING =
            "(?<date>\\d{4}-\\d{2}-\\d{2} \\d{2}:\\d{2}:\\d{2},\\d+) " +
            "\\[(?<threadName>\\w+)]" +
            " (?<logType>[\\w.]+) (?<className>[\\w.]+) – (?<message>.*)$";

    public static final String LOG_DATE_PATTERN = "yyyy-MM-dd HH:mm:ss,SSS";
    public static final String COMPONENT_TIME_FORMAT = "yyyy_MM_dd-HH_mm_ss";
    public static final String COMPONENT_PATTERN_STRING =
            "(?<componentName>.*)-" +
            "(?<date>\\d{4}_\\d{2}_\\d{2}-\\d{2}_\\d{2}_\\d{2})" +
            ".log$";
}
