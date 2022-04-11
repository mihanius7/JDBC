package com.belhard.jdbc.util;

import java.sql.*;

public class SqlUtil {
    private static final String URL = "localhost:5432";
    private static final String USER = "postgres";
    private static final String PASSWD = "root";
    private static Connection connection;

    static {
        try {
            connection = DriverManager.getConnection(URL, USER, PASSWD);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static String getTableByQuery(String columnName, String tableName) throws SQLException {
        String queryMask = "SELECT ? FROM ?";
        PreparedStatement statement = connection.prepareStatement(queryMask);
        statement.setString(1, columnName);
        statement.setString(2, tableName);
        int result = statement.executeUpdate();
        return null;
    }
}
