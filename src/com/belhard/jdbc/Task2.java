package com.belhard.jdbc;

import com.belhard.jdbc.util.SqlUtil;

import java.sql.SQLException;

public class Task2 {
    public static void main(String[] args) {
        try {
            System.out.println(SqlUtil.getCountries());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
