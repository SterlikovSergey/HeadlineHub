package ru.clevertec.domain.news;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

/**
 * Представляет доменную модель для обновления новости.
 * Используется в сервисном слое для обработки операций обновления новости.
 *
 * @field id    уникальный идентификатор новости
 * @field title обновленный заголовок новости
 * @field text  обновленный текст новости
 */
@Getter
@Setter
public class NewsUpdateDomain {
    private UUID id;
    private String title;
    private String text;
}
