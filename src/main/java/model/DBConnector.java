package model;

import org.w3c.dom.stylesheets.MediaList;

import java.io.PrintWriter;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DBConnector {
    static Connection databaseConn = null;
    static PreparedStatement databasePrepareStat = null;



    private static void makeJDBCConnection() {

        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            return;
        }

        try {
            databaseConn = DriverManager.getConnection("jdbc:mysql://localhost:3306/visep?useUnicode=yes&characterEncoding=utf8", "root", "");
            if (databaseConn != null) {
            } else {
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return;
        }

    }


    public static List<User> getAllUsers(){
        try {
            String getQueryStatement = "SELECT * FROM user";
            databasePrepareStat = databaseConn.prepareStatement(getQueryStatement);
            ResultSet rs = databasePrepareStat.executeQuery();
            List<User> lst = new ArrayList<>();
            while (rs.next()) {
                lst.add(new User(rs.getInt("id"), rs.getString("first_name"), rs.getString("last_name"), null, rs.getInt("code"), null, null));

            }

            return lst;

        } catch (

                SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static void getUsersFromDB() {

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
            String cd = null;
            while (rs.next()){
                cd = rs.getString("code");
            }
            return cd;

        } catch (SQLException e) {
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
    public static String getAssociationDescription(Integer idAssociation) {
        try {
            makeJDBCConnection();

            // MySQL Select Query Tutorial
            String getQueryStatement = "SELECT description FROM association WHERE id = ? ";

            databasePrepareStat = databaseConn.prepareStatement(getQueryStatement);

            databasePrepareStat.setInt(1, idAssociation);

            // Execute the Query, and get a java ResultSet
            ResultSet rs = databasePrepareStat.executeQuery();

            // Let's iterate through the java ResultSet
            String descriptionAssociation = null;

            while (rs.next()){
                descriptionAssociation = rs.getString("description");
            }
            return descriptionAssociation;

        } catch (SQLException e) {
            e.printStackTrace();

            return "L'id de l'asso n'est pas bon";
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

    public static Integer getUserId(int code) {
        try {
            makeJDBCConnection();
            String getQueryStatement = "SELECT id FROM user WHERE code = ?";
            databasePrepareStat = databaseConn.prepareStatement(getQueryStatement);
            databasePrepareStat.setInt(1, code);
            ResultSet rs = databasePrepareStat.executeQuery();
            rs.next();
            int user_id = rs.getInt("id");
            return user_id;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static Integer getUserId(String fName, String lName) {
        try {
            makeJDBCConnection();
            String getQueryStatement = "SELECT id FROM user WHERE first_name = ? AND last_name = ?";
            databasePrepareStat = databaseConn.prepareStatement(getQueryStatement);
            databasePrepareStat.setString(1, fName);
            databasePrepareStat.setString(2, lName);
            ResultSet rs = databasePrepareStat.executeQuery();
            if (rs.next()) {
                int user_id = rs.getInt("id");
                return user_id;
            }
            return null;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static Integer getAssociationId(String assoName) {
        try {
            makeJDBCConnection();
            String getQueryStatement = "SELECT id FROM association WHERE name = ?";
            databasePrepareStat = databaseConn.prepareStatement(getQueryStatement);
            databasePrepareStat.setString(1, assoName);
            ResultSet rs = databasePrepareStat.executeQuery();
            rs.next();
            int association_id = rs.getInt("id");
            return association_id;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static void deleteAssoMember(String assoName, int memberCode) {
        try {
            int user_id = getUserId(memberCode);
            int association_id = getAssociationId(assoName);
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
            Integer user_id = getUserId(fName, lName);
            if (user_id != null) {
                int association_id = getAssociationId(assoName);
                String insertQueryStatement = "INSERT INTO membership (user_id, association_id) VALUES (?,?)";
                databasePrepareStat = databaseConn.prepareStatement(insertQueryStatement);
                databasePrepareStat.setInt(1, user_id);
                databasePrepareStat.setInt(2, association_id);
                databasePrepareStat.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static List<Event> getEventsAsso(String assoName) {
        try {
            makeJDBCConnection();
            String getQueryStatement = "SELECT event.id, date, event.description FROM event INNER JOIN association ON event.association_id = association.id WHERE association.name = ? AND event.date >= CURRENT_DATE ORDER BY event.date";
            databasePrepareStat = databaseConn.prepareStatement(getQueryStatement);
            databasePrepareStat.setString(1, assoName);
            ResultSet rs = databasePrepareStat.executeQuery();
            List<Event> events = new ArrayList<>();
            while (rs.next()) {
                Association association = new Association(assoName, null, null, null);
                Event event = new Event(rs.getInt("id"), rs.getDate("date"), rs.getString("description"), association);
                events.add(event);
            }
            return events;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static void deleteEvent(int id) {
        try {
            makeJDBCConnection();
            String deleteQueryStatement = "DELETE FROM event WHERE id = ?";
            databasePrepareStat = databaseConn.prepareStatement(deleteQueryStatement);
            databasePrepareStat.setInt(1, id);
            databasePrepareStat.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void createEvent(String assoName, Date date, String description) {
        try {
            makeJDBCConnection();
            int association_id = getAssociationId(assoName);
            String insertQueryStatement = "INSERT INTO event (date, description, association_id) VALUES (?,?,?)";
            databasePrepareStat = databaseConn.prepareStatement(insertQueryStatement);
            databasePrepareStat.setDate(1, date);
            databasePrepareStat.setString(2, description);
            databasePrepareStat.setInt(3, association_id);
            databasePrepareStat.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static List<Message> getAllMessagesReceivedByUser(int id){
        try {
            makeJDBCConnection();
            String getQueryStatement = "SELECT * FROM message WHERE id_recipient = ? OR id_sender = ? ORDER BY date DESC";
            databasePrepareStat = databaseConn.prepareStatement(getQueryStatement);
            databasePrepareStat.setInt(1, id);
            databasePrepareStat.setInt(2, id);
            ResultSet rs = databasePrepareStat.executeQuery();
            List<Message> messages = new ArrayList<>();
            while (rs.next()) {
                Message msg = new Message(rs.getInt("id"),rs.getInt("id_sender"), rs.getInt("id_recipient"), rs.getDate("date"), rs.getString("text"));
                messages.add(msg);

            }
            return messages;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static void sendMessage(int from, int to, String msg){
        try {
            makeJDBCConnection();
            String insertQueryStatement = "INSERT INTO message(id_sender, id_recipient, text) VALUE (?, ? ,?)";
            databasePrepareStat = databaseConn.prepareStatement(insertQueryStatement);
            databasePrepareStat.setInt(1, from);
            databasePrepareStat.setInt(2, to);
            databasePrepareStat.setString(3, msg);
            databasePrepareStat.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println(e);
        }
    }

    public static Boolean isMemberOfAsso(String assoName, String fName, String lName) {
        try {
            Integer user_id = getUserId(fName, lName);
            if (user_id != null) {
                int association_id = getAssociationId(assoName);
                String getQueryStatement = "SELECT id FROM membership WHERE user_id = ? AND association_id = ?";
                databasePrepareStat = databaseConn.prepareStatement(getQueryStatement);
                databasePrepareStat.setInt(1, user_id);
                databasePrepareStat.setInt(2, association_id);
                ResultSet rs = databasePrepareStat.executeQuery();
                if (rs.next()) {
                    return true;
                }
            }
            return false;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

}