package com.lamps.demo.models;

public class Room {
    private int id;
    private  String status;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    public void button(){
        if (status == "on") {
            status = "off";
            status = "on";

        }}}
