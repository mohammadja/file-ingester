package ir.sahab.sahabino.rulesEvaluator;


import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;


import static ir.sahab.sahabino.common.config.Config.RULES;
import static ir.sahab.sahabino.common.config.Config.RULE_FILE_ADDRESS;

public class RulePool {
    static private final Logger LOGGER = LoggerFactory.getLogger(RulePool.class);
    static RulePool singletonObject = null;
    private final ArrayList<Rule> rules = new ArrayList<>();
    private void addRules(JsonArray rulesJson) {
        for(JsonElement ruleElement:rulesJson)
            addRule(ruleElement);
    }

    private void addRule(JsonElement ruleElement) {
        Gson gson = new Gson();
        String type = ruleElement.getAsJsonObject().get("type").getAsString();
        String rule = ruleElement.getAsJsonObject().get("data").toString();
        Rule newRule = (Rule) gson.fromJson(rule, findRuleClass(type));
        rules.add(newRule);
    }
    private static Class findRuleClass(String type) {
        for(Class ruleType:RULES){
            if(type.equals(ruleType.getName()))
                return ruleType;
        }
        LOGGER.error("Rule Class Not found (please add  it ro config class)" + type);
        throw new RuntimeException("Rule Class Not found");
    }
    private RulePool(){
        JsonParser jsonParser = new JsonParser();
        try {
            JsonElement rulesJsonElement = jsonParser.parse(new FileReader(RULE_FILE_ADDRESS));
            JsonArray rulesJsonArray = rulesJsonElement.getAsJsonArray();
            addRules(rulesJsonArray);
        } catch (IOException e) {
            LOGGER.error("cannot load Rules");
        }
    }
    static public RulePool getInstance(){
        if(singletonObject == null)
            singletonObject = new RulePool();
        return singletonObject;
    }
    public ArrayList<Rule> getRules(){
        return rules;
    }
}