package app.services;

import app.entities.ChatRoomEntity;
import app.models.ChatRoomDto;
import app.repositories.ChatRoomRepository;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class ChatRoomService {

    private final ChatRoomRepository chatRoomRepository;

    public ChatRoomService(
            final ChatRoomRepository chatRoomRepository
    ) {
        this.chatRoomRepository = chatRoomRepository;
    }

    public String save(final ChatRoomDto chatRoomDto) {
        String id = UUID.randomUUID().toString();
        chatRoomRepository.save(
                new ChatRoomEntity(
                        id,
                        chatRoomDto.name()
                )
        );
        return id;
    }

    public String findById(final String roomId) {
        ChatRoomEntity chatRoomEntity = chatRoomRepository
                .findById(roomId)
                .orElseThrow(() -> new IllegalArgumentException(roomId + " is not a valid chatroom"));

        return chatRoomEntity.getName();
    }
}
