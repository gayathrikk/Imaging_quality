package com.test.Imaging_Quality;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.testng.annotations.Test;

public class Datatest {
    @Test
    public void testDB() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        System.out.println("Driver loaded");

        String url = "jdbc:mysql://apollo2.humanbrain.in:3306/HBA_V2";
        String username = "root";
        String password = "Health#123";
        Connection connection = DriverManager.getConnection(url, username, password);
        System.out.println("MYSQL database connected");

        // Call the method to execute and print the query
        executeAndPrintQuery(connection);
        
        // Close the connection
        connection.close();
    }

    private void executeAndPrintQuery(Connection connection) throws SQLException {
        executeAndPrintQuery(connection, "reImage=1");
        executeAndPrintQuery(connection, "reCoverslip=1");
        executeAndPrintQuery(connection, "badStain=1");
        executeAndPrintQuery(connection, "misLabeled=1");
        executeAndPrintQuery(connection, "isDuplicated=1");
        executeAndPrintQuery(connection, "goDelete=1");
        
    }

    private void executeAndPrintQuery(Connection connection, String condition) throws SQLException {
        Statement statement = connection.createStatement();
        String query = "SELECT COUNT(*) FROM `huron_slideinfo` WHERE " + condition;

        ResultSet resultSet = statement.executeQuery(query);

        // Process the result
        if (resultSet.next()) {
            int count = resultSet.getInt(1);
            System.out.println("Count of records where " + condition + ": " + count);
        }

        // Close the result set and statement
        resultSet.close();
        statement.close();
    }

}
