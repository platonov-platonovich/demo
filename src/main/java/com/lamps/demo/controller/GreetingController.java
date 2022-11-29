package com.lamps.demo.controller;

import com.lamps.demo.models.Room;
import com.lamps.demo.websocket.Greeting;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.util.HtmlUtils;

@Controller
public class GreetingController {


	@MessageMapping("/hello")
	@SendTo("/topic/greetings")
	public Greeting greeting(Room newRoom) throws Exception {
		Thread.sleep(1000); // simulated delay
		return new Greeting(HtmlUtils.htmlEscape(newRoom.getStatus()) + "!");
	}
}
