package ru.clevertec.domain.comment;
import java.util.UUID;
import java.time.LocalDateTime;
import lombok.Getter;
import lombok.Setter;

/**
 * Представляет доменную модель для создания комментария.
 * Используется в сервисном слое для обработки операций создания комментария.
 *
 * @field id        уникальный идентификатор комментария
 * @field username  имя пользователя, создавшего комментарий
 * @field text      текст комментария
 * @field newsId    идентификатор новости, к которой относится комментарий
 * @field createdAt время создания комментария
 */
@Getter
@Setter
public class CommentCreateDomain {
    private UUID id;
    private String username;
    private String text;
    private UUID newsId;
    private LocalDateTime createdAt;
}
