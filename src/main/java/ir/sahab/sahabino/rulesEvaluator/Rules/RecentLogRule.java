package ir.sahab.sahabino.rulesEvaluator.Rules;

import com.google.gson.Gson;
import ir.sahab.sahabino.common.database.SQLRecord;
import ir.sahab.sahabino.common.log.Log;
import ir.sahab.sahabino.rulesEvaluator.Rule;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;

public class RecentLogRule implements Rule {
    transient final static private Logger LOGGER = LoggerFactory.getLogger(RecentLogRule.class);
    transient final private Map<String, ArrayList<Log>> data = new TreeMap<>();
    private long timeLimit;
    private int logNumberLimit;
    @Override
    public SQLRecord update(Log log) {
        ArrayList<Log> nearDates = getNearDates(log);
        data.get(log.getComponent().getComponentName()).add(log);
        if(nearDates.size() < logNumberLimit)
            return null;
        return new SQLRecord(
                this.getClass().getName(),
                log.getJson(),nearDates.get(nearDates.size() - 1).getJson()
        );
    }


    private ArrayList<Log> getNearDates(Log log) {
        ArrayList<Log> dates = new ArrayList<>(getDates(log.getComponent().getComponentName()));
        dates.removeIf(log1 -> log1.getDate().getTime() > log.getDate().getTime());
        dates.removeIf(log1 -> log1.getDate().getTime() < log.getDate().getTime() - timeLimit);
        dates.sort(Comparator.comparing(Log::getDate));
        return dates;
    }

    private ArrayList<Log> getDates(String componentName) {
        if(!data.containsKey(componentName)) {
            ArrayList<Log> newDates = new ArrayList<>();
            data.put(componentName, newDates);
        }
        return data.get(componentName);
    }

    public void setTimeLimit(long timeLimit) {
        this.timeLimit = timeLimit;
    }

    public void setLogNumberLimit(int logNumberLimit) {
        this.logNumberLimit = logNumberLimit;
    }
}
