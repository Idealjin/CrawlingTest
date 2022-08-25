package com.sangjin.crawling.app.app.common;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ApiResponse<T> {
    private ApiStatus status;
    private T data;

}
