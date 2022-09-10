package ir.sahab.sahabino.rulesEvaluator.Rules;
import ir.sahab.sahabino.rulesEvaluator.Rule;
import ir.sahab.sahabino.utility.Log;

public class topLimitRule implements Rule {
    private int limit;
    private transient int now = 0;
    @Override
    public void update(Log log) {
        now ++;
    }

    @Override
    public boolean checkCondition() {
        return (limit <= now);
    }

    @Override
    public String getRecord() {
        return "you reached the limit";
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }
}
