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
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private static void initConnection() throws SQLException {
            connection = DriverManager.getConnection(URL, USER, PASSWD);
    }

    public static String getFilms(int yearMin, int yearMax) throws SQLException {
        initConnection();
        String queryMask = "SELECT title, release_year, rating, length FROM film \n" +
                "WHERE release_year >= ? AND release_year <= ? ORDER BY length DESC;\n";
        PreparedStatement statement = connection.prepareStatement(queryMask);
        statement.setInt(1, yearMin);
        statement.setInt(2, yearMax);
        System.out.println("Executing query: " + statement + "\n");
        ResultSet resultSet = statement.executeQuery();
        StringBuilder output = new StringBuilder(String.format("%32.32s %6.6s %12.12s \n", "Title", "Year", "Duration, m"));
        while (resultSet.next()) {
            output.append(String.format("%32.32s %6d %12d\n",
                    resultSet.getString("title"),
                    resultSet.getInt("release_year"),
                    resultSet.getInt("length")));
        }
        connection.close();
        return output.toString();
    }

    public static String getCountries() throws SQLException {
        initConnection();
        return "to be continued";
    }
}
