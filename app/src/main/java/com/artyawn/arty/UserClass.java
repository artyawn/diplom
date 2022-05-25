package com.artyawn.arty;

public class UserClass {
    public String email, id;
    public UserClass(){}

    public String getEmail() {
        return email;
    }

    public String getId() {
        return id;
    }

    public UserClass(String id, String email){
        this.id = id;
        this.email= email;
    }
}
