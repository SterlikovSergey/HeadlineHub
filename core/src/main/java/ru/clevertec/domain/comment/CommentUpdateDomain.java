package ru.clevertec.domain.comment;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

/**
 * Представляет доменную модель для обновления комментария.
 * Используется в сервисном слое для обработки операций обновления комментария.
 *
 * @field id        уникальный идентификатор комментария
 * @field username  обновленное имя пользователя
 * @field text      обновленный текст комментария
 * @field newsId    идентификатор новости, к которой относится комментарий
 */
@Getter
@Setter
public class CommentUpdateDomain {
    private UUID id;
    private String username;
    private String text;
    private UUID newsId;
}
