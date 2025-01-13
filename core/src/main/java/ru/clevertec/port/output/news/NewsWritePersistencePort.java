package ru.clevertec.port.output.news;

import ru.clevertec.domain.news.NewsCreateDomain;
import ru.clevertec.domain.news.NewsUpdateDomain;

import java.util.UUID;

/**
 * Интерфейс для портов записи новостей в источник данных.
 */
public interface NewsWritePersistencePort {
    NewsCreateDomain saveNews(NewsCreateDomain newsDomainModel);

    NewsUpdateDomain updateNews(NewsUpdateDomain newsDomainModel);

    void deleteNews(UUID id);
}

