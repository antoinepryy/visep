package model;

import sun.security.pkcs11.Secmod;

import java.io.Serializable;

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
}
