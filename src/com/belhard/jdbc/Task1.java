package com.belhard.jdbc;

import com.belhard.jdbc.util.SqlUtil;

import java.sql.SQLException;

public class Task1 {

    public static void main(String[] args) {
        try {
            System.out.println(SqlUtil.getFilms(2005, 2007));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
