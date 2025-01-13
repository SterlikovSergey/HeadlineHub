package ru.clevertec.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;
import ru.clevertec.adapter.input.web.news.dto.request.NewsCreateRequestDto;
import ru.clevertec.adapter.input.web.news.dto.response.NewsResponseDto;
import ru.clevertec.adapter.input.web.news.dto.request.NewsUpdateRequestDto;
import ru.clevertec.adapter.output.jpa.entity.NewsEntity;
import ru.clevertec.domain.news.NewsCreateDomain;
import ru.clevertec.domain.news.NewsUpdateDomain;

/**
 * Интерфейс для маппинга между DTO, доменной моделью и сущностью новостей.
 * Использует MapStruct для автоматической генерации мапперов.
 */
@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface NewsMapper {

    /**
     * Преобразует DTO для создания новости в доменную модель.
     *
     * @param dto DTO для создания новости
     * @return доменная модель для создания новости
     */
    NewsCreateDomain toCreateDomain(NewsCreateRequestDto dto);

    /**
     * Преобразует DTO для обновления новости в доменную модель.
     *
     * @param dto DTO для обновления новости
     * @return доменная модель для обновления новости
     */
    NewsUpdateDomain toUpdateDomain(NewsUpdateRequestDto dto);

    /**
     * Преобразует доменную модель новости в сущность для сохранения в базе данных.
     *
     * @param domain доменная модель новости
     * @return сущность новости
     */
    NewsEntity toEntity(NewsCreateDomain domain);

    /**
     * Преобразует доменную модель обновления новости в сущность для сохранения в базе данных.
     *
     * @param domain доменная модель обновления новости
     * @return сущность новости
     */
    NewsEntity toEntity(NewsUpdateDomain domain);

    /**
     * Преобразует сущность новости в доменную модель для создания.
     *
     * @param entity сущность новости
     * @return доменная модель для создания новости
     */
    NewsCreateDomain toCreateDomain(NewsEntity entity);

    /**
     * Преобразует сущность новости в доменную модель для обновления.
     *
     * @param entity сущность новости
     * @return доменная модель для обновления новости
     */
    NewsUpdateDomain toUpdateDomain(NewsEntity entity);

    /**
     * Преобразует доменную модель в DTO ответа для новости.
     *
     * @param domain доменная модель новости
     * @return DTO ответа с информацией о новости
     */
    NewsResponseDto toResponseDto(NewsCreateDomain domain);

    /**
     * Преобразует доменную модель обновления в DTO ответа для новости.
     *
     * @param domain доменная модель обновления новости
     * @return DTO ответа с информацией о новости
     */
    NewsResponseDto toResponseDto(NewsUpdateDomain domain);

    /**
     * Обновляет существующую сущность новости с учетом null значений из DTO.
     *
     * @param dto  DTO для обновления новости
     * @param news существующая сущность новости для обновления
     * @return обновленная сущность новости
     */
    NewsEntity updateWithNull(NewsUpdateRequestDto dto, @MappingTarget NewsEntity news);
}

