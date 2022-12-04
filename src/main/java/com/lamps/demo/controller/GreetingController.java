package com.lamps.demo.controller;

import com.lamps.demo.dao.RoomDAO;
import com.lamps.demo.hello.Greeting;
import com.lamps.demo.hello.HelloMessage;
import com.lamps.demo.hello.Room;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.util.HtmlUtils;

import java.util.List;

@Controller
public class GreetingController {

    private final RoomDAO roomDAO;

    public GreetingController(RoomDAO roomDAO) {
        this.roomDAO = roomDAO;
    }

    @GetMapping("")
    public String index(Model model) {
        List<Room> rooms = roomDAO.index();
        model.addAttribute("rooms", rooms);
        return "index";
    }

    @GetMapping("/{id}")
    public String getModel(@PathVariable("id") int id, Model model) {
        Room room = roomDAO.getById(id);
        model.addAttribute("room", room);
        return "room";
    }

    @GetMapping("/{id}/edit")
    public String edit(Model model, @PathVariable("id") int id) {
//        model.addAttribute("room", roomDAO.getById(id));
        return "edit";
    }

//    @PostMapping("/{id}")
//    public String update(@ModelAttribute("room") Room room, @PathVariable("id") int id) {
//        roomDAO.update(id, room);
//        return "redirect:";
//    }
@MessageMapping("/hello")
@SendTo("/topic/greetings")
public Greeting greeting (HelloMessage message, Model model, @PathVariable("id") int id)  throws Exception{
        Thread.sleep(1000); // simulated delay
    return new Greeting( HtmlUtils.htmlEscape(message.getName()));
}
}
//    @MessageMapping("/hello")
//    @SendTo("/topic/greetings")
//    public Greeting greeting(HelloMessage message) throws Exception {
//        Thread.sleep(1000); // simulated delay
//        return new Greeting("Hello, " + HtmlUtils.htmlEscape(message.getName()) + "!");
//    }
