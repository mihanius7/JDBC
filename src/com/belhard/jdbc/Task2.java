package com.belhard.jdbc;

import com.belhard.jdbc.util.SqlUtil;

import java.sql.SQLException;

public class Task2 {
    public static void main(String[] args) {
        try {
            System.out.printf("%24.24s %20.20s\n", "City", "Country");
            SqlUtil.getCities().forEach(System.out::println);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
