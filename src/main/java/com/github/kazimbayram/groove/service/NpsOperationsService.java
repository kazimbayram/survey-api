package com.github.kazimbayram.groove.service;

import com.github.kazimbayram.groove.model.SurveyAnswerModel;
import com.github.kazimbayram.groove.model.SurveyQuestionModel;

public interface NpsOperationsService {
    void calculateNewScore(SurveyAnswerModel model);

    void createNewScoreboard(SurveyQuestionModel model);
}
