package ru.clevertec.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import ru.clevertec.adapter.input.web.news.dto.request.NewsCreateRequestDto;


@FeignClient(name = "core-service", url = "${core-service.url}/api/core")
public interface CoreFeignClient {

    @PostMapping("/news")
    NewsCreateRequestDto createNews(@RequestBody NewsCreateRequestDto newsDto);
}