package com.github.kazimbayram.groove.service.impl;

import com.github.kazimbayram.groove.entity.SurveyTopicEntity;
import com.github.kazimbayram.groove.exceptions.TopicNotFoundException;
import com.github.kazimbayram.groove.model.SurveyQuestionModel;
import com.github.kazimbayram.groove.model.SurveyTopicListModel;
import com.github.kazimbayram.groove.model.SurveyTopicModel;
import com.github.kazimbayram.groove.reporistory.SurveyTopicRepository;
import com.github.kazimbayram.groove.service.TopicListingService;
import com.github.kazimbayram.groove.utility.Mapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class TopicListingServiceImpl implements TopicListingService {

    private final SurveyTopicRepository surveyTopicRepository;
    private final Mapper mapper;

    public TopicListingServiceImpl(SurveyTopicRepository surveyTopicRepository, Mapper mapper) {
        this.surveyTopicRepository = surveyTopicRepository;
        this.mapper = mapper;
    }

    @Override
    public Optional<SurveyTopicModel> getTopicById(int id) {
        var entity = surveyTopicRepository.findById(id);

        return entity.map(surveyTopicEntity -> mapper.map(surveyTopicEntity, SurveyTopicModel.class));
    }

    @Override
    public boolean isAnyTopicWithId(int id) {
        return surveyTopicRepository.existsById(id);
    }

    @Override
    public List<SurveyTopicListModel> getAllTopics() {
        var entities = surveyTopicRepository.findAll();

        return mapper.map(entities, SurveyTopicListModel.class);
    }

    @Override
    @Transactional
    public SurveyQuestionModel createNewTopic(SurveyQuestionModel model) {

        var entity = mapper.map(model, SurveyTopicEntity.class);
        entity.setScore(0);

        System.out.println("TEST");

        var savedEntity = this.surveyTopicRepository.save(entity);

        return mapper.map(savedEntity, SurveyQuestionModel.class);
    }

    @Override
    @Transactional
    public SurveyQuestionModel updateTopicById(int topicId, SurveyQuestionModel model) {

        var entity = surveyTopicRepository.findById(topicId);

        if (entity.isEmpty()) {
            throw new TopicNotFoundException(topicId);
        }

        var entityToUpdate = entity.get();

        entityToUpdate.setTopic(model.getTopic());
        entityToUpdate.setQuestion(model.getQuestion());

        entityToUpdate = surveyTopicRepository.save(entityToUpdate);

        return mapper.map(entityToUpdate, SurveyQuestionModel.class);
    }

    @Override
    @Transactional
    public void deleteTopicById(int topicId) {
        surveyTopicRepository.deleteById(topicId);
    }
}
