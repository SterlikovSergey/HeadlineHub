package ru.clevertec.adapter.input.web.news.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.clevertec.adapter.input.web.news.dto.request.NewsCreateRequestDto;
import ru.clevertec.adapter.input.web.news.dto.request.NewsDeleteRequestDto;
import ru.clevertec.adapter.input.web.news.dto.request.NewsUpdateRequestDto;
import ru.clevertec.adapter.input.web.news.dto.response.NewsResponseDto;
import ru.clevertec.client.CoreFeignClient;
import ru.clevertec.domain.news.NewsCreateDomain;
import ru.clevertec.domain.news.NewsDeleteDomain;
import ru.clevertec.domain.news.NewsUpdateDomain;
import ru.clevertec.logger.annotation.MonitorLogger;
import ru.clevertec.mapper.NewsMapper;
import ru.clevertec.port.input.news.NewsReadServicePort;
import ru.clevertec.port.input.news.NewsWriteServicePort;

import java.util.List;
import java.util.UUID;

/**
 * REST контроллер для управления новостями.
 * Обрабатывает HTTP запросы для CRUD операций с новостями.
 */
@RestController
@RequestMapping("/api/news")
@RequiredArgsConstructor
public class NewsController {

    private final CoreFeignClient coreFeignClient;
    private final NewsReadServicePort newsReadService;
    private final NewsWriteServicePort newsWriteService;
    private final NewsMapper newsMapper;

    /**
     * Создает новую новость.
     *
     * @param requestDto DTO с данными для создания новости
     * @return DTO с информацией о созданной новости
     */
    @MonitorLogger
    @PostMapping
    public NewsResponseDto createNews(@RequestBody NewsCreateRequestDto requestDto) {
        NewsCreateDomain domain = newsMapper.toCreateDomain(requestDto);
        return newsMapper.toResponseDto(newsWriteService.createNews(domain));
    }

    /**
     * Обновляет существующую новость.
     *
     * @param requestDto DTO с данными для обновления новости
     * @return DTO с информацией об обновленной новости
     */
    @PutMapping
    public NewsResponseDto updateNews(@RequestBody NewsUpdateRequestDto requestDto) {
        NewsUpdateDomain domain = newsMapper.toUpdateDomain(requestDto);
        return newsMapper.toResponseDto(newsWriteService.updateNews(domain));
    }

    /**
     * Удаляет новость по её идентификатору.
     *
     * @param requestDto DTO с идентификатором новости для удаления
     */
    @DeleteMapping
    public void deleteNews(@RequestBody NewsDeleteRequestDto requestDto) {
        NewsDeleteDomain domain = new NewsDeleteDomain();
        domain.setId(requestDto.id());
        newsWriteService.deleteNews(domain);
    }

    /**
     * Получает новость по её идентификатору.
     *
     * @param id идентификатор новости
     * @return DTO с информацией о новости
     */
    @GetMapping("/{id}")
    public NewsResponseDto getNews(@PathVariable UUID id) {
        return newsMapper.toResponseDto(newsReadService.readNews(id));
    }

    /**
     * Получает все новости.
     *
     * @return список DTO с информацией о новостях
     */
    @GetMapping
    public List<NewsResponseDto> getAllNews() {
        return newsReadService.readAllNews().stream().map(newsMapper::toResponseDto).toList();
    }

    /**
     * Выполняет полнотекстовый поиск новостей по заданному запросу с пагинацией.
     *
     * @param query строка запроса
     * @param page  номер страницы, по умолчанию 0
     * @param size  размер страницы, по умолчанию 10
     * @return страница с результатами поиска в формате DTO
     */
    @GetMapping("/search")
    public List<NewsResponseDto> searchNews(@RequestParam String query,
                                            @RequestParam(defaultValue = "0") int page,
                                            @RequestParam(defaultValue = "10") int size) {
        return newsReadService.searchNews(query, page,size).stream()
        .map(newsMapper::toResponseDto)
                .toList();
    }
}

