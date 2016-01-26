package com.cdavis.exceptions;

import static java.lang.String.format;

public class NoSuchEntityException extends Exception {
    public NoSuchEntityException(String customerId) {
        super(format("Customer with id %s was not found", customerId));
    }
}
