package app.repositories;

import app.entities.MessageEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MessageRepository extends JpaRepository<MessageEntity, Long> {

    List<MessageEntity> findByRoomId(final String roomId);

    MessageEntity save(final MessageEntity messageEntity);
}
