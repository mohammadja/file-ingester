package ir.sahab.sahabino.rulesEvaluator.Rules;
import ir.sahab.sahabino.rulesEvaluator.Rule;
import ir.sahab.sahabino.rulesEvaluator.SQLRecord;
import ir.sahab.sahabino.utility.Log;

public class TopLimitRule implements Rule {
    private int limit;
    private transient int now = 0;
    @Override
    public SQLRecord update(Log log) {
        now ++;
        if(now == limit)
            return new SQLRecord(this.getClass().getName(), log.getJson(), "you reached the limit");
        return null;
    }
    public void setLimit(int limit) {
        this.limit = limit;
    }

    public static void main(String[] args) {
        System.out.println(TopLimitRule.class.getName());
    }
}