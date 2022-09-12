package ir.sahab.sahabino.common.config;

import ir.sahab.sahabino.rulesEvaluator.Rules.RecentLogRule;
import ir.sahab.sahabino.rulesEvaluator.Rules.TopLimitRule;

import java.util.ArrayList;

public class RulesConfig {
    public static final ArrayList<Class> RULES = new ArrayList<>();
    static {
        RULES.add(TopLimitRule.class);
        RULES.add(RecentLogRule.class);
    }

    public static final boolean DELETE_AFTER_READ = false;
}
