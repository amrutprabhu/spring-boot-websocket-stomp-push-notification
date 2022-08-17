package com.amrut.prabhu.notifications;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

@Controller
public class MessageController {

    @Autowired
    SimpMessagingTemplate simpMessagingTemplate;

    // Mapped as /app/application
    @MessageMapping("/application")
    @SendTo("/all/messages")
    public Message send(final Message message) throws Exception {
        return message;
    }

    // Mapped as /app/private
    @MessageMapping("/private")
    public void sendToSpecificUser(@Payload Message message) {
        simpMessagingTemplate.convertAndSendToUser(message.getTo(), "/specific", message);
    }
}