package com.lamps.demo.hello;

public class Room {
    private int id;
    private String status;

     public Room(String status) {
        this.status = status;
    }

    public Room(int id, String status) {
        this.id = id;
        this.status = status;
    }

    public Room() {}

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

//    public void button() {
//        if (name == "on") {
//            name = "off";
//        } else {
//            name = "on";
//        }
//    }
}
