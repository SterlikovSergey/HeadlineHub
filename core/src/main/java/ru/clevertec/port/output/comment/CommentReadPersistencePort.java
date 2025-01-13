package ru.clevertec.port.output.comment;

import ru.clevertec.domain.comment.CommentCreateDomain;

import java.util.List;
import java.util.UUID;

/**
 * Интерфейс для портов чтения комментариев из источника данных.
 */
public interface CommentReadPersistencePort {
    CommentCreateDomain getCommentById(UUID id);

    List<CommentCreateDomain> getCommentsByNewsId(UUID newsId, int page, int size);

    List<CommentCreateDomain> searchComments(String query, int page, int size);
}
