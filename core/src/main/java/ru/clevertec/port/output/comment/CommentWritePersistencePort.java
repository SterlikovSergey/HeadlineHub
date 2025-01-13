package ru.clevertec.port.output.comment;

import ru.clevertec.domain.comment.CommentCreateDomain;
import ru.clevertec.domain.comment.CommentUpdateDomain;

import java.util.UUID;

/**
 * Интерфейс для портов записи комментариев в источник данных.
 */
public interface CommentWritePersistencePort {
    CommentCreateDomain saveComment(CommentCreateDomain commentDomainModel);

    CommentUpdateDomain updateComment(CommentUpdateDomain commentDomainModel);

    void deleteComment(UUID id);
}
