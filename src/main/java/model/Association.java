package model;

import sun.security.pkcs11.Secmod;

import java.io.Serializable;
import java.util.List;

public class Association implements Serializable {
    private int id;
    private String name;
    private String description;
    private String recruitment;
    private User admin;

    public Association(String name, String description, String recruitment, User admin) {
        this.name = name;
        this.description = description;
        this.recruitment = recruitment;
        this.admin = admin;
    }

    public Association(int id , String name, String description, String recruitment, User admin) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.recruitment = recruitment;
        this.admin = admin;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getRecruitment() {
        return recruitment;
    }

    public void setRecruitment(String recruitment) {
        this.recruitment = recruitment;
    }

    public User getAdmin() {
        return this.admin;
    }

    public void persist(){
        DBConnector.saveAssociation(this);
    }

    public static void changeAdmin(String assoName, String adminFirstName, String adminLastName) {
        DBConnector.changeAdminAsso(assoName, adminFirstName, adminLastName);
    }

    public static void delete(String name) {
        DBConnector.deleteAsso(name);
    }

    public static Association getInfos(String nameAsso) {
        return DBConnector.getInfosAsso(nameAsso);
    }

    public static List<User> getMembers(String nameAsso) {
        return DBConnector.getMembersAsso(nameAsso);
    }

    public Boolean isAdmin(int userCode) {
        return DBConnector.isAssoAdmin(this.name, userCode);
    }

    public void updateInfos() {
        DBConnector.updateAssoInfos(this);
    }

    public static List<Event> getEvents(String assoName) {
        return DBConnector.getEventsAsso(assoName);
    }
}
