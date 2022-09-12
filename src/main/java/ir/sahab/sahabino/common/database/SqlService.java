package ir.sahab.sahabino.common.database;



import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SqlService {
    private final static String RULE_NAME_KEY = "rule_name";
    private final static String LOG_JSON_KEY = "log_json";
    private final static String Rule_MESSAGE = "rule_message";

    public static SQLRecord extractRecord(ResultSet resultSet) throws SQLException {
        String ruleName = resultSet.getString(RULE_NAME_KEY);
        String logJson = resultSet.getString(LOG_JSON_KEY);
        String ruleMessage = resultSet.getString(Rule_MESSAGE);
        return new SQLRecord(ruleName, logJson, ruleMessage);
    }

    public static String getInsertAllQueryStatement() {
        return "INSERT INTO " + SQLRecord.class.getSimpleName()
                + "(" + getSQLFieldsStatement() + ") "
                + "VALUES(?,?,?)";
    }

    public static String createTableStatement() {
        return "CREATE TABLE IF NOT EXISTS "
                + SQLRecord.class.getSimpleName() + " ( "
                + "id INT AUTO_INCREMENT PRIMARY KEY, "
                + RULE_NAME_KEY + "  VARCHAR(255) NOT NULL, "
                + LOG_JSON_KEY + "  VARCHAR(255) NOT NULL, "
                + Rule_MESSAGE + " VARCHAR(255) NOT NULL)";
    }

    public static String getSelectAllQueryStringStatement() {
        return "SELECT " + getSQLFieldsStatement() + " FROM " + SQLRecord.class.getSimpleName();
    }

    public static void setParameters(SQLRecord not, PreparedStatement preparedStatement) throws SQLException {
        preparedStatement.setString(1, not.getRuleName());
        preparedStatement.setString(2, not.getLogJson());
        preparedStatement.setString(3, not.getRuleMessage());
    }

    private static String getSQLFieldsStatement() {
        return RULE_NAME_KEY + ", " + LOG_JSON_KEY + ", " + Rule_MESSAGE;
    }
}