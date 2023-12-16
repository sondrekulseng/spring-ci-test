package app.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity(name = "CHAT_ROOM")
public class ChatRoomEntity {

    @Id
    private String id;

    private String name;

    public ChatRoomEntity(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public ChatRoomEntity() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
