package com.github.kazimbayram.groove.model;

import lombok.Data;

@Data
public class SurveyTopicModel {
    public int id;

    public String question;

    public String topic;

    public int score;
}
