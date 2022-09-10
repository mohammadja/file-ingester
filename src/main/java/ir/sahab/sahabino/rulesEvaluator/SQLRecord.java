package ir.sahab.sahabino.rulesEvaluator.Rules;

public class SQLRecord {
    private String ruleName;
    private String logJson;
    private String ruleMessage;

    public SQLRecord(String ruleName, String logJson, String ruleMessage) {
        this.logJson = logJson;
        this.ruleMessage = ruleMessage;
        this.ruleName = ruleName;
    }

    public String getRuleName() {
        return ruleName;
    }

    public void setRuleName(String ruleName) {
        this.ruleName = ruleName;
    }

    public String getLogJson() {
        return logJson;
    }

    public void setLogJson(String logJson) {
        this.logJson = logJson;
    }

    public String getRuleMessage() {
        return ruleMessage;
    }

    public void setRuleMessage(String ruleMessage) {
        this.ruleMessage = ruleMessage;
    }
}
