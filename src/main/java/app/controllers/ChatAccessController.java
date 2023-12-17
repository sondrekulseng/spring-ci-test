package app.controllers;

import app.models.ChatRoomDto;
import app.services.ChatRoomService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class ChatAccessController {

    private final ChatRoomService chatRoomService;

    public ChatAccessController(
            final ChatRoomService chatRoomService
    ) {
        this.chatRoomService = chatRoomService;
    }

    @PostMapping("/create/room")
    public String createRoom(@RequestBody final ChatRoomDto chatRoomDto) {
        return chatRoomService.save(chatRoomDto);
    }

    @GetMapping("/room/{id}")
    public ResponseEntity<ChatRoomDto> getRoom(@PathVariable("id") final String id) {
        try {
            return ResponseEntity.ok(chatRoomService.findById(id));
        } catch (IllegalArgumentException illegalArgumentException) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @GetMapping("/rooms/public")
    public List<ChatRoomDto> getPublicRooms() {
        return chatRoomService.getPublicRooms();
    }
}
