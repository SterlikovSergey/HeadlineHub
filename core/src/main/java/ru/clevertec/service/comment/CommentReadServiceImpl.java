package ru.clevertec.service.comment;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.clevertec.domain.comment.CommentCreateDomain;
import ru.clevertec.port.input.comment.CommentReadServicePort;
import ru.clevertec.port.output.comment.CommentReadPersistencePort;

import java.util.List;
import java.util.UUID;

/**
 * Сервис для выполнения операций чтения комментариев.
 * Реализует интерфейс {@link CommentReadServicePort}.
 */
@Service
@RequiredArgsConstructor
public class CommentReadServiceImpl implements CommentReadServicePort {

    private final CommentReadPersistencePort commentReadPersistencePort;

    @Override
    public CommentCreateDomain readComment(UUID id) {
        return commentReadPersistencePort.getCommentById(id);
    }

    @Override
    public List<CommentCreateDomain> readCommentsByNewsId(UUID newsId, int page, int size) {
        return commentReadPersistencePort.getCommentsByNewsId(newsId, page, size);
    }

    @Override
    public List<CommentCreateDomain> searchComments(String query, int page, int size) {
        return commentReadPersistencePort.searchComments(query, page, size);
    }
}
