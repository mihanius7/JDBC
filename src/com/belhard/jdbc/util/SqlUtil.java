package com.belhard.jdbc.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

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

    public static List<City> getCities() throws SQLException {
        initConnection();
        String queryMask = "SELECT city, country FROM city \n" +
                "JOIN country ON city.country_id = country.country_id ORDER BY country";
        PreparedStatement statement = connection.prepareStatement(queryMask);
        ResultSet resultSet = statement.executeQuery();
//        StringBuilder output = new StringBuilder(String.format(" № %24.24s %16.16s\n", "City", "Country"));
        int i = 0;
        List<City> cities = new ArrayList<>(0);
        City city;
        while (resultSet.next()) {
            city = new City();
            city.setName(resultSet.getString("city"));
            city.setCountry(resultSet.getString("country"));
            cities.add(city);
        }
        connection.close();
        return cities;
    }

    public static class City {
        String name;
        String country;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getCountry() {
            return country;
        }

        public void setCountry(String country) {
            this.country = country;
        }

        @Override
        public String toString() {
            return String.format("%24.24s %20.20s", name, country);
        }
    }
}