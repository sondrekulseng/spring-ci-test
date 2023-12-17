package app.services;

import app.entities.ChatRoomEntity;
import app.models.ChatRoomDto;
import app.repositories.ChatRoomRepository;
import org.springframework.stereotype.Service;

import java.util.List;
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
                        chatRoomDto.name(),
                        chatRoomDto.isPublic()
                )
        );
        return id;
    }

    public ChatRoomDto findById(final String roomId) {
        ChatRoomEntity chatRoomEntity = chatRoomRepository
                .findById(roomId)
                .orElseThrow(() -> new IllegalArgumentException(roomId + " is not a valid chatroom"));

        return new ChatRoomDto(
                chatRoomEntity.getId(),
                chatRoomEntity.getName(),
                chatRoomEntity.isPublic()
        );
    }

    public List<ChatRoomDto> getPublicRooms() {
        return chatRoomRepository.findByIsPublic(true).stream()
                .map(entity -> new ChatRoomDto(entity.getId(), entity.getName(), entity.isPublic()))
                .toList();
    }
}
