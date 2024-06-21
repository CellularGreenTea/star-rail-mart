package net.cgt.service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DB {
    public static Connection connect() {
        try {
            Connection con = DriverManager.getConnection(DBConfig.getUrl(), DBConfig.getUsername(), DBConfig.getPassword());
            System.out.println("Connected to the database");
            return con;
        
        }
        catch (SQLException error) {
            System.out.println(error.getMessage());
            return null;
        }
    }                  
}
