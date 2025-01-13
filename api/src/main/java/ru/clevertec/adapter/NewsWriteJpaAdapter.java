package ru.clevertec.adapter;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import ru.clevertec.adapter.input.web.news.dto.request.NewsUpdateRequestDto;
import ru.clevertec.adapter.output.jpa.entity.NewsEntity;
import ru.clevertec.adapter.output.jpa.repository.NewsRepository;
import ru.clevertec.domain.news.NewsCreateDomain;
import ru.clevertec.domain.news.NewsUpdateDomain;
import ru.clevertec.mapper.NewsMapper;
import ru.clevertec.port.output.news.NewsWritePersistencePort;

import java.util.UUID;

/**
 * Адаптер для операций записи новостей, использующий JPA.
 * Этот класс служит для реализации портов сохранения, обновления и удаления новостей,
 * переводя доменные объекты в сущности JPA и обратно.
 *
 * @see NewsWritePersistencePort
 */
@Service
@RequiredArgsConstructor
public class NewsWriteJpaAdapter implements NewsWritePersistencePort {

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
     * Сохраняет новую новость в базе данных.
     *
     * @param domainModel доменная модель новости для сохранения
     * @return доменную модель сохраненной новости
     */
    @Override
    public NewsCreateDomain saveNews(NewsCreateDomain domainModel) {
        NewsEntity entity = mapper.toEntity(domainModel);
        NewsEntity savedEntity = repository.save(entity);
        return mapper.toCreateDomain(savedEntity);
    }

    /**
     * Обновляет существующую новость в базе данных.
     *
     * @param domainModel доменная модель новости для обновления
     * @return обновленную доменную модель новости
     * @throws RuntimeException если новость не найдена
     */
    @Override
    public NewsUpdateDomain updateNews(NewsUpdateDomain domainModel) {
        NewsEntity entity = repository.findById(domainModel.getId())
                .orElseThrow(() -> new RuntimeException("News not found"));
        NewsEntity updatedEntity = mapper.updateWithNull(
                new NewsUpdateRequestDto(domainModel.getId(), domainModel.getTitle(), domainModel.getText()),
                entity
        );
        return mapper.toUpdateDomain(repository.save(updatedEntity));
    }

    /**
     * Удаляет новость из базы данных по идентификатору.
     *
     * @param id идентификатор новости для удаления
     */
    @Override
    public void deleteNews(UUID id) {
        repository.deleteById(id);
    }
}