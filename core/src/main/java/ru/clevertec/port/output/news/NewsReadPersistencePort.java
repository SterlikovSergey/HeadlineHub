package ru.clevertec.port.output.news;

import ru.clevertec.domain.news.NewsCreateDomain;

import java.util.List;
import java.util.UUID;

/**
 * Интерфейс для портов чтения новостей из источника данных.
 */
public interface NewsReadPersistencePort {
    /**
     * Выполняет полнотекстовый поиск новостей по заголовку и тексту.
     *
     * @param query    строка для поиска
     * @return страница с результатами поиска в доменной модели
     */
    List<NewsCreateDomain> searchNewsByQuery(String query, int page, int size);

    /**
     * Получает новость по идентификатору.
     *
     * @param id идентификатор новости
     * @return новость в доменной модели
     */
    NewsCreateDomain getNewsById(UUID id);

    /**
     * Получает все новости.
     *
     * @return список новостей в доменной модели
     */
    List<NewsCreateDomain> getAllNews();
}
