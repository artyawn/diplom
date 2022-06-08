package com.artyawn.arty;

import java.util.Date;

public class CreateTaskClass {
    public String task_name;
    public String group;
    public String worker;
    public String description;
    public String sender;
    public String date;
    public String worker_id;
    public String sender_id;
    public String status;

    public String getStatus(){return status;}

    public String getWorker_id(){return worker_id;}

    public String getSender_id(){return sender_id;}

    public String getTask_name() {
        return task_name;
    }

    public String getSender() {
        return sender;
    }

    public String getDate() {
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

    public CreateTaskClass(String task_name, String date, String description,  String sender, String group, String sender_id) {
        this.task_name = task_name;
        this.date = date;
        this.description = description;
        this.sender = sender;
        this.group = group;
        this.sender_id = sender_id;

    }

    public CreateTaskClass(String status,String task_name, String date, String description, String worker, String group, String worker_id) {
        this.status = status;
        this.task_name = task_name;
        this.date = date;
        this.description = description;
        this.worker = worker;
        this.group = group;
        this.worker_id = worker_id;

    }

}
