package com.artyawn.arty;

import java.util.Date;

public class CreateTaskClass {
    public String task_name;
    public String group;
    public String worker;
    public String description;
    public String sender;
    public Date date;

    public String getTask_name() {
        return task_name;
    }

    public String getSender() {
        return sender;
    }

    public Date getDate() {
        return date;
    }

    public String getGroup() {
        return group;
    }

    public String getWorker() {
        return worker;
    }

    public String getDescription() {
        return description;
    }

    public CreateTaskClass() {
    }

    public CreateTaskClass(String task_name, String group, String worker, String description) {
        this.description = description;
        this.task_name = task_name;
        this.worker = worker;
        this.group = group;
    }

    public CreateTaskClass(String task_name, String group, String description) {
        this.description = description;
        this.task_name = task_name;
        this.group = group;
    }

    public CreateTaskClass(String task_name, Date date, String worker, String description) {
        this.description = description;
        this.task_name = task_name;
        this.worker = worker;
        this.date = date;
    }
    public CreateTaskClass(String task_name,  String group,Date date, String description) {
        this.description = description;
        this.task_name = task_name;
        this.worker = worker;
        this.date = date;
    }
}
