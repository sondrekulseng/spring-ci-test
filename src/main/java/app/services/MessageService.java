package app.services;

import app.entities.MessageEntity;
import app.models.MessageDto;
import app.repositories.MessageRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
public class MessageService {

    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm");

    private static final Logger LOGGER = LoggerFactory.getLogger(MessageService.class);

    @Value("${adminPin}")
    private String adminPin;

    private final MessageRepository messageRepository;

    public MessageService(
        final MessageRepository messageRepository
    ) {
        this.messageRepository = messageRepository;
    }

    public List<MessageDto> getMessages(final String roomId) {
        List<MessageEntity> fetchedMessages = messageRepository.findByRoomId(roomId);
        return fetchedMessages.stream().map(messageEntity -> new MessageDto(
                messageEntity.getRoomId(),
                messageEntity.getSender(),
                messageEntity.getMessage(),
                messageEntity.getDateTimeSent().format(DATE_TIME_FORMATTER))
        ).toList();
    }

    public boolean deleteMessagesForRoom(final String roomId, final String pin) {
        if (pin.equals(adminPin)) {
            List<MessageEntity> messagesToDelete = messageRepository.findByRoomId(roomId);
            messageRepository.deleteAll(messagesToDelete);
            LOGGER.info("Chats deleted for room: " + roomId);
            return true;
        }
        return false;
    }

    public boolean deleteAllMessages(final String pin) {
        if (pin.equals(adminPin)) {
            messageRepository.deleteAll();
            LOGGER.info("All chats deleted");
            return true;
        }
        return false;
    }

    public MessageEntity saveMessage(final MessageDto messageDto) {
        return messageRepository.save(
                new MessageEntity(
                        messageDto.roomId(),
                        messageDto.message(),
                        messageDto.sender(),
                        LocalDateTime.now()
                )
        );
    }

}
