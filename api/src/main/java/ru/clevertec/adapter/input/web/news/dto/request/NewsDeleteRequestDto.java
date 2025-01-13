package ru.clevertec.adapter.input.web.news.dto.request;

import jakarta.validation.constraints.NotNull;
import ru.clevertec.adapter.output.jpa.entity.NewsEntity;

import java.util.UUID;

/**
 * Data Transfer Object (DTO) для удаления новости.
 * Используется для передачи идентификатора новости при удалении.
 *
 * @param id уникальный идентификатор новости
 * @link DTO for {@link NewsEntity}
 */
public record NewsDeleteRequestDto(@NotNull UUID id) {
}
