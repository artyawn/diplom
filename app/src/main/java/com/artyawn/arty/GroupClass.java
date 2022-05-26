package com.artyawn.arty;

import java.util.ArrayList;

public class GroupClass {
    public String title;
    public String grouper;
    public ArrayList<String> mates;
    public GroupClass(){}

    public String getTitle() {
        return title;
    }

    public String getGrouper() {
        return grouper;
    }

    public GroupClass(String title, String grouper){
        this.title = title;
        this.grouper = grouper;

    }
    public GroupClass(String title){
        this.title = title;


    }
    public GroupClass(String title,ArrayList<String> mates){
        this.title = title;
        this.mates = mates;


    }

}
