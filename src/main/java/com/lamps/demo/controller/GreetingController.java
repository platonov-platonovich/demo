package com.lamps.demo.controller;

import com.lamps.demo.dao.RoomDaoPostgres;
import com.lamps.demo.hello.Room;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
public class GreetingController {

    private final RoomDaoPostgres roomDao;

    public GreetingController(RoomDaoPostgres roomDao) {
        this.roomDao = roomDao;
    }

    @GetMapping("")
    public String index(Model model) {
        List<Room> rooms = roomDao.index();
        model.addAttribute("rooms", rooms);
        return "index";
    }

    @GetMapping("/{id}")
    public String getModel(@PathVariable("id") int id, Model model) {
        Room room = roomDao.getById(id);
        model.addAttribute("room", room);
        return "room";
    }

    @GetMapping("/{id}/edit")
    public String edit(Model model, @PathVariable("id") int id) {
        model.addAttribute("room", roomDao.getById(id));
        return "edit";
    }

    //    @PostMapping("/{id}")
//    public String update(@ModelAttribute("room") Room room, @PathVariable("id") int id) {
//        roomDAO.update(id, room);
//        return "redirect:";
//    }
    @MessageMapping("/hello")
    @SendTo("/topic/greetings")
    public Room greeting(Room room) throws Exception {
        Thread.sleep(1000); // simulated delay
        roomDao.update(room.getId(), room);
        return room;
    }
}
//    @MessageMapping("/hello")
//    @SendTo("/topic/greetings")
//    public Greeting greeting(HelloMessage message) throws Exception {
//        Thread.sleep(1000); // simulated delay
//        return new Greeting("Hello, " + HtmlUtils.htmlEscape(message.getName()) + "!");
//    }
