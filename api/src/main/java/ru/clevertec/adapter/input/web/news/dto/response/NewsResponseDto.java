package ru.clevertec.adapter.input.web.news.dto.response;

import ru.clevertec.adapter.output.jpa.entity.NewsEntity;

import java.util.UUID;

/**
 * Data Transfer Object (DTO) для ответа с информацией о новости.
 * Используется для передачи данных новости клиенту.
 *
 * @param id    уникальный идентификатор новости
 * @param title заголовок новости
 * @param text  текст новости
 * @link DTO for {@link NewsEntity}
 */
public record NewsResponseDto(UUID id, String title, String text) {
}
