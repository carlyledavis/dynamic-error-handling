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

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

@org.springframework.web.bind.annotation.ControllerAdvice
public class ControllerAdvice extends ResponseEntityExceptionHandler {

    private final ErrorHandler handler;

    @Autowired
    public ControllerAdvice(ErrorHandler handler) {
        this.handler = handler;
    }

    @ExceptionHandler
    public ResponseEntity<Object> handle(Exception e, WebRequest request) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);


        try {
            Method method = handler.getClass().getDeclaredMethod( "process", e.getClass());
            ResponseError responseCode = (ResponseError) method.invoke(handler, e);
            return handleExceptionInternal(e, responseCode, headers,
                    HttpStatus.valueOf(responseCode.getHttpResponseCode()), request);
        } catch (NoSuchMethodException e1) {
            Method method = handler.getClass().getDeclaredMethod( "process", Exception.class);
            ResponseError responseCode = (ResponseError) method.invoke(handler, e);

            return handleExceptionInternal(e, method.invoke(handler, e), headers,
                    HttpStatus.valueOf(responseCode.getHttpResponseCode()), request);
        } catch (InvocationTargetException e1) {
            e1.printStackTrace();
        } catch (IllegalAccessException e1) {
            e1.printStackTrace();
        }


        return null;
    }

}
