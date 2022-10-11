package com.implementationcms.domain.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class DraftNotFoundException extends RuntimeException{
    public DraftNotFoundException(Long postId) {
        super(String.format("Cannot find draft of post [%d]", postId));
    }
}
