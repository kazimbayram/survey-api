package com.github.kazimbayram.groove.model;

import lombok.Data;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
public class SurveyAnswerModel {

    private int id;

    @Min(1)
    @Max(10)
    private int score;

    @NotNull
    @NotEmpty
    private String feedback;

    private int topicId;
}
