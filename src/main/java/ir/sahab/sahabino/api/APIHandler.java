package ir.sahab.sahabino.api;

import ir.sahab.sahabino.common.database.MySqlHandler;

import static ir.sahab.sahabino.common.config.DBConfig.*;
import static spark.Spark.get;
import com.google.gson.Gson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class APIHandler {
    private static final Logger logger = LoggerFactory.getLogger(APIHandler.class);
    static MySqlHandler sql = new MySqlHandler(DB_URL,DB_USER_NAME,DB_USER_PASS,DB_NAME);

    public static void main(String[] args) {
        logger.info("starting java spark web service on" + "http://localhost:4567/");
        get("/", (request, response) -> {
            response.type("application/json");
            return new Gson().toJson(
                    new StandardResponse(StatusResponse.SUCCESS,new Gson()
                            .toJsonTree(sql.getLogList())));
        });
    }
}
