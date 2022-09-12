package ir.sahab.sahabino.common.database;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.util.ArrayList;


public class MySqlHandler {
    private final static Logger LOGGER = LoggerFactory.getLogger(MySqlHandler.class.getName());
    private final String databaseURL;
    private final String user;
    private final String password;
    private Connection connection;
    private Statement statement;
    private final String databaseName;

    public MySqlHandler(String databaseURL, String user, String password, String databaseName) {
        this.databaseURL = databaseURL;
        this.user = user;
        this.password = password;
        this.databaseName = databaseName;
        connectToMySQL();
        createStatement();
        createDatabase();
        initDatabase();
        createTables();
    }

    public void insert(SQLRecord record) {
        try {
            PreparedStatement prepareStatement = connection.prepareStatement(
                    SqlService.getInsertAllQueryStatement(),
                    Statement.RETURN_GENERATED_KEYS);
            SqlService.setParameters(record, prepareStatement);
            int rowAffected = prepareStatement.executeUpdate();
            LOGGER.info("record inserted; " + rowAffected + "row(s) changed");

        } catch (SQLException e) {
            LOGGER.error("Error while inserting. " + e);
        }
    }

    public ArrayList<SQLRecord> getLogList() {
        try {
            ResultSet resultSet = statement.executeQuery(SqlService.getSelectAllQueryStringStatement());
            return makeLogList(resultSet);
        } catch (SQLException e) {
            LOGGER.error("Error while get all Log List. " + e);
            return new ArrayList<>();
        }
    }

    private ArrayList<SQLRecord> makeLogList(ResultSet resultSet) throws SQLException {
        ArrayList<SQLRecord> records = new ArrayList<>();
        while (resultSet.next()) {
            records.add(SqlService.extractRecord(resultSet));
        }
        return records;
    }


    private void createTables() {
        try {
            statement.executeUpdate(SqlService.createTableStatement());
            String tableName = SQLRecord.class.getSimpleName();
            LOGGER.info("table " + tableName + " created");
        } catch (SQLException e) {
            LOGGER.error("Could not create table." + e);
            throw new RuntimeException(e);
        }
    }

    private void createStatement() {
        try {
            statement = connection.createStatement();
        } catch (SQLException e) {
            LOGGER.error("Could not get the statement." + e);
            throw new RuntimeException(e);
        }
    }

    private void initDatabase() {
        try {
            statement.executeUpdate("USE " + databaseName);
        } catch (SQLException e) {
            LOGGER.error("Could not use database." + e);
            throw new RuntimeException(e);
        }
    }

    private void createDatabase() {
        try {
            statement.executeUpdate("CREATE DATABASE IF NOT EXISTS " + databaseName);
        } catch (SQLException e) {
            LOGGER.error("Could not make database." + e);
            throw new RuntimeException(e);
        }
    }

    private void connectToMySQL() {
        try {
            connection = DriverManager.getConnection(databaseURL, user, password);
            LOGGER.info("Connected to database");
        } catch (SQLException e) {
            LOGGER.error("Could not connect to mysql" + e);
            throw new RuntimeException(e);
        }
    }
}
