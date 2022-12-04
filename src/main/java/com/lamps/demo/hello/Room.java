package com.lamps.demo.hello;

public class Room {
    private int id;
    private String name;

    public Room(String spring) {}
    public Room() {}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

//    public void button() {
//        if (name == "on") {
//            name = "off";
//        } else {
//            name = "on";
//        }
//    }
}
