package com.github.kazimbayram.groove.model;

import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Data
public class SurveyQuestionModel {

    @Min(0)
    private int id;

    @NotNull
    private String topic;

    @NotNull
    private String question;
}
