package com.lamps.demo.controller;

import com.lamps.demo.dao.RoomDAO;
import com.lamps.demo.models.Room;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/room")
public class HelloController {

    private final RoomDAO roomDAO;

    public HelloController(RoomDAO roomDAO) {
        this.roomDAO = roomDAO;
    }

    @GetMapping("/all")
    public String index(Model model) {
        List<Room> rooms = roomDAO.index();
        model.addAttribute("rooms", rooms);
        return "allrooms";
    }

    @GetMapping("/{id}")
    public String getModel(@PathVariable("id") int id, Model model) {
        Room room = roomDAO.getById(id);
        model.addAttribute("room", room);
        return "room";
    }

    @GetMapping("/{id}/edit")
    public String edit(Model model, @PathVariable("id") int id) {
        model.addAttribute("room", roomDAO.getById(id));
        return "edit";
    }

    @PostMapping("/{id}")
    public String update(@ModelAttribute("room") Room room, @PathVariable("id") int id) {
        System.out.println(room.getId() + " status:"+ room.getStatus() + " path variable: " + id);
        roomDAO.update(id, room);
        return "redirect:/room/all";
    }
}
