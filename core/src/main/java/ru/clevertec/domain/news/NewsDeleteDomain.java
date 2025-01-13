package ru.clevertec.domain.news;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

/**
 * Представляет доменную модель для удаления новости.
 * Используется в сервисном слое для обработки операций удаления новости.
 *
 * @field id уникальный идентификатор новости для удаления
 */
@Getter
@Setter
public class NewsDeleteDomain {
    private UUID id;
}
