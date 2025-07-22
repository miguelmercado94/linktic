package com.linktic.test.productos_service.interfaces.exception;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDateTime;

@Getter
@Setter
public class ApiError implements Serializable {

    private int code;

    private String url;

    private String backendMessage;

    private String message;

    private String method;

    private LocalDateTime timestamp;
}

