package ir.sahab.sahabino.rulesEvaluator.Rules;

import ir.sahab.sahabino.utility.Log;

public class LogSqlService {
    private final static String TABLE_NAME = "Notification";
    private final static String RULE_NAME_KEY = "rule_name";
    private final static String SYMBOL_KEY = "symbol";
    private final static String CURRENT_PRICE_KEY = "current_price";
    private final static String OPEN_TIME_KEY = "open_time";

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
                + SYMBOL_KEY + "  VARCHAR(255) NOT NULL, "
                + CURRENT_PRICE_KEY + " DOUBLE, "
                + OPEN_TIME_KEY +" BIGINT )";
    }
    public static String getSelectAllQueryStringStatement() {
        return "SELECT "+ getSQLFieldsStatement() +" FROM " +TABLE_NAME;
    }
    public static void setParameters(Notification not, PreparedStatement pstmt) throws SQLException {
        pstmt.setString(1,not.getRuleName());
        pstmt.setString(2,not.getSymbol());
        pstmt.setDouble(3,not.getCurrentPrice());
        pstmt.setLong(4,not.getOpenTime());
    }
    private static String getSQLFieldsStatement() {
        return RULE_NAME_KEY+", "+SYMBOL_KEY+", "+CURRENT_PRICE_KEY+", "+ OPEN_TIME_KEY;
    }
}
