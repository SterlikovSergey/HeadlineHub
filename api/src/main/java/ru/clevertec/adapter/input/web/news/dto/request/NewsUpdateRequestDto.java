package ru.clevertec.adapter.input.web.news.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import ru.clevertec.adapter.output.jpa.entity.NewsEntity;

import java.util.UUID;

/**
 * Data Transfer Object (DTO) для обновления новости.
 * Используется для передачи данных между слоями приложения при обновлении новости.
 *
 * @param id    уникальный идентификатор новости
 * @param title обновленный заголовок новости
 * @param text  обновленный текст новости
 * @link DTO for {@link NewsEntity}
 */
public record NewsUpdateRequestDto(@NotNull UUID id,
                                   @NotBlank
                                   @NotNull String title,
                                   @Size(min = 10, max = 1000) String text) {
}
