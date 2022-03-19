package com.github.kazimbayram.groove.service.impl;

import com.github.kazimbayram.groove.entity.SurveyAnswerEntity;
import com.github.kazimbayram.groove.entity.SurveyTopicEntity;
import com.github.kazimbayram.groove.exceptions.TopicNotFoundException;
import com.github.kazimbayram.groove.model.SurveyAnswerModel;
import com.github.kazimbayram.groove.reporistory.SurveyAnswerRepository;
import com.github.kazimbayram.groove.service.SurveyAnswerService;
import com.github.kazimbayram.groove.service.TopicListingService;
import com.github.kazimbayram.groove.utility.Mapper;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import java.util.List;

@Service
public class SurveyAnswerServiceImpl implements SurveyAnswerService {

    private final TopicListingService topicListingService;
    private final SurveyAnswerRepository surveyAnswerRepository;
    private final EntityManager entityManager;
    private final Mapper mapper;

    public SurveyAnswerServiceImpl(TopicListingService topicListingService, SurveyAnswerRepository surveyAnswerRepository, EntityManager entityManager, Mapper mapper) {
        this.topicListingService = topicListingService;
        this.surveyAnswerRepository = surveyAnswerRepository;
        this.entityManager = entityManager;
        this.mapper = mapper;
    }

    @Override
    public void saveAnswerWithTopicId(int id, SurveyAnswerModel answerModel) {
        var isTopicExists = this.topicListingService.isAnyTopicWithId(id);

        if (!isTopicExists) {
            throw new TopicNotFoundException(id);
        }

        var entity = mapper.map(answerModel, SurveyAnswerEntity.class);
        entity.setTopic(entityManager.getReference(SurveyTopicEntity.class, id));

        surveyAnswerRepository.save(entity);

        // TODO calculation logic
    }

    @Override
    public List<SurveyAnswerModel> getAnswersByTopicId(int topicId) {
        var entities = surveyAnswerRepository.findByTopicId(topicId);
        return mapper.map(entities, SurveyAnswerModel.class);
    }
}
