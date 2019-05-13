package model;

import java.io.Serializable;

public class User implements Serializable {
    private int id;
    private String firstName;
    private String lastName;
    private String password;
    private int code;
    private String email;

    public  User(String fName, String lName, String pw, int code, String mail){
        this.firstName = fName;
        this.lastName = lName;
        this.password = pw;
        this.code = code;
        this.email = mail;

    }

    public int getId() {
        return id;
    }


    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void persist(){
        DBConnector.saveUser(this);
    }

    public static String isAuthenticationValidated(String uname, String pw){
        return DBConnector.checkIfUserExist(uname, pw);

    }
}
