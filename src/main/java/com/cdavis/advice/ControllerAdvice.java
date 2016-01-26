package com.cdavis.advice;

import com.cdavis.errorhandling.ErrorHandler;
import com.cdavis.errorhandling.ResponseError;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@org.springframework.web.bind.annotation.ControllerAdvice
public class ControllerAdvice extends ResponseEntityExceptionHandler {

    private final ErrorHandler handler;

    @Autowired
    public ControllerAdvice(ErrorHandler handler) {
        this.handler = handler;
    }

    @ExceptionHandler
    public ResponseEntity<Object> handle(Exception e, WebRequest request) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        ResponseError error = handler.process(e.getClass().cast(e));
        return handleExceptionInternal(e, error, headers,
                HttpStatus.valueOf(error.getHttpResponseCode()), request);
    }

}
