package com.github.kazimbayram.groove.service;

import com.github.kazimbayram.groove.model.SurveyAnswerModel;

public interface NetPromoterScoreCalculatorService {
    void calculateAndUpdate(SurveyAnswerModel model);
}
