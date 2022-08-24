package com.sangjin.crawling.app.common;

import lombok.Getter;

public enum ApiStatus {
    SUCCESS("SUCCESS"),
    FAIL("FAIL"),
    ;

    @Getter
    private String status;

    ApiStatus(String status){
        this.status = status;
    }
}
