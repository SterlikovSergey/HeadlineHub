package ru.clevertec.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;
import ru.clevertec.adapter.input.web.comment.dto.request.CommentCreateRequestDto;
import ru.clevertec.adapter.input.web.comment.dto.response.CommentResponseDto;
import ru.clevertec.adapter.input.web.comment.dto.request.CommentUpdateRequestDto;
import ru.clevertec.adapter.output.jpa.entity.CommentEntity;
import ru.clevertec.domain.comment.CommentCreateDomain;
import ru.clevertec.domain.comment.CommentUpdateDomain;

import java.time.LocalDateTime;

/**
 * Интерфейс для маппинга между DTO, доменной моделью и сущностью комментариев.
 * Использует MapStruct для автоматической генерации мапперов.
 */
@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface CommentMapper {

    /**
     * Преобразует DTO для создания комментария в доменную модель.
     *
     * @param dto DTO для создания комментария
     * @return доменная модель для создания комментария
     */
    default CommentCreateDomain toCreateDomain(CommentCreateRequestDto dto) {
        CommentCreateDomain domain = new CommentCreateDomain();
        domain.setUsername(dto.username());
        domain.setText(dto.text());
        domain.setNewsId(dto.newsId());
        domain.setCreatedAt(LocalDateTime.now()); // Установить текущее время создания
        return domain;
    }

    /**
     * Преобразует DTO для обновления комментария в доменную модель.
     *
     * @param dto DTO для обновления комментария
     * @return доменная модель для обновления комментария
     */
    CommentUpdateDomain toUpdateDomain(CommentUpdateRequestDto dto);

    /**
     * Преобразует доменную модель комментария в сущность для сохранения в базе данных.
     *
     * @param domain доменная модель комментария
     * @return сущность комментария
     */
    CommentEntity toEntity(CommentCreateDomain domain);

    /**
     * Преобразует доменную модель обновления комментария в сущность для сохранения в базе данных.
     *
     * @param domain доменная модель обновления комментария
     * @return сущность комментария
     */
    CommentEntity toEntity(CommentUpdateDomain domain);

    /**
     * Преобразует сущность комментария в доменную модель для создания.
     *
     * @param entity сущность комментария
     * @return доменная модель для создания комментария
     */
    CommentCreateDomain toCreateDomain(CommentEntity entity);

    /**
     * Преобразует сущность комментария в доменную модель для обновления.
     *
     * @param entity сущность комментария
     * @return доменная модель для обновления комментария
     */
    CommentUpdateDomain toUpdateDomain(CommentEntity entity);

    /**
     * Преобразует доменную модель в DTO ответа для комментария.
     *
     * @param domain доменная модель комментария
     * @return DTO ответа с информацией о комментарии
     */
    CommentResponseDto toResponseDto(CommentCreateDomain domain);

    /**
     * Преобразует доменную модель обновления в DTO ответа для комментария.
     *
     * @param domain доменная модель обновления комментария
     * @return DTO ответа с информацией о комментарии
     */
    CommentResponseDto toResponseDto(CommentUpdateDomain domain);

    /**
     * Обновляет существующую сущность комментария с учетом null значений из DTO.
     *
     * @param dto DTO для обновления комментария
     * @param comment существующая сущность комментария для обновления
     * @return обновленная сущность комментария
     */
    CommentEntity updateWithNull(CommentUpdateRequestDto dto, @MappingTarget CommentEntity comment);
}