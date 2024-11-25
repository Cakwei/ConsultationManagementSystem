package org.cakwei;

public class Account {
    private final String username, password, id;
    private final userTypes userType;
    Account(String id, String username,String password, userTypes userType) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.userType = userType;
    }
    public String getName() {
        return username;
    }
    public String getPassword() {
        return this.password;
    }
    public userTypes getUserType() {
        return this.userType;
    }

    public String getId() {
        return id;
    }
    //public int getId() {return this.}
}
