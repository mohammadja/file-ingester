package ir.sahab.sahabino.rulesEvaluator;


import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import ir.sahab.sahabino.utility.Log;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class RuleManager{
    static ArrayList<Rule> rules = new ArrayList<>();
    static {
        JsonParser jsonParser = new JsonParser();
        try {
            JsonElement rulesJsonElament = jsonParser.parse(new FileReader(Config.RULE_FILE_ADDRESS));
            JsonArray rulesJsonArray = rulesJsonElament.getAsJsonArray();
            addRules(rulesJsonArray);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static void addRules(JsonArray rulesJson) {
        for(JsonElement ruleElement:rulesJson)
            addRule(ruleElement);

    }

    private static void addRule(JsonElement ruleElement) {
        Gson gson = new Gson();
        String type = ruleElement.getAsJsonObject().get("type").getAsString();
        String rule = ruleElement.getAsJsonObject().get("data").toString();
        Rule newRule = (Rule) gson.fromJson(rule, findRuleClass(type));
        rules.add(newRule);
    }

    private static Class findRuleClass(String type) {
        for(Class ruleType:Config.RULES){
            if(type.equals(ruleType.getName()))
                return ruleType;
        }
        throw new RuntimeException("Rule Class Not found");
    }
    void update(Log log){
        for(Rule rule:rules)
            rule.update(log);
    }
    void send(){
        // TODO: 9/10/22
    }
}