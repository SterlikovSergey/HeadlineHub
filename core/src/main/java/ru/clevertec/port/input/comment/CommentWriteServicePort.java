package ru.clevertec.port.input.comment;

import ru.clevertec.domain.comment.CommentCreateDomain;
import ru.clevertec.domain.comment.CommentDeleteDomain;
import ru.clevertec.domain.comment.CommentUpdateDomain;

/**
 * Интерфейс для сервисов записи комментариев.
 */
public interface CommentWriteServicePort {
    CommentCreateDomain createComment(CommentCreateDomain commentDomainModel);
    CommentUpdateDomain updateComment(CommentUpdateDomain commentDomainModel);
    CommentDeleteDomain deleteComment(CommentDeleteDomain commentDomainModel);
}
