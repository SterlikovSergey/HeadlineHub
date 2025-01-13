package ru.clevertec.service.comment;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.clevertec.domain.comment.CommentCreateDomain;
import ru.clevertec.domain.comment.CommentDeleteDomain;
import ru.clevertec.domain.comment.CommentUpdateDomain;
import ru.clevertec.port.input.comment.CommentWriteServicePort;
import ru.clevertec.port.output.comment.CommentWritePersistencePort;

/**
 * Сервис для выполнения операций записи комментариев.
 * Реализует интерфейс {@link CommentWriteServicePort}.
 */
@Service
@RequiredArgsConstructor
public class CommentWriteServiceImpl implements CommentWriteServicePort {

    private final CommentWritePersistencePort commentWritePersistencePort;

    @Override
    public CommentCreateDomain createComment(CommentCreateDomain commentDomainModel) {
        return commentWritePersistencePort.saveComment(commentDomainModel);
    }

    @Override
    public CommentUpdateDomain updateComment(CommentUpdateDomain commentDomainModel) {
        return commentWritePersistencePort.updateComment(commentDomainModel);
    }

    @Override
    public CommentDeleteDomain deleteComment(CommentDeleteDomain commentDomainModel) {
        commentWritePersistencePort.deleteComment(commentDomainModel.getId());
        return commentDomainModel;
    }
}
