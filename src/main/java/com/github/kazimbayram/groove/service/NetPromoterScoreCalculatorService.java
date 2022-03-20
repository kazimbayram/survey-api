package com.github.kazimbayram.groove.service;

import com.github.kazimbayram.groove.enums.NpsType;
import com.github.kazimbayram.groove.model.SurveyTopicScoreboardModel;

public interface NetPromoterScoreCalculatorService {

    NpsType getByScore(int score);

    int calculateNpsScore(SurveyTopicScoreboardModel scoreboardModel);
}
