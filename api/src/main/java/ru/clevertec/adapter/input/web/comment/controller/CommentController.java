package ru.clevertec.adapter.input.web.comment.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.clevertec.adapter.input.web.comment.dto.request.CommentCreateRequestDto;
import ru.clevertec.adapter.input.web.comment.dto.request.CommentDeleteRequestDto;
import ru.clevertec.adapter.input.web.comment.dto.request.CommentUpdateRequestDto;
import ru.clevertec.adapter.input.web.comment.dto.response.CommentResponseDto;
import ru.clevertec.domain.comment.CommentCreateDomain;
import ru.clevertec.domain.comment.CommentDeleteDomain;
import ru.clevertec.domain.comment.CommentUpdateDomain;
import ru.clevertec.port.input.comment.CommentReadServicePort;
import ru.clevertec.port.input.comment.CommentWriteServicePort;
import ru.clevertec.mapper.CommentMapper;

import java.util.List;
import java.util.UUID;

/**
 * REST контроллер для управления комментариями.
 * Обрабатывает HTTP запросы для CRUD операций с комментариями.
 */
@RestController
@RequestMapping("/api/comment")
@RequiredArgsConstructor
public class CommentController {

    private final CommentReadServicePort commentReadService;
    private final CommentWriteServicePort commentWriteService;
    private final CommentMapper commentMapper;

    /**
     * Создает новый комментарий.
     *
     * @param requestDto DTO с данными для создания комментария
     * @return DTO с информацией о созданном комментарии
     */
    @PostMapping
    public CommentResponseDto createComment(@RequestBody CommentCreateRequestDto requestDto) {
        CommentCreateDomain domain = commentMapper.toCreateDomain(requestDto);
        return commentMapper.toResponseDto(commentWriteService.createComment(domain));
    }

    /**
     * Обновляет существующий комментарий.
     *
     * @param requestDto DTO с данными для обновления комментария
     * @return DTO с информацией об обновленном комментарии
     */
    @PutMapping
    public CommentResponseDto updateComment(@RequestBody CommentUpdateRequestDto requestDto) {
        CommentUpdateDomain domain = commentMapper.toUpdateDomain(requestDto);
        return commentMapper.toResponseDto(commentWriteService.updateComment(domain));
    }

    /**
     * Удаляет комментарий по его идентификатору.
     *
     * @param requestDto DTO с идентификатором комментария для удаления
     */
    @DeleteMapping
    public void deleteComment(@RequestBody CommentDeleteRequestDto requestDto) {
        CommentDeleteDomain domain = new CommentDeleteDomain();
        domain.setId(requestDto.id());
        commentWriteService.deleteComment(domain);
    }

    /**
     * Получает комментарий по его идентификатору.
     *
     * @param id идентификатор комментария
     * @return DTO с информацией о комментарии
     */
    @GetMapping("/{id}")
    public CommentResponseDto getComment(@PathVariable UUID id) {
        return commentMapper.toResponseDto(commentReadService.readComment(id));
    }

    /**
     * Получает комментарии для конкретной новости с пагинацией.
     *
     * @param newsId идентификатор новости
     * @param page   номер страницы
     * @param size   размер страницы
     * @return список DTO с информацией о комментариях
     */
    @GetMapping("/news/{newsId}")
    public List<CommentResponseDto> getCommentsByNewsId(@PathVariable UUID newsId,
                                                        @RequestParam(defaultValue = "0") int page,
                                                        @RequestParam(defaultValue = "10") int size) {
        return commentReadService.readCommentsByNewsId(newsId, page, size).stream()
                .map(commentMapper::toResponseDto)
                .toList();
    }

    /**
     * Выполняет полнотекстовый поиск комментариев с пагинацией.
     *
     * @param query строка для поиска
     * @param page  номер страницы
     * @param size  размер страницы
     * @return список DTO с информацией о найденных комментариях
     */
    @GetMapping("/search")
    public List<CommentResponseDto> searchComments(@RequestParam String query,
                                                   @RequestParam(defaultValue = "0") int page,
                                                   @RequestParam(defaultValue = "10") int size) {
        return commentReadService.searchComments(query, page, size).stream()
                .map(commentMapper::toResponseDto)
                .toList();
    }
}