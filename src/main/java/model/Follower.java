package model;

import java.io.Serializable;

public class Follower implements Serializable {

    private int id;
    private int userId;
    private int associationId;

    public Follower() {
    }

    public int getId() {
        return id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getAssociationId() {
        return associationId;
    }

    public void setAssociationId(int associationId) {
        this.associationId = associationId;
    }
}
