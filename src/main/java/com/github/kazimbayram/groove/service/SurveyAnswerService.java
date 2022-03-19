package com.github.kazimbayram.groove.service;

import com.github.kazimbayram.groove.model.SurveyAnswerModel;

import java.util.List;


public interface SurveyAnswerService {
    void saveAnswerWithTopicId(int id, SurveyAnswerModel answerModel);

    List<SurveyAnswerModel> getAnswersByTopicId(int topicId);
}
