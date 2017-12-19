package cn.reawei.api.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class ChatController {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @CrossOrigin(origins = "*", maxAge = 3600)
    @MessageMapping("hello")
    @SendTo("topic/greetings")
    public String say(String message) {
        logger.info("message: { " + message + " } ");
        return "welcome," + message + " !";
    }

}
