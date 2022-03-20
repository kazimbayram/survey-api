package com.github.kazimbayram.groove.model;

import lombok.Data;


@Data
public class SurveyTopicScoreboardModel {

    private Integer topicId;

    private Integer totalSubmission;

    private Integer totalDetractors;

    private Integer totalPassives;

    private Integer totalPromoters;
}
