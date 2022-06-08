package com.artyawn.arty;

import java.util.ArrayList;
import java.util.List;

public class GroupClass {
    public String title;
    public List<String> items;

    public String getTitle() {
        return title;
    }

    public List<String> getItems() {
        return items;
    }

    public GroupClass(String title, List<String> items){
        this.title = title;
        this.items = items;
    }

}


