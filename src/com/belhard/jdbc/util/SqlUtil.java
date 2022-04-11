package com.belhard.jdbc.util;

import java.sql.*;

public class SqlUtil {
    private static final String URL = "jdbc:postgresql://localhost:5432/dvd_rental";
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

    public static String getFilms(int yearMin, int yearMax) throws SQLException {
        String queryMask = "SELECT title, release_year, rating, length FROM film \n" +
                "WHERE release_year >= ? AND release_year <= ? ORDER BY length DESC;";
        PreparedStatement statement = connection.prepareStatement(queryMask);
        statement.setInt(1, yearMin);
        statement.setInt(2, yearMax);
        System.out.println("Executing query: " + statement.toString());
        ResultSet resultSet = statement.executeQuery();
        String output = String.format("%32.32s %6.6s %12.12s \n", "Title", "Year", "Duration, m");
        while (resultSet.next()) {
            output += String.format("%32.32s %6d %12d\n",
                    resultSet.getString("title"),
                    resultSet.getInt("release_year"),
                    resultSet.getInt("length"));
        }
        return output;
    }
}
