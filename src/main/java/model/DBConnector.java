package model;

import java.sql.*;

public class DBConnector {
    static Connection databaseConn = null;
    static PreparedStatement databasePrepareStat = null;

    public static void main(String[] argv) {

        try {
            log("-------- Simple database Tutorial on how to make JDBC connection to MySQL DBConnector locally on macOS ------------");
            makeJDBCConnection();

            log("\n---------- Adding company 'database LLC' to DBConnector ----------");
            //addDataUserToDB("Antoine", "Perry", "Pass", 12345, "antoine@gmail.com");
            //addDataUserToDB("Vincent", "Pescio", "Pass", 10857, "vincent@gmail.com");
            //addDataAssociationToDB("IsePorc","On adore manger comme des porcs","Uniquement des gros mangeurs wanted");
            //addDataEventToDB("Gros event IsePorc",8);
            User usr = new User("test", "test", "test", 1, "test");
            saveUser(usr);
            log("\n---------- Let's get Data from DBConnector ----------");
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

    private static void addDataUserToDB(String firstName, String lastName, String password, int code, String mail) {

        try {
            String insertQueryStatement = "INSERT  INTO  user(first_name,last_name,password,code,mail)  VALUES  (?,?,?,?,?)";

            databasePrepareStat = databaseConn.prepareStatement(insertQueryStatement);
            databasePrepareStat.setString(1, firstName);
            databasePrepareStat.setString(2, lastName);
            databasePrepareStat.setString(3, password);
            databasePrepareStat.setInt(4, code);
            databasePrepareStat.setString(5, mail);
            

            // execute insert SQL statement
            databasePrepareStat.executeUpdate();
            log(firstName + " added successfully");
        } catch (

                SQLException e) {
            e.printStackTrace();
        }
    }

    private static void addDataAssociationToDB(String name, String description, String recruitment) {

        try {
            String insertQueryStatement = "INSERT  INTO  association(name,description,recruitment)  VALUES  (?,?,?)";

            databasePrepareStat = databaseConn.prepareStatement(insertQueryStatement);
            databasePrepareStat.setString(1, name);
            databasePrepareStat.setString(2, description);
            databasePrepareStat.setString(3, recruitment);


            // execute insert SQL statement
            databasePrepareStat.executeUpdate();
            log(name + " added successfully");
        } catch (

                SQLException e) {
            e.printStackTrace();
        }
    }

    private static void addDataEventToDB(String description, int associationId) {

        try {
            String insertQueryStatement = "INSERT  INTO  event(date_event,description,association_id)  VALUES  (?,?,?)";
            int year = new java.util.Date().getYear();
            int month = new java.util.Date().getMonth();
            int day = new java.util.Date().getDay();
            java.sql.Date today = new Date(year, month, day);
            databasePrepareStat = databaseConn.prepareStatement(insertQueryStatement);
            databasePrepareStat.setDate(1, today);
            databasePrepareStat.setString(2, description);
            databasePrepareStat.setInt(3, associationId);


            // execute insert SQL statement
            databasePrepareStat.executeUpdate();
            log("date" + " added successfully");
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

    public static void saveUser(User usr){
        try {
            String insertQueryStatement = "INSERT INTO user(first_name,last_name,password,code,mail)  VALUES (?,?,?,?,?)";

            databasePrepareStat = databaseConn.prepareStatement(insertQueryStatement);
            databasePrepareStat.setString(1, usr.getFirstName());
            databasePrepareStat.setString(2, usr.getLastName());
            databasePrepareStat.setString(3, usr.getPassword());
            databasePrepareStat.setInt(4, 1);
            databasePrepareStat.setString(5, usr.getEmail());

            databasePrepareStat.executeUpdate();
        } catch (

                SQLException e) {e.printStackTrace();
        }
    }
}
