package ru.clevertec.adapter;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import ru.clevertec.adapter.output.jpa.entity.NewsEntity;
import ru.clevertec.adapter.output.jpa.repository.NewsRepository;
import ru.clevertec.domain.news.NewsCreateDomain;
import ru.clevertec.mapper.NewsMapper;
import ru.clevertec.port.output.news.NewsReadPersistencePort;

import java.util.List;
import java.util.UUID;

/**
 * Адаптер для операций чтения новостей, использующий JPA.
 * Этот класс предоставляет реализацию для извлечения новостей из базы данных,
 * преобразуя их в доменные модели.
 *
 * @see NewsReadPersistencePort
 */
@Service
@RequiredArgsConstructor
public class NewsReadJpaAdapter implements NewsReadPersistencePort {

    /**
     * Репозиторий для работы с новостями через JPA.
     */
    private final NewsRepository repository;

    /**
     * Маппер для преобразования между доменными моделями и сущностями JPA.
     */
    @Qualifier("newsMapper")
    private final NewsMapper mapper;

    /**
     * Выполняет полнотекстовый поиск новостей по заданному запросу.
     *
     * @param query строка запроса для поиска
     * @param page  номер страницы
     * @param size  размер страницы
     * @return страница с результатами поиска в формате доменной модели
     */
    @Override
    public List<NewsCreateDomain> searchNewsByQuery(String query, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<NewsEntity> newsPage = repository.searchByQuery(query, pageable);
        return newsPage.getContent().stream()
                .map(mapper::toCreateDomain)
                .toList();
    }

    /**
     * Извлекает новость из базы данных по её идентификатору.
     *
     * @param id идентификатор новости для поиска
     * @return доменную модель новости, или null, если новость не найдена
     */
    @Override
    public NewsCreateDomain getNewsById(UUID id) {
        return repository.findById(id)
                .map(mapper::toCreateDomain)
                .orElse(null);
    }

    /**
     * Извлекает все новости из базы данных.
     *
     * @return список всех новостей в формате доменной модели
     */
    @Override
    public List<NewsCreateDomain> getAllNews() {
        return repository.findAll().stream()
                .map(mapper::toCreateDomain)
                .toList();
    }
}