package ru.clevertec.adapter.input.web.comment.dto.request;

import jakarta.validation.constraints.NotNull;
import ru.clevertec.adapter.output.jpa.entity.CommentEntity;

import java.util.UUID;

/**
 * Data Transfer Object (DTO) для удаления комментария.
 * Используется для передачи идентификатора комментария при удалении.
 *
 * @param id уникальный идентификатор комментария
 * @link DTO for {@link CommentEntity}
 */
public record CommentDeleteRequestDto(@NotNull UUID id) {
}
