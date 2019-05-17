package model;

import java.io.Serializable;
import java.sql.Date;

public class Event implements Serializable {
    private int id;
    private Date dateEvent;
    private String description;
    private int associationId;
    private Association association;

    public Event(int id, Date dateEvent, String description, Association association) {
        this.id = id;
        this.dateEvent = dateEvent;
        this.description = description;
        this.association = association;
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

    public static void delete(int id) {
        DBConnector.deleteEvent(id);
    }

    public static void create(String assoName, Date date, String description) {
        DBConnector.createEvent(assoName, date, description);
    }
}
