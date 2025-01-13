package ru.clevertec.service.news;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.clevertec.domain.news.NewsCreateDomain;
import ru.clevertec.domain.news.NewsDeleteDomain;
import ru.clevertec.domain.news.NewsUpdateDomain;
import ru.clevertec.port.input.news.NewsWriteServicePort;
import ru.clevertec.port.output.news.NewsWritePersistencePort;

/**
 * Сервис для выполнения операций записи новостей.
 * Реализует интерфейс {@link NewsWriteServicePort}.
 */
@Service
@RequiredArgsConstructor
public class NewsWriteServiceImpl implements NewsWriteServicePort {

    private final NewsWritePersistencePort newsWritePersistencePort;

    /**
     * Создает новую новость.
     *
     * @param newsDomainModel объект доменной модели для создания новости
     * @return созданный объект {@link NewsCreateDomain}
     */
    @Override
    public NewsCreateDomain createNews(NewsCreateDomain newsDomainModel) {
        return newsWritePersistencePort.saveNews(newsDomainModel);
    }

    /**
     * Обновляет существующую новость.
     *
     * @param newsDomainModel объект доменной модели с обновленными данными новости
     * @return обновленный объект {@link NewsUpdateDomain}
     */
    @Override
    public NewsUpdateDomain updateNews(NewsUpdateDomain newsDomainModel) {
        return newsWritePersistencePort.updateNews(newsDomainModel);
    }

    /**
     * Удаляет новость.
     *
     * @param newsDomainModel объект доменной модели для удаления новости
     * @return объект {@link NewsDeleteDomain} для подтверждения удаления
     */
    @Override
    public NewsDeleteDomain deleteNews(NewsDeleteDomain newsDomainModel) {
        newsWritePersistencePort.deleteNews(newsDomainModel.getId());
        return newsDomainModel;
    }
}

