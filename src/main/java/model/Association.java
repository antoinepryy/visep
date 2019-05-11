package model;

import java.io.Serializable;

public class Association implements Serializable {
    private int id;
    private String name;
    private String description;
    private String recruitment;

    public Association() {

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
}