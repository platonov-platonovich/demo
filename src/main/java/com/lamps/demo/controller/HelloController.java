package com.lamps.demo.controller;

import com.lamps.demo.dao.RoomDAO;
import com.lamps.demo.models.Room;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class HelloController {

    private final RoomDAO roomDAO;

    public HelloController(RoomDAO roomDAO) {
        this.roomDAO = roomDAO;
    }

    @GetMapping("/room/{id}")
    public String getModel(@PathVariable("id") int id, Model model) {
        Room room = roomDAO.getById(id);
        model.addAttribute("room", room);
        return "room";
    }

}
