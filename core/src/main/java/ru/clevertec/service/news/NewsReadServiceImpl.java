package ru.clevertec.service.news;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.clevertec.domain.news.NewsCreateDomain;
import ru.clevertec.port.input.news.NewsReadServicePort;
import ru.clevertec.port.output.news.NewsReadPersistencePort;

import java.util.List;
import java.util.UUID;

/**
 * Сервис для выполнения операций чтения новостей.
 * Реализует интерфейс {@link NewsReadServicePort}.
 */
@Service
@RequiredArgsConstructor
public class NewsReadServiceImpl implements NewsReadServicePort {

    private final NewsReadPersistencePort newsReadPersistencePort;

    /**
     * Выполняет полнотекстовый поиск новостей по заданному запросу.
     *
     * @param query    строка для поиска
     * @return страница с результатами поиска в формате доменной модели
     */
    @Override
    public List<NewsCreateDomain> searchNews(String query, int page, int size) {
        return newsReadPersistencePort.searchNewsByQuery(query, page, size);
    }

    /**
     * Читает новость по её идентификатору.
     *
     * @param id идентификатор новости
     * @return объект {@link NewsCreateDomain} с данными новости, или null, если новость не найдена
     */
    @Override
    public NewsCreateDomain readNews(UUID id) {
        return newsReadPersistencePort.getNewsById(id);
    }

    /**
     * Читает все новости.
     *
     * @return список всех новостей как объектов {@link NewsCreateDomain}
     */
    @Override
    public List<NewsCreateDomain> readAllNews() {
        return newsReadPersistencePort.getAllNews();
    }
}
