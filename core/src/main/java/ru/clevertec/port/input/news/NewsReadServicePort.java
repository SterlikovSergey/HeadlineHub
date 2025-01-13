package ru.clevertec.port.input.news;

import ru.clevertec.domain.news.NewsCreateDomain;

import java.util.List;
import java.util.UUID;

/**
 * Интерфейс для сервисов чтения комментариев.
 */
public interface NewsReadServicePort {
    List<NewsCreateDomain> searchNews(String query, int page, int size);

    NewsCreateDomain readNews(UUID id);

    List<NewsCreateDomain> readAllNews();
}
