package com.cdavis.errorhandling;

import com.cdavis.exceptions.NoSuchEntityException;
import org.springframework.http.HttpStatus;

public class ErrorHandler {

    public String ENTITY_NOT_FOUND = "9000";

    public ResponseError process(NoSuchEntityException nsee ){
        return (new ResponseError(ENTITY_NOT_FOUND, "Item is not here", HttpStatus.NOT_FOUND.value()));
    }

    public ResponseError process(Exception e ){
        return (new ResponseError("1000", "Server Error Detected", HttpStatus.INTERNAL_SERVER_ERROR.value() ));
    }

}
