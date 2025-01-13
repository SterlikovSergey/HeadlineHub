package ru.clevertec.adapter;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import ru.clevertec.adapter.output.jpa.entity.CommentEntity;
import ru.clevertec.adapter.output.jpa.repository.CommentRepository;
import ru.clevertec.domain.comment.CommentCreateDomain;
import ru.clevertec.mapper.CommentMapper;
import ru.clevertec.port.output.comment.CommentReadPersistencePort;

import java.util.List;
import java.util.UUID;

/**
 * Адаптер для операций чтения комментариев, использующий JPA.
 * Этот класс предоставляет методы для получения комментариев из базы данных,
 * в том числе с пагинацией и полнотекстовым поиском.
 *
 * @see CommentReadPersistencePort
 */
@Service
@RequiredArgsConstructor
public class CommentReadJpaAdapter implements CommentReadPersistencePort {

    /**
     * Репозиторий для работы с комментариями через JPA.
     */
    private final CommentRepository repository;

    /**
     * Маппер для преобразования между доменными моделями и сущностями JPA.
     */
    private final CommentMapper mapper;

    /**
     * Извлекает комментарий из базы данных по его идентификатору.
     *
     * @param id идентификатор комментария для поиска
     * @return доменную модель комментария, или null, если комментарий не найден
     */
    @Override
    public CommentCreateDomain getCommentById(UUID id) {
        return repository.findById(id)
                .map(mapper::toCreateDomain)
                .orElse(null);
    }

    /**
     * Получает комментарии для определенной новости с пагинацией.
     *
     * @param newsId идентификатор новости
     * @param page   номер страницы
     * @param size   размер страницы
     * @return список комментариев в формате доменной модели
     */
    @Override
    public List<CommentCreateDomain> getCommentsByNewsId(UUID newsId, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<CommentEntity> commentsPage = repository.findByNewsId(newsId, pageable);
        return commentsPage.getContent().stream()
                .map(mapper::toCreateDomain)
                .toList();
    }

    /**
     * Выполняет полнотекстовый поиск комментариев по заданному запросу с пагинацией.
     *
     * @param query строка запроса для поиска
     * @param page  номер страницы
     * @param size  размер страницы
     * @return список найденных комментариев в формате доменной модели
     */
    @Override
    public List<CommentCreateDomain> searchComments(String query, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<CommentEntity> commentsPage = repository.searchByQuery(query, pageable);
        return commentsPage.getContent().stream()
                .map(mapper::toCreateDomain)
                .toList();
    }
}