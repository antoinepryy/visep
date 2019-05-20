package model;

import java.io.Serializable;
import java.util.Date;

public class Message implements Serializable {

    private int id;
    private int senderId;
    private int recipientId;
    private Date msgDate;
    private String text;

    public Message(int id, int sender, int recipient, Date date, String text) {
        this.id = id;
        this.senderId = sender;
        this.recipientId = recipient;
        this.msgDate = date;
        this.text = text;
    }

    public Message(int sender, int recipient, Date date, String text) {
        this.senderId = sender;
        this.recipientId = recipient;
        this.msgDate = date;
        this.text = text;
    }

    public int getId() {
        return id;
    }

    public int getSenderId() {
        return senderId;
    }

    public void setSenderId(int senderId) {
        this.senderId = senderId;
    }

    public int getRecipientId() {
        return recipientId;
    }

    public void setRecipientId(int recipientId) {
        this.recipientId = recipientId;
    }

    public Date getMsgDate() {
        return msgDate;
    }

    public void setMsgDate(Date msgDate) {
        this.msgDate = msgDate;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
