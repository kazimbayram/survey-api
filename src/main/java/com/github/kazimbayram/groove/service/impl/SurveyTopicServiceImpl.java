package com.github.kazimbayram.groove.service.impl;

import com.github.kazimbayram.groove.entity.SurveyTopic;
import com.github.kazimbayram.groove.exceptions.TopicNotFoundException;
import com.github.kazimbayram.groove.model.SurveyQuestionModel;
import com.github.kazimbayram.groove.model.SurveyTopicListModel;
import com.github.kazimbayram.groove.model.SurveyTopicModel;
import com.github.kazimbayram.groove.reporistory.SurveyAnswerRepository;
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
public class SurveyTopicServiceImpl implements SurveyTopicService {

    private final SurveyTopicRepository surveyTopicRepository;
    private final SurveyTopicScoreboardRepository surveyTopicScoreboardRepository;
    private final SurveyAnswerRepository surveyAnswerRepository;
    private final Mapper mapper;
    private final NpsOperationsService npsOperationsService;

    public SurveyTopicServiceImpl(
            SurveyTopicRepository surveyTopicRepository,
            NpsOperationsService npsOperationsService,
            SurveyTopicScoreboardRepository surveyTopicScoreboardRepository,
            SurveyAnswerRepository surveyAnswerRepository, Mapper mapper
    ) {
        this.surveyTopicRepository = surveyTopicRepository;
        this.npsOperationsService = npsOperationsService;
        this.surveyTopicScoreboardRepository = surveyTopicScoreboardRepository;
        this.surveyAnswerRepository = surveyAnswerRepository;
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

        var entity = surveyTopicRepository.findById(topicId)
                .orElseThrow(() -> new TopicNotFoundException(topicId));

        entity.setTopic(model.getTopic());
        entity.setQuestion(model.getQuestion());

        entity = surveyTopicRepository.save(entity);

        return mapper.map(entity, SurveyTopicModel.class);
    }

    @Override
    @Transactional
    public void deleteTopicById(int topicId) {
        surveyAnswerRepository.deleteByTopicId(topicId);
        surveyTopicScoreboardRepository.deleteByTopicId(topicId);
        surveyTopicRepository.deleteById(topicId);
    }
}
