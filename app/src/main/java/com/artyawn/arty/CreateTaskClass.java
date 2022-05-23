package com.artyawn.arty;

public class CreateTaskClass {
    public String task_name;
    public String group;
    public String worker;
    public String description;

    public CreateTaskClass(){}
    public CreateTaskClass( String task_name,String group,String worker,String description){
        this.description=description;
        this.task_name = task_name;
        this.worker = worker;
        this.group=group;
    }
}
