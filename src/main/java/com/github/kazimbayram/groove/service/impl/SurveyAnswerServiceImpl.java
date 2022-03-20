package com.github.kazimbayram.groove.service.impl;

import com.github.kazimbayram.groove.entity.SurveyAnswer;
import com.github.kazimbayram.groove.exceptions.TopicNotFoundException;
import com.github.kazimbayram.groove.model.SurveyAnswerModel;
import com.github.kazimbayram.groove.reporistory.SurveyAnswerRepository;
import com.github.kazimbayram.groove.reporistory.SurveyTopicRepository;
import com.github.kazimbayram.groove.service.NpsOperationsService;
import com.github.kazimbayram.groove.service.SurveyAnswerService;
import com.github.kazimbayram.groove.service.SurveyTopicService;
import com.github.kazimbayram.groove.utility.Mapper;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import javax.validation.ValidationException;
import java.util.List;

@Service
public class SurveyAnswerServiceImpl implements SurveyAnswerService {

    private final SurveyTopicService surveyTopicService;
    private final NpsOperationsService npsOperationsService;
    private final SurveyAnswerRepository surveyAnswerRepository;
    private final SurveyTopicRepository surveyTopicRepository;
    private final Mapper mapper;

    public SurveyAnswerServiceImpl(
            SurveyTopicService surveyTopicService,
            NpsOperationsService npsOperationsService,
            SurveyAnswerRepository surveyAnswerRepository,
            SurveyTopicRepository surveyTopicRepository, Mapper mapper
    ) {
        this.surveyTopicService = surveyTopicService;
        this.npsOperationsService = npsOperationsService;
        this.surveyAnswerRepository = surveyAnswerRepository;
        this.surveyTopicRepository = surveyTopicRepository;
        this.mapper = mapper;
    }

    @Override
    @Transactional(Transactional.TxType.REQUIRES_NEW)
    public SurveyAnswerModel saveAnswerWithTopicId(int id, SurveyAnswerModel answerModel) {

        if (answerModel.getScore() < 1 || answerModel.getScore() > 10) {
            throw new ValidationException("Score should be between 1 and 10");
        }

        var isTopicExists = this.surveyTopicService.isAnyTopicWithId(id);

        if (!isTopicExists) {
            throw new TopicNotFoundException(id);
        }

        var entity = mapper.map(answerModel, SurveyAnswer.class);
        entity.setTopic(surveyTopicRepository.getById(id));
        entity = surveyAnswerRepository.save(entity);

        var model = mapper.map(entity, SurveyAnswerModel.class);

        npsOperationsService.calculateNewScore(model);

        return model;
    }

    @Override
    public List<SurveyAnswerModel> getAnswersByTopicId(int topicId) {
        var isTopicExists = this.surveyTopicService.isAnyTopicWithId(topicId);

        if (!isTopicExists) {
            throw new TopicNotFoundException(topicId);
        }

        var entities = surveyAnswerRepository.findByTopicId(topicId);
        return mapper.map(entities, SurveyAnswerModel.class);
    }
}
