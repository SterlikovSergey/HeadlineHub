package ru.clevertec.adapter.input.web.comment.dto.response;

import ru.clevertec.adapter.output.jpa.entity.CommentEntity;

import java.time.LocalDateTime;
import java.util.UUID;

/**
 * Data Transfer Object (DTO) для ответа с информацией о комментарии.
 * Используется для передачи данных комментария клиенту.
 *
 * @param id        уникальный идентификатор комментария
 * @param username  имя пользователя, создавшего комментарий
 * @param text      текст комментария
 * @param newsId    идентификатор новости, к которой относится комментарий
 * @param createdAt время создания комментария
 * @link DTO for {@link CommentEntity}
 */
public record CommentResponseDto(UUID id, String username, String text, UUID newsId, LocalDateTime createdAt) {
}
