package com.artyawn.arty;

public class CreateTaskClass {
    public String task_name;
    public String group;
    public String worker;
    public String description;

    public String getTask_name() {
        return task_name;
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
}
