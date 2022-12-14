package com.sangjin.crawling.app.app.controller;

import com.sangjin.crawling.app.app.common.ApiResponse;
import com.sangjin.crawling.app.app.common.ApiStatus;
import com.sangjin.crawling.app.app.service.CrawlingService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class Controller {

    @GetMapping("/crawling")
    public ApiResponse bookCrawling() {
        return new ApiResponse(ApiStatus.SUCCESS, CrawlingService.crawling());
    }

    @GetMapping("/test")
    public ApiResponse justTest(){
        return new ApiResponse(ApiStatus.SUCCESS, CrawlingService.justTest());
    }

}