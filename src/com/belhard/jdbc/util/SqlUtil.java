package com.belhard.jdbc.util;

import java.sql.*;

public class SqlUtil {
    private static final String URL = "jdbc:postgres//localhost:5432/dvd_rental";
    private static final String USER = "postgres";
    private static final String PASSWD = "root";
    private static Connection connection;

    static {
        try {
            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection(URL, USER, PASSWD);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static String getTableByQuery(String columnName, String tableName) throws SQLException {
        String queryMask = "SELECT ? FROM ?";
        PreparedStatement statement = connection.prepareStatement(queryMask);
        statement.setString(1, columnName);
        statement.setString(2, tableName);
        int result = statement.executeUpdate();
        return String.valueOf(result);
    }
}
