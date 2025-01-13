package ru.clevertec.adapter.input.web.news.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import ru.clevertec.adapter.output.jpa.entity.NewsEntity;

/**
 * Data Transfer Object (DTO) для создания новости.
 * Используется для передачи данных между слоями приложения при создании новости.
 *
 * @param title заголовок новости
 * @param text  текст новости
 * @link DTO for {@link NewsEntity}
 */
public record NewsCreateRequestDto(@NotBlank
                                   @NotNull(message = "Title must not be blank and not be null")
                                   String title,
                                   @Size(message = "Text must be between 10 and 1000 characters", min = 10, max = 1000)
                                   String text) {
}
