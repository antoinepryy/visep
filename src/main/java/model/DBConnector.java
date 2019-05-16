package model;

import java.io.PrintWriter;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DBConnector {
    static Connection databaseConn = null;
    static PreparedStatement databasePrepareStat = null;

    public static void main(String[] argv) {

        try {
            makeJDBCConnection();

            //addDataUserToDB("Antoine", "Perry", "Pass", 12345, "antoine@gmail.com");
            //addDataUserToDB("Vincent", "Pescio", "Pass", 10857, "vincent@gmail.com");
            //addDataAssociationToDB("IsePorc","On adore manger comme des porcs","Uniquement des gros mangeurs wanted");
            //addDataEventToDB("Gros event IsePorc",8);
            User usr = new User("test", "test", "test", 1, "test", false);
            saveUser(usr);
            getUsersFromDB();

            databasePrepareStat.close();
            databaseConn.close(); // connection close

        } catch (SQLException e) {

            e.printStackTrace();
        }
    }

    private static void makeJDBCConnection() {

        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            return;
        }

        try {
            // DriverManager: The basic service for managing a set of JDBC drivers.
            databaseConn = DriverManager.getConnection("jdbc:mysql://localhost:3306/visep", "root", "");
            if (databaseConn != null) {
            } else {
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return;
        }

    }

    public static void addDataUserToDB(String firstName, String lastName, String password, int code, String mail) {

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
        } catch (

                SQLException e) {
            e.printStackTrace();
        }
    }

    private static void getUsersFromDB() {

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
            makeJDBCConnection();

            try {
                String insertQueryStatement = "INSERT INTO user(first_name,last_name,password,code,mail,is_admin)  VALUES (?,?,?,?,?,?)";

                databasePrepareStat = databaseConn.prepareStatement(insertQueryStatement);
                databasePrepareStat.setString(1, usr.getFirstName());
                databasePrepareStat.setString(2, usr.getLastName());
                databasePrepareStat.setString(3, usr.getPassword());
                databasePrepareStat.setInt(4, usr.getCode());
                databasePrepareStat.setString(5, usr.getEmail());
                databasePrepareStat.setBoolean(6, usr.getAdmin());

                databasePrepareStat.executeUpdate();
            } catch (

                    SQLException e) {e.printStackTrace();
            }



            databasePrepareStat.close();
            databaseConn.close(); // connection close

        } catch (SQLException e) {

            e.printStackTrace();
        }

    }
    public static String checkIfUserExist(String uname, String pw) {
        try {
            makeJDBCConnection();

            // MySQL Select Query Tutorial
            String getQueryStatement = "SELECT * FROM user WHERE code = ? AND password = ? ";

            databasePrepareStat = databaseConn.prepareStatement(getQueryStatement);


            databasePrepareStat.setInt(1, Integer.parseInt(uname));
            databasePrepareStat.setString(2 ,pw);

            // Execute the Query, and get a java ResultSet
            ResultSet rs = databasePrepareStat.executeQuery();

            // Let's iterate through the java ResultSet
            System.out.println(rs);
            String cd = null;
            while (rs.next()){
                cd = rs.getString("code");
                System.out.println(rs.getString("password"));
            }
            return cd;

        } catch (

                SQLException e) {
            e.printStackTrace();
            System.out.println(e);

            return null;
        }


    }

    public static void saveAssociation(Association association){

            try {
                String insertQueryStatement = "INSERT INTO association(admin_id, name, description, recruitment)  VALUES (?,?,?,?)";

                databasePrepareStat = databaseConn.prepareStatement(insertQueryStatement);
                databasePrepareStat.setInt(1, 0);
                databasePrepareStat.setString(2, association.getName());
                databasePrepareStat.setString(3, association.getDescription());
                databasePrepareStat.setString(4, association.getRecruitment());


                databasePrepareStat.executeUpdate();
            } catch (

                    SQLException e) {e.printStackTrace();
            }



    }

    public static Boolean isAdmin(String code) {
        try {
            makeJDBCConnection();
            String getQueryStatement = "SELECT is_admin FROM user WHERE code = ?";
            databasePrepareStat = databaseConn.prepareStatement(getQueryStatement);
            databasePrepareStat.setInt(1, Integer.parseInt(code));
            ResultSet rs = databasePrepareStat.executeQuery();
            rs.next();
            return rs.getBoolean("is_admin");
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static List<Association> getAssociationsFromDB() {
        try {
            makeJDBCConnection();
            String getQueryStatement = "SELECT * FROM association LEFT JOIN user ON association.admin_id = user.id";
            databasePrepareStat = databaseConn.prepareStatement(getQueryStatement);
            ResultSet rs = databasePrepareStat.executeQuery();
            List<Association> associations = new ArrayList<>();
            while (rs.next()) {
                User admin = new User(rs.getString("first_name"), rs.getString("last_name"), null, rs.getInt("code"), null, null);
                Association association = new Association(rs.getInt("id"), rs.getString("name"), rs.getString("description"), rs.getString("recruitment"), admin);
                associations.add(association);
            }
            return associations;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static Association getAssociationById(int id) {
        try {
            makeJDBCConnection();
            String getQueryStatement = "SELECT * FROM association LEFT JOIN user ON association.admin_id = user.id WHERE association.id = ? ";
            databasePrepareStat = databaseConn.prepareStatement(getQueryStatement);
            databasePrepareStat.setInt(1, id);
            ResultSet rs = databasePrepareStat.executeQuery();
            Association association;
            User admin;
            rs.next();
            admin = new User(rs.getString("first_name"), rs.getString("last_name"), null, rs.getInt("code"), null, null);
            association = new Association(rs.getString("name"), rs.getString("description"), rs.getString("recruitment"), admin);
            return association;


        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static List<User> getVisepAdmins() {
        try {
            makeJDBCConnection();
            String getQueryStatement = "SELECT first_name, last_name, code FROM user WHERE is_admin = 1";
            databasePrepareStat = databaseConn.prepareStatement(getQueryStatement);
            ResultSet rs = databasePrepareStat.executeQuery();
            List<User> visepAdmins = new ArrayList<>();
            while (rs.next()) {
                String fName = rs.getString("first_name");
                String lName = rs.getString("last_name");
                int code = rs.getInt("code");
                User user = new User(fName, lName, null, code, null, true);
                visepAdmins.add(user);
            }
            return visepAdmins;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static void addVisepAdmin(String fName, String lName) {
        try {
            makeJDBCConnection();
            String updateQueryStatement = "UPDATE user SET is_admin = 1 WHERE first_name = ? and last_name = ?";
            databasePrepareStat = databaseConn.prepareStatement(updateQueryStatement);
            databasePrepareStat.setString(1, fName);
            databasePrepareStat.setString(2, lName);
            databasePrepareStat.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void deleteVisepAdmin(int code) {
        try {
            makeJDBCConnection();
            String updateQueryStatement = "UPDATE user SET is_admin = 0 WHERE code = ?";
            databasePrepareStat = databaseConn.prepareStatement(updateQueryStatement);
            databasePrepareStat.setInt(1, code);
            databasePrepareStat.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void changeAdminAsso(String assoName, String fName, String lName) {
        try {
            makeJDBCConnection();
            String getQueryStatement = "SELECT id FROM user WHERE first_name = ? AND last_name = ?";
            databasePrepareStat = databaseConn.prepareStatement(getQueryStatement);
            databasePrepareStat.setString(1, fName);
            databasePrepareStat.setString(2, lName);
            ResultSet rs = databasePrepareStat.executeQuery();
            int id;
            if (rs.next()) {
                id = rs.getInt("id");
            }
            else {
                id = 0;
            }
            String updateQueryStatement = "UPDATE association SET admin_id = ? WHERE name = ?";
            databasePrepareStat = databaseConn.prepareStatement(updateQueryStatement);
            databasePrepareStat.setInt(1, id);
            databasePrepareStat.setString(2, assoName);
            databasePrepareStat.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void deleteAsso(String name) {
        try {
            makeJDBCConnection();
            String deleteQueryStatement = "DELETE FROM association WHERE name = ?";
            databasePrepareStat = databaseConn.prepareStatement(deleteQueryStatement);
            databasePrepareStat.setString(1, name);
            databasePrepareStat.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static Association getInfosAsso(String nameAsso) {
        try {
            makeJDBCConnection();
            String getQueryStatement = "SELECT * FROM association WHERE association.name = ?";
            databasePrepareStat = databaseConn.prepareStatement(getQueryStatement);
            databasePrepareStat.setString(1, nameAsso);
            ResultSet rs = databasePrepareStat.executeQuery();
            if (rs.next()) {
                Association association = new Association(rs.getString("name"), rs.getString("description"), rs.getString("recruitment"), null);
                return association;
            }
            return null;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static List<User> getMembersAsso(String nameAsso) {
        try {
            makeJDBCConnection();
            String getQueryStatement = "SELECT first_name, last_name, code FROM user INNER JOIN membership ON user.id = membership.user_id RIGHT JOIN association ON membership.association_id = association.id WHERE association.name = ?";
            databasePrepareStat = databaseConn.prepareStatement(getQueryStatement);
            databasePrepareStat.setString(1, nameAsso);
            ResultSet rs = databasePrepareStat.executeQuery();
            List<User> members = new ArrayList<>();
            while(rs.next()) {
                User member = new User(rs.getString("first_name"), rs.getString("last_name"), null, rs.getInt("code"), null, null);
                members.add(member);
            }
            return members;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static Boolean isAssoMember(String assoName, int userCode) {
        try {
            makeJDBCConnection();
            String getQueryStatement = "SELECT user.id FROM user INNER JOIN membership ON user.id = membership.user_id INNER JOIN association ON membership.association_id = association.id WHERE user.code = ? AND association.name = ?";
            databasePrepareStat = databaseConn.prepareStatement(getQueryStatement);
            databasePrepareStat.setInt(1, userCode);
            databasePrepareStat.setString(2,assoName);
            ResultSet rs = databasePrepareStat.executeQuery();
            if (rs.next()) {
                return true;
            }
            return false;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static Boolean isAssoAdmin(String assoName, int userCode) {
        try {
            makeJDBCConnection();
            String getQueryStatement = "SELECT user.code FROM association INNER JOIN user ON association.admin_id = user.id WHERE association.name = ?";
            databasePrepareStat = databaseConn.prepareStatement(getQueryStatement);
            databasePrepareStat.setString(1,assoName);
            ResultSet rs = databasePrepareStat.executeQuery();
            if (rs.next()) {
                if (rs.getInt("code") == userCode) {
                    return true;
                }
            }
            return false;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static void updateAssoInfos(Association association) {
        try {
            makeJDBCConnection();
            String updateQueryStatement = "UPDATE association SET description = ?, recruitment = ? WHERE name = ?";
            databasePrepareStat = databaseConn.prepareStatement(updateQueryStatement);
            databasePrepareStat.setString(1,association.getDescription());
            databasePrepareStat.setString(2,association.getRecruitment());
            databasePrepareStat.setString(3,association.getName());
            databasePrepareStat.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void deleteAssoMember(String assoName, int memberCode) {
        try {
            makeJDBCConnection();
            String getQueryStatement = "SELECT id FROM user WHERE code = ?";
            databasePrepareStat = databaseConn.prepareStatement(getQueryStatement);
            databasePrepareStat.setInt(1, memberCode);
            ResultSet rs = databasePrepareStat.executeQuery();
            rs.next();
            int user_id = rs.getInt("id");
            getQueryStatement = "SELECT id FROM association WHERE name = ?";
            databasePrepareStat = databaseConn.prepareStatement(getQueryStatement);
            databasePrepareStat.setString(1, assoName);
            rs = databasePrepareStat.executeQuery();
            rs.next();
            int association_id = rs.getInt("id");
            String deleteQueryStatement = "DELETE FROM membership WHERE user_id = ? AND association_id = ?";
            databasePrepareStat = databaseConn.prepareStatement(deleteQueryStatement);
            databasePrepareStat.setInt(1, user_id);
            databasePrepareStat.setInt(2, association_id);
            databasePrepareStat.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void addAssoMember(String assoName, String fName, String lName) {
        try {
            makeJDBCConnection();
            String getQueryStatement = "SELECT id FROM user WHERE first_name = ? AND last_name = ?";
            databasePrepareStat = databaseConn.prepareStatement(getQueryStatement);
            databasePrepareStat.setString(1, fName);
            databasePrepareStat.setString(2, lName);
            ResultSet rs = databasePrepareStat.executeQuery();
            if (rs.next()) {
                int user_id = rs.getInt("id");
                getQueryStatement = "SELECT id FROM association WHERE name = ?";
                databasePrepareStat = databaseConn.prepareStatement(getQueryStatement);
                databasePrepareStat.setString(1, assoName);
                rs = databasePrepareStat.executeQuery();
                rs.next();
                int association_id = rs.getInt("id");
                String insertQueryStatement = "INSERT INTO membership(user_id, association_id) VALUES (?,?)";
                databasePrepareStat = databaseConn.prepareStatement(insertQueryStatement);
                databasePrepareStat.setInt(1, user_id);
                databasePrepareStat.setInt(2, association_id);
                databasePrepareStat.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}