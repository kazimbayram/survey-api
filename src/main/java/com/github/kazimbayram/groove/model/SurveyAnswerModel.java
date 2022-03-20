package com.github.kazimbayram.groove.model;

import lombok.Data;

import javax.validation.constraints.*;

@Data
public class SurveyAnswerModel {

    private Integer id;

    @Min(value = 1, message = "Score should be between 1 and 10")
    @Max(value = 10, message = "Score should be between 1 and 10")
    private int score;


    private String feedback;

    private Integer topicId;
}
