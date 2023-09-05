package app.controllers;

import app.services.MessageService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/admin")
public class AdminController {

    private final MessageService messageService;

    public AdminController(
        final MessageService messageService
    ) {
        this.messageService = messageService;
    }

    @DeleteMapping("/chats")
    public ResponseEntity<String> deleteRoom(@RequestParam("roomId") String roomId, @RequestParam("pin") String pin) {
        boolean deleted = messageService.deleteMessagesForRoom(roomId, pin);
        if (deleted) {
            return ResponseEntity.ok("Deleted");
        } else {
            return ResponseEntity.internalServerError().build();
        }
    }

    @DeleteMapping("/chats/all")
    public ResponseEntity<String> deleteAll(@RequestParam("pin") String pin) {
        boolean deleted = messageService.deleteAllMessages(pin);
        if (deleted) {
            return ResponseEntity.ok("Deleted");
        } else {
            return ResponseEntity.internalServerError().build();
        }
    }
}
