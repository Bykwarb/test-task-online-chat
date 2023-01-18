package com.example.testtask.websocket;

import com.example.testtask.websocket.entity.Message;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.stereotype.Controller;

@Controller
@Slf4j
public class WebSocketController {
    @MessageMapping("/chat.sendMessage")
    @SendTo("/topic/publicChatRoom")
    public Message sendMessage(@Payload Message message){
        log.info("message send: " + message.getContent());
        return message;
    }
    @MessageMapping("/chat.addUser")
    @SendTo("/topic/publicChatRoom")
    public Message addUser(@Payload Message message, SimpMessageHeaderAccessor headerAccessor){
        log.info("New user discovered");
        headerAccessor.getSessionAttributes().put("username", message.getSender());
        return message;
    }
}
