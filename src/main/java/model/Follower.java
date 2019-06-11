package model;

import java.io.Serializable;
import java.util.List;

public class Follower implements Serializable {

    private int id;
    private int userId;
    private int associationId;

    public Follower(int id, int userId, int associationId) {
        this.id = id;
        this.userId = userId;
        this.associationId = associationId;
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

    public static List<Follower> getFollowers(int userId) {
        return DBConnector.getFollowers(userId);
    }

    public static Boolean isFollower(int userId, int associationId) {
        return DBConnector.isFollower(userId, associationId);
    }

    public static void addFollower(int userId, int associationId) {
        DBConnector.addFollower(userId, associationId);
    }

    public static void delFollower(int userId, int associationId) {
        DBConnector.delFollower(userId, associationId);
    }
}