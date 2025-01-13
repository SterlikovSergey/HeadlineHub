package ru.clevertec.port.input.comment;

import ru.clevertec.domain.comment.CommentCreateDomain;

import java.util.List;
import java.util.UUID;

/**
 * Интерфейс для сервисов чтения комментариев.
 */
public interface CommentReadServicePort {
    CommentCreateDomain readComment(UUID id);
    List<CommentCreateDomain> readCommentsByNewsId(UUID newsId, int page, int size);
    List<CommentCreateDomain> searchComments(String query, int page, int size);
}
