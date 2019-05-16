package model;

import java.io.Serializable;

public class Membership implements Serializable {

    private int id;
    private int userid;
    private int associationId;

    public Membership() {
    }

    public int getId() {
        return id;
    }

    public int getUserid() {
        return userid;
    }

    public void setUserid(int userid) {
        this.userid = userid;
    }

    public int getAssociationId() {
        return associationId;
    }

    public void setAssociationId(int associationId) {
        this.associationId = associationId;
    }

    public static void deleteMember(String assoName, int memberCode) {
        DBConnector.deleteAssoMember(assoName, memberCode);
    }

    public static void addMember(String assoName, String fName, String lName) {
        DBConnector.addAssoMember(assoName, fName, lName);
    }
}