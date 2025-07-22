package com.linktic.test.inventario_service.interfaces.exception;

import lombok.Getter;

@Getter
public enum ErrorCode {


    INTERNAL_ERROR(3000),

    BAD_REQUEST(4000);

    private final int code;

    ErrorCode(int code) {
        this.code = code;
    }
}
