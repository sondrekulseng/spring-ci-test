package app.services;

import app.entities.MessageEntity;
import app.models.MessageDto;
import app.repositories.MessageRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DataJpaTest
class MessageServiceTest {

    @Autowired
    private MessageRepository messageRepository;

    @Test
    void should_save_message() {
        // Arrange
        MessageService messageService = new MessageService(messageRepository);

        // Act
        messageService.saveMessage(new MessageDto("roomId", "sender", "message", null));

        // Assert
        List<MessageEntity> result = messageRepository.findAll();
        assertThat(result.size()).isEqualTo(1);
        MessageEntity messageEntity = result.get(0);
        assertThat(messageEntity.getRoomId()).isEqualTo("roomId");
        assertThat(messageEntity.getSender()).isEqualTo("sender");
        assertThat(messageEntity.getMessage()).isEqualTo("message");
        assertThat(messageEntity.getDateTimeSent()).isInstanceOf(LocalDateTime.class);
    }

    @Test
    void should_get_messages_from_room() {
        // Arrange
        LocalDateTime localDateTime = LocalDateTime.of(
                2023,
                1,
                1,
                12,
                0
        );
        messageRepository.save(new MessageEntity("room1", "message1", "sender1", localDateTime));
        messageRepository.save(new MessageEntity("room1", "message2", "sender2", localDateTime.plusMinutes(1)));
        messageRepository.save(new MessageEntity("room2", "message", "sender", localDateTime));

        MessageService messageService = new MessageService(messageRepository);

        // Act
        List<MessageDto> messages = messageService.getMessages("room1");

        // Assert
        assertThat(messages.size()).isEqualTo(2);
        assertThat(messages.get(0).roomId()).isEqualTo("room1");
        assertThat(messages.get(1).roomId()).isEqualTo("room1");
        assertThat(messages.get(0).message()).isEqualTo("message1");
        assertThat(messages.get(1).message()).isEqualTo("message2");
        assertThat(messages.get(0).sender()).isEqualTo("sender1");
        assertThat(messages.get(1).sender()).isEqualTo("sender2");
        assertThat(messages.get(0).dateTimeSent()).isEqualTo("01.01.2023 12:00");
        assertThat(messages.get(1).dateTimeSent()).isEqualTo("01.01.2023 12:01");
    }
}