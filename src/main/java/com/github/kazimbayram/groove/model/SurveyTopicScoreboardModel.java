package com.github.kazimbayram.groove.model;

import lombok.Data;


@Data
public class SurveyTopicScoreboardModel {
    private int topicId;

    private int totalSubmission;

    private int totalDetractors;

    private int totalPassives;

    private int totalPromoters;
}
