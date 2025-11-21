package com.mechongo.backend.entity.attachment;

import com.mechongo.backend.entity.common.BaseEntity;
import com.mechongo.backend.entity.user.User;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "attachments", indexes = {
        @Index(name = "idx_attachment_owner", columnList = "owner_id"),
        @Index(name = "idx_attachment_type", columnList = "attachment_type")
})
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Attachment extends BaseEntity {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "owner_id") // could be user or mechanic
    private User owner;

    @Column(name = "attachment_type", length = 64)
    private String attachmentType;

    @Column(name = "file_url", nullable = false, length = 1500)
    private String fileUrl; // S3 / Cloud storage URL

    @Column(name = "original_name", length = 255)
    private String originalName;

    @Column(name = "file_size")
    private Long fileSize; // bytes

    @Column(name = "content_type", length = 256)
    private String contentType; // MIME type (image/jpeg, application/pdf)

    @Column(name = "metadata", columnDefinition = "LONGTEXT")
    private String metadata;
}
