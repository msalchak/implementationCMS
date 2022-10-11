package com.implementationcms.domain.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class PostModelNotFoundException extends RuntimeException{
    public PostModelNotFoundException(Long postModelId) {
        super(String.format("Cannot find content model [%d]", postModelId));
    }
}
