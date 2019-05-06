package model;

import java.io.Serializable;
import java.util.Date;

public class Event implements Serializable {
    private int id;
    private Date dateEvent;
    private String description;
    private int associationId;

    public Event(){

    }

    public int getId() {
        return id;
    }

    public Date getDateEvent() {
        return dateEvent;
    }

    public void setDateEvent(Date dateEvent) {
        this.dateEvent = dateEvent;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getAssociationId() {
        return associationId;
    }

    public void setAssociationId(int associationId) {
        this.associationId = associationId;
    }
}
