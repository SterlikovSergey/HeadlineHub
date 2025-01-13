package ru.clevertec.adapter.input.web.comment.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import ru.clevertec.adapter.output.jpa.entity.CommentEntity;

import java.util.UUID;

/**
 * Data Transfer Object (DTO) для обновления комментария.
 * Используется для передачи данных между слоями приложения при обновлении комментария.
 *
 * @param id       уникальный идентификатор комментария
 * @param username обновленное имя пользователя
 * @param text     обновленный текст комментария
 * @param newsId   идентификатор новости, к которой относится комментарий
 * @link DTO for {@link CommentEntity}
 */
public record CommentUpdateRequestDto(@NotNull UUID id,
                                      @NotBlank @NotNull String username,
                                      @NotBlank @NotNull String text,
                                      @NotNull UUID newsId) {
}
