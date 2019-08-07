package com.internshipSoftServe.eshop.exeptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class NotFoundExeptions extends RuntimeException {
    public NotFoundExeptions(){
        super("Resource Not Found");
    }
}
