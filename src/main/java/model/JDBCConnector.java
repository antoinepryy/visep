package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class JDBCConnector {
    static Connection databaseConn = null;
    static PreparedStatement databasePrepareStat = null;

    public static void main(String[] argv) {

        try {
            log("-------- Simple database Tutorial on how to make JDBC connection to MySQL DB locally on macOS ------------");
            makeJDBCConnection();

            log("\n---------- Adding company 'database LLC' to DB ----------");
            addDataToDB("Antoine", "Pass");
            addDataToDB("Quentin", "Michel");

            log("\n---------- Let's get Data from DB ----------");
            getDataFromDB();

            databasePrepareStat.close();
            databaseConn.close(); // connection close

        } catch (SQLException e) {

            e.printStackTrace();
        }
    }

    private static void makeJDBCConnection() {

        try {
            Class.forName("com.mysql.jdbc.Driver");
            log("Congrats - Seems your MySQL JDBC Driver Registered!");
        } catch (ClassNotFoundException e) {
            log("Sorry, couldn't found JDBC driver. Make sure you have added JDBC Maven Dependency Correctly");
            e.printStackTrace();
            return;
        }

        try {
            // DriverManager: The basic service for managing a set of JDBC drivers.
            databaseConn = DriverManager.getConnection("jdbc:mysql://localhost:3306/visep", "root", "");
            if (databaseConn != null) {
                log("Connection Successful! Enjoy. Now it's time to push data");
            } else {
                log("Failed to make connection!");
            }
        } catch (SQLException e) {
            log("MySQL Connection Failed!");
            e.printStackTrace();
            return;
        }

    }

    private static void addDataToDB(String firstName, String password) {

        try {
            String insertQueryStatement = "INSERT  INTO  user(first_name,password)  VALUES  (?,?)";

            databasePrepareStat = databaseConn.prepareStatement(insertQueryStatement);
            databasePrepareStat.setString(1, firstName);
            databasePrepareStat.setString(2, password);
            

            // execute insert SQL statement
            databasePrepareStat.executeUpdate();
            log(firstName + " added successfully");
        } catch (

                SQLException e) {
            e.printStackTrace();
        }
    }

    private static void getDataFromDB() {

        try {
            // MySQL Select Query Tutorial
            String getQueryStatement = "SELECT * FROM user";

            databasePrepareStat = databaseConn.prepareStatement(getQueryStatement);

            // Execute the Query, and get a java ResultSet
            ResultSet rs = databasePrepareStat.executeQuery();

            // Let's iterate through the java ResultSet
            while (rs.next()) {
                String name = rs.getString("first_name");
                String address = rs.getString("password");
                

                // Simply Print the results
                System.out.format("%s, %s\n", name,address);
            }

        } catch (

                SQLException e) {
            e.printStackTrace();
        }

    }

    // Simple log utility
    private static void log(String string) {
        System.out.println(string);

    }
}
