package com.lamps.demo.controller;

import com.lamps.demo.dao.RoomDAO;
import com.lamps.demo.models.Room;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PatchMapping;
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
@GetMapping("/{id}/edit")
    public String edit(Model model, @PathVariable("id") int id){
        model.addAttribute("room", roomDAO.getById(id));
        return "rooms/edit";
    }
    @PatchMapping("/{id}")
    public String update(@ModelAttribute("room") Room room,@PathVariable("id") int id){
        roomDAO.update(id, room);
        return ""
    }
}
