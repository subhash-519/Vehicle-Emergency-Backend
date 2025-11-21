package com.mechongo.backend.entity.chat;

import com.mechongo.backend.entity.common.BaseEntity;
import com.mechongo.backend.entity.user.User;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "chat_messages", indexes = {
        @Index(name = "idx_chat_sos", columnList = "sos_request_id"),
        @Index(name = "idx_chat_sender", columnList = "sender_id")
})
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ChatMessage extends BaseEntity {

    @Column(name = "sos_request_id", length = 36)
    private String sosRequestId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "sender_id")
    private User sender;

    @Column(name = "message", columnDefinition = "TEXT")
    private String message;

    @Column(name = "attachment_url", length = 1500)
    private String attachmentUrl;

    @Column(name = "message_type", length = 64)
    private String messageType = "TEXT";

    @Column(name = "seen", nullable = false)
    private boolean seen = false;
}
