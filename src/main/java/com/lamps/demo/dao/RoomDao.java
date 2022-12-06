package com.lamps.demo.dao;

import com.lamps.demo.hello.Room;

import java.util.List;

public interface RoomDao {
    List<Room> index();

    Room getById(int id);

    void update(int id, Room updatedRoom);
}
