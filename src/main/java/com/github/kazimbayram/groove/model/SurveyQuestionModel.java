package com.github.kazimbayram.groove.model;

import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class SurveyQuestionModel {

    private Integer id;

    @NotNull
    @NotBlank(message = "topic should have a value!")
    private String topic;

    @NotBlank(message = "question should have a value!")
    private String question;
}
