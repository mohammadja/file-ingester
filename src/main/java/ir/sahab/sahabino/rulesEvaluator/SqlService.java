package ir.sahab.sahabino.rulesEvaluator;

import ir.sahab.sahabino.rulesEvaluator.Rules.SQLRecord;
import ir.sahab.sahabino.utility.Log;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class LogSqlService {
    private final static String TABLE_NAME = "Notification";
    private final static String RULE_NAME_KEY = "rule_name";
    private final static String LOG_JSON_KEY = "log_json";
    private final static String Rule_MESSAGE = "rule_message";


    public static String getInsertAllQueryStatement() {
        return "INSERT INTO " + Log.class.getSimpleName()
                + "("+ getSQLFieldsStatement()+") "
                + "VALUES(?,?,?)";
    }
    public static String createTableStatement() {
        return "CREATE TABLE IF NOT EXISTS "
                + Log.class.getSimpleName() + " ( "
                + "id INT AUTO_INCREMENT PRIMARY KEY, "
                + RULE_NAME_KEY +"  VARCHAR(255) NOT NULL, "
                + LOG_JSON_KEY + "  VARCHAR(255) NOT NULL, "
                + Rule_MESSAGE + " VARCHAR(255) NOT NULL)";
    }
    public static String getSelectAllQueryStringStatement() {
        return "SELECT "+ getSQLFieldsStatement() +" FROM " + TABLE_NAME;
    }
    public static void setParameters(SQLRecord not, PreparedStatement pstmt) throws SQLException {
        pstmt.setString(1,not.getRuleName());
        pstmt.setString(2,not.getLogJson());
        pstmt.setString(3,not.getRuleMessage());
    }
    private static String getSQLFieldsStatement() {
        return RULE_NAME_KEY + ", " + LOG_JSON_KEY + ", " + Rule_MESSAGE ;
    }
}
