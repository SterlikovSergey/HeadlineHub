package ru.clevertec.adapter.input.web.comment.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import ru.clevertec.adapter.output.jpa.entity.CommentEntity;

import java.util.UUID;

/**
 * Data Transfer Object (DTO) для создания комментария.
 * Используется для передачи данных между слоями приложения при создании комментария.
 *
 * @param username имя пользователя, создающего комментарий
 * @param text     текст комментария
 * @param newsId   идентификатор новости, к которой относится комментарий
 * @link DTO for {@link CommentEntity}
 */
public record CommentCreateRequestDto(@NotBlank
                                      @NotNull(message = "username must not be null")
                                      String username,
                                      @NotBlank
                                      @NotNull
                                      @Size(message = "Text must be between 5 and 55 characters", min = 5, max = 55)
                                      String text,
                                      @NotNull (message = "News id must not be null")
                                      UUID newsId) {
}
