package com.IITB.chat.Repositry;

import com.IITB.chat.Entity.Chat;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface ChatRepository extends JpaRepository<Chat, Long> {
    List<Chat> findBySenderIdAndReceiverIdOrSenderIdAndReceiverIdOrderByTimeStampMessageAsc(Long senderId1, Long receiverId1, Long senderId2, Long receiverId2);
}

