package ir.sahab.sahabino.api;

import ir.sahab.sahabino.rulesEvaluator.MySqlHandler;
import static spark.Spark.get;
import com.google.gson.Gson;


public class APIHandler {
    static MySqlHandler sql = new MySqlHandler("jdbc:mysql://localhost/","root","root","Notification");
    public static void main(String[] args) {
        get("/", (request, response) -> {
            response.type("application/json");
            return new Gson().toJson(
                    new StandardResponse(StatusResponse.SUCCESS,new Gson()
                            .toJsonTree(sql.getLogList())));
        });
    }
}
