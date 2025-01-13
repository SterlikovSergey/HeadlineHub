package ru.clevertec.adapter.output.jpa.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.clevertec.adapter.output.jpa.entity.CommentEntity;

import java.util.UUID;

public interface CommentRepository extends JpaRepository<CommentEntity, UUID>, JpaSpecificationExecutor<CommentEntity> {

    /**
     * Находит все комментарии для конкретной новости с пагинацией.
     *
     * @param newsId   идентификатор новости
     * @param pageable объект страницы для пагинации
     * @return страница с комментариями
     */
    Page<CommentEntity> findByNewsId(UUID newsId, Pageable pageable);

    /**
     * Выполняет полнотекстовый поиск комментариев по тексту.
     *
     * @param query    строка для поиска
     * @param pageable объект страницы для пагинации
     * @return страница с результатами поиска
     */
    @Query(value = "SELECT c FROM comment c WHERE to_tsvector('english', c.text) @@ plainto_tsquery(:query)",
            nativeQuery = true)
    Page<CommentEntity> searchByQuery(@Param("query") String query, Pageable pageable);
}