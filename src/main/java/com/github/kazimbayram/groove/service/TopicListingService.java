package com.github.kazimbayram.groove.service;

import com.github.kazimbayram.groove.model.SurveyQuestionModel;
import com.github.kazimbayram.groove.model.SurveyTopicListModel;
import com.github.kazimbayram.groove.model.SurveyTopicModel;

import java.util.List;
import java.util.Optional;

public interface TopicListingService {
    Optional<SurveyTopicModel> getTopicById(int id);
    
    List<SurveyTopicListModel> getAllTopics();

    boolean isAnyTopicWithId(int id);

    SurveyQuestionModel createNewTopic(SurveyQuestionModel model);

    SurveyQuestionModel updateTopicById(int topicId, SurveyQuestionModel model);

    void deleteTopicById(int topicId);
}
