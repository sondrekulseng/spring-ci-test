package app.controllers;

import app.models.MessageDto;
import app.services.MessageService;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.annotation.SubscribeMapping;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class ChatController {

    private final MessageService messageService;

    public ChatController(
        final MessageService messageService
    ) {
        this.messageService = messageService;
    }


    @SubscribeMapping("/chat/{roomId}")
    public List<MessageDto> subscribe(@DestinationVariable String roomId) {
        return messageService.getMessages(roomId);
    }

    @MessageMapping("/send/{roomId}")
    @SendTo("/app/chat/{roomId}")
    public List<MessageDto> send(@DestinationVariable String roomId, @Payload(required = false) MessageDto messageDto) {
        messageService.saveMessage(messageDto);
        return messageService.getMessages(roomId);
    }

    @MessageMapping("/typing/{roomId}")
    @SendTo("/app/typing/{roomId}")
    public String typing(@DestinationVariable String roomId, @Payload(required = false) String message) {
        return message;
    }

    @MessageMapping("/deleted/{roomId}")
    @SendTo("/app/deleted/{roomId}")
    public String deleted(@DestinationVariable String roomId, @Payload(required = false) String message) {
        return message;
    }
}
