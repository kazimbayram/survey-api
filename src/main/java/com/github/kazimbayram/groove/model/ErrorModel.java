package com.github.kazimbayram.groove.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ErrorModel {

    private int statusCode;

    private String errorMessage;
}
