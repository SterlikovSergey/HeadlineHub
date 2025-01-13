package ru.clevertec.domain.comment;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

/**
 * Представляет доменную модель для удаления комментария.
 * Используется в сервисном слое для обработки операций удаления комментария.
 *
 * @field id уникальный идентификатор комментария для удаления
 */
@Getter
@Setter
public class CommentDeleteDomain {
    private UUID id;
}
