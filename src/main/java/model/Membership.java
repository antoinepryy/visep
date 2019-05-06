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
}
