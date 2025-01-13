package ru.clevertec.adapter.output.jpa.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.clevertec.adapter.output.jpa.entity.NewsEntity;

import java.util.UUID;

public interface NewsRepository extends JpaRepository<NewsEntity, UUID>, JpaSpecificationExecutor<NewsEntity> {

    /**
     * Выполняет полнотекстовый поиск новостей по заголовку и тексту.
     *
     * @param query    строка для поиска
     * @param pageable объект страницы для пагинации
     * @return страница с результатами поиска
     */
    @Query(value = "SELECT n FROM NewsEntity n WHERE to_tsvector('english', n.title || ' ' || n.text) @@ plainto_tsquery(:query)",
            nativeQuery = true)
    Page<NewsEntity> searchByQuery(@Param("query") String query, Pageable pageable);

    /**
     * Получает новости с пагинацией.
     *
     * @param pageable объект страницы для пагинации
     * @return страница новостей
     */
    Page<NewsEntity> findAll(Pageable pageable);
}