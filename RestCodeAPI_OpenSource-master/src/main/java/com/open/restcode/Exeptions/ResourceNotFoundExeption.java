package com.open.restcode.Exeptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ResourceNotFoundExeption extends RuntimeException {


    public ResourceNotFoundExeption(String banckAcount) {
        super();
    }

    public ResourceNotFoundExeption(String user, String name, String message) {
        super(message);
    }

    public ResourceNotFoundExeption(String message, Throwable cause) {
        super(message, cause);
    }

    public ResourceNotFoundExeption(String resourceName, String fieldName, Object fieldValue) {
        super(String.format("Resource %s not found for %s with value %s",
                resourceName, fieldName, fieldValue));
    }
}
