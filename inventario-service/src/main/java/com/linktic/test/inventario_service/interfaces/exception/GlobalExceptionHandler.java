package com.linktic.test.inventario_service.interfaces.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;
import java.util.stream.Collectors;

@RestControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger log = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(Exception.class)
    public Mono<ResponseEntity<ApiError>> handleGenericException(ServerHttpRequest request, Exception ex) {
        log.error("❌ Error procesando la solicitud [{} {}]: {}",
                request.getMethod(),
                request.getURI(),
                ex.getMessage(),
                ex);

        ApiError apiError = new ApiError();
        apiError.setCode(ErrorCode.INTERNAL_ERROR.getCode());
        apiError.setBackendMessage(ex.getMessage());
        apiError.setUrl(request.getURI().toString());
        apiError.setMethod(request.getMethod().name());
        apiError.setMessage("Ocurrió un error interno inesperado");
        apiError.setTimestamp(LocalDateTime.now());

        return Mono.just(ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .contentType(MediaType.APPLICATION_JSON)
                .body(apiError));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Mono<ResponseEntity<ApiError>> handleValidationException(ServerHttpRequest request, MethodArgumentNotValidException ex) {
        String details = ex.getAllErrors()
                .stream()
                .map(DefaultMessageSourceResolvable::getDefaultMessage)
                .collect(Collectors.joining("; "));

        log.warn("⚠️ Error de validación [{} {}]: {}",
                request.getMethod(),
                request.getURI(),
                details);

        ApiError apiError = new ApiError();
        apiError.setCode(ErrorCode.BAD_REQUEST.getCode());
        apiError.setBackendMessage(details);
        apiError.setUrl(request.getURI().toString());
        apiError.setMethod(request.getMethod().name());
        apiError.setMessage("Solicitud mal formada. Verifique los campos.");
        apiError.setTimestamp(LocalDateTime.now());

        return Mono.just(ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .contentType(MediaType.APPLICATION_JSON)
                .body(apiError));
    }
}
