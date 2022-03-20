package com.github.kazimbayram.groove.service.impl;

import com.github.kazimbayram.groove.entity.SurveyTopic;
import com.github.kazimbayram.groove.exceptions.TopicNotFoundException;
import com.github.kazimbayram.groove.model.SurveyQuestionModel;
import com.github.kazimbayram.groove.model.SurveyTopicListModel;
import com.github.kazimbayram.groove.model.SurveyTopicModel;
import com.github.kazimbayram.groove.reporistory.SurveyTopicRepository;
import com.github.kazimbayram.groove.reporistory.SurveyTopicScoreboardRepository;
import com.github.kazimbayram.groove.service.NpsOperationsService;
import com.github.kazimbayram.groove.service.SurveyTopicService;
import com.github.kazimbayram.groove.utility.Mapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class surveyTopicServiceImpl implements SurveyTopicService {

    private final SurveyTopicRepository surveyTopicRepository;
    private SurveyTopicScoreboardRepository surveyTopicScoreboardRepository;
    private final Mapper mapper;
    private final NpsOperationsService npsOperationsService;

    public surveyTopicServiceImpl(SurveyTopicRepository surveyTopicRepository,
                                   NpsOperationsService npsOperationsService,
                                   SurveyTopicScoreboardRepository surveyTopicScoreboardRepository,
                                   Mapper mapper) {
        this.surveyTopicRepository = surveyTopicRepository;
        this.npsOperationsService = npsOperationsService;
        this.surveyTopicScoreboardRepository = surveyTopicScoreboardRepository;
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

        var entity = mapper.map(model, SurveyTopic.class);
        entity.setScore(0);

        var savedEntity = this.surveyTopicRepository.save(entity);

        model = mapper.map(savedEntity, SurveyQuestionModel.class);
        npsOperationsService.createNewScoreboard(model);

        return model;
    }

    @Override
    @Transactional
    public SurveyTopicModel updateTopicById(int topicId, SurveyTopicModel model) {

        var entity = surveyTopicRepository.findById(topicId);

        if (entity.isEmpty()) {
            throw new TopicNotFoundException(topicId);
        }

        var entityToUpdate = entity.get();

        entityToUpdate.setTopic(model.getTopic());
        entityToUpdate.setQuestion(model.getQuestion());

        entityToUpdate = surveyTopicRepository.save(entityToUpdate);

        return mapper.map(entityToUpdate, SurveyTopicModel.class);
    }

    @Override
    @Transactional
    public void deleteTopicById(int topicId) {
        surveyTopicScoreboardRepository.deleteByTopicId(topicId);
        surveyTopicRepository.deleteById(topicId);
    }
}
