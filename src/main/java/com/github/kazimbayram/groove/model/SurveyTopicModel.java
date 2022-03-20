package com.github.kazimbayram.groove.model;

import lombok.Data;

@Data
public class SurveyTopicModel {

    public Integer id;

    public String question;

    public String topic;

    public int score;
}
