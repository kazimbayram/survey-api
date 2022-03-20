package com.github.kazimbayram.groove.exceptions;

import lombok.Data;

@Data
public class TopicNotFoundException extends RuntimeException {
    private final int id;

    public TopicNotFoundException(int id) {
        this.id = id;
    }

    @Override
    public String getMessage() {
        return String.format("Topic not found with Id:%d", id);
    }
}
