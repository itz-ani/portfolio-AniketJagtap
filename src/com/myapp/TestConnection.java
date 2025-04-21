package com.myapp;

import java.sql.*;

public class TestConnection {
    public static void main(String[] args) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/portfolio?useSSL=false&serverTimezone=UTC",
                "root", "your_password"
            );
            System.out.println("✅ Connected to database!");
            con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
