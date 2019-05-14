package model;

import sun.security.pkcs11.Secmod;

import java.io.Serializable;

public class Association implements Serializable {
    private int id;
    private String name;
    private String description;
    private String recruitment;

    public Association(String name, String description, String recruitment) {
        this.name = name;
        this.description = description;
        this.recruitment = recruitment;
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

    public void persist(){
        DBConnector.saveAssociation(this);

    }
}
