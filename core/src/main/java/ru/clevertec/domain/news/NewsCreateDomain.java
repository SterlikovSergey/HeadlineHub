package ru.clevertec.domain.news;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

/**
 * Представляет доменную модель для создания новости.
 * Используется в сервисном слое для обработки операций создания новости.
 *
 * @field id    уникальный идентификатор новости
 * @field title заголовок новости
 * @field text  текст новости
 */
@Getter
@Setter
public class NewsCreateDomain {
    private UUID id;
    private String title;
    private String text;
}
