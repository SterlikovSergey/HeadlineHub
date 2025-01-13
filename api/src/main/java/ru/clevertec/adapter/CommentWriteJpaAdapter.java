package ru.clevertec.adapter;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.clevertec.adapter.input.web.comment.dto.request.CommentUpdateRequestDto;
import ru.clevertec.adapter.output.jpa.entity.CommentEntity;
import ru.clevertec.adapter.output.jpa.repository.CommentRepository;
import ru.clevertec.domain.comment.CommentCreateDomain;
import ru.clevertec.domain.comment.CommentUpdateDomain;
import ru.clevertec.mapper.CommentMapper;
import ru.clevertec.port.output.comment.CommentWritePersistencePort;

import java.util.UUID;

/**
 * Адаптер для операций записи комментариев, использующий JPA.
 * Этот класс реализует функциональность для создания, обновления и удаления комментариев,
 * переводя доменные объекты в сущности JPA и обратно.
 *
 * @see CommentWritePersistencePort
 */
@Service
@RequiredArgsConstructor
public class CommentWriteJpaAdapter implements CommentWritePersistencePort {

    /**
     * Репозиторий для работы с комментариями через JPA.
     */
    private final CommentRepository repository;

    /**
     * Маппер для преобразования между доменными моделями и сущностями JPA.
     */
    private final CommentMapper mapper;

    /**
     * Сохраняет новый комментарий в базе данных.
     *
     * @param commentDomainModel доменная модель комментария для сохранения
     * @return доменную модель сохраненного комментария
     */
    @Override
    public CommentCreateDomain saveComment(CommentCreateDomain commentDomainModel) {
        CommentEntity entity = mapper.toEntity(commentDomainModel);
        CommentEntity savedEntity = repository.save(entity);
        return mapper.toCreateDomain(savedEntity);
    }

    /**
     * Обновляет существующий комментарий в базе данных.
     *
     * @param commentDomainModel доменная модель комментария для обновления
     * @return обновленную доменную модель комментария
     * @throws RuntimeException если комментарий не найден
     */
    @Override
    public CommentUpdateDomain updateComment(CommentUpdateDomain commentDomainModel) {
        CommentEntity entity = repository.findById(commentDomainModel.getId())
                .orElseThrow(() -> new RuntimeException("Comment not found"));
        CommentEntity updatedEntity = mapper.updateWithNull(
                new CommentUpdateRequestDto(commentDomainModel.getId(), commentDomainModel.getUsername(), commentDomainModel.getText(), commentDomainModel.getNewsId()),
                entity
        );
        return mapper.toUpdateDomain(repository.save(updatedEntity));
    }

    /**
     * Удаляет комментарий из базы данных по идентификатору.
     *
     * @param id идентификатор комментария для удаления
     */
    @Override
    public void deleteComment(UUID id) {
        repository.deleteById(id);
    }
}