package com.lamps.demo.dao;

import com.lamps.demo.hello.Room;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
@Repository
public class RoomDaoPostgres implements RoomDao {

    private static int ROOMS_COUNT;
    private static final String URL = "jdbc:postgresql://localhost:5432/postgres";
    private static final String USERNAME = "postgres";
    private static final String PASSWORD = "qwerty";
    private static Connection connection;

    static {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        try {
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        } catch (SQLException throwables) {
            throw new RuntimeException(throwables);
        }
    }

@Override
    public List<Room> index() {
        List<Room> rooms = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();
            String SQL = "SELECT * FROM Room";
            ResultSet resultSet = statement.executeQuery(SQL);
            while (resultSet.next()) {
                Room room = new Room();
                room.setId(resultSet.getInt("id"));
                room.setStatus(resultSet.getString("status"));
                rooms.add(room);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return rooms;
    }

@Override
    public Room getById(int id) {
        try (Statement statement = connection.createStatement()) {
            String query = "SELECT * FROM Room WHERE id='%d'";
            ResultSet resultSet = statement.executeQuery(String.format(query, id));
            Room room = new Room();
            resultSet.next();
            room.setId(resultSet.getInt("id"));
            room.setStatus(resultSet.getString("status"));
            return room;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
@Override
    public void update(int id, Room updatedRoom) {
        try {
            PreparedStatement preparedStatement =
                    connection.prepareStatement("UPDATE Room SET status=? WHERE id=?");
            preparedStatement.setString(1, updatedRoom.getStatus());
            preparedStatement.setInt(2, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}