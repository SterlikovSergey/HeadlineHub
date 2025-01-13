package ru.clevertec.port.input.news;

import ru.clevertec.domain.news.NewsCreateDomain;
import ru.clevertec.domain.news.NewsDeleteDomain;
import ru.clevertec.domain.news.NewsUpdateDomain;

/**
 * Интерфейс для сервисов записи новостей.
 */
public interface NewsWriteServicePort {
    NewsCreateDomain createNews(NewsCreateDomain newsDomainModel);

    NewsUpdateDomain updateNews(NewsUpdateDomain newsDomainModel);

    NewsDeleteDomain deleteNews(NewsDeleteDomain newsDomainModel);
}
