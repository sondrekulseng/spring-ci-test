package app.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Entity(name = "MESSAGE")
public class MessageEntity {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    private String roomId;
    private String message;
    private String sender;
    private LocalDateTime dateTimeSent;

    public MessageEntity(String roomId, String message, String sender, LocalDateTime dateTimeSent) {
        this.roomId = roomId;
        this.message = message;
        this.sender = sender;
        this.dateTimeSent = dateTimeSent;
    }

    public MessageEntity() {
    }

    public Long getId() {
        return id;
    }

    public String getRoomId() {
        return roomId;
    }

    public String getMessage() {
        return message;
    }

    public String getSender() {
        return sender;
    }

    public LocalDateTime getDateTimeSent() {
        return dateTimeSent;
    }

    public String getFormattedDateTimeSent() {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd.MMM 'kl'. HH:mm");
        return dateTimeSent.format(dateTimeFormatter);
    }
}
