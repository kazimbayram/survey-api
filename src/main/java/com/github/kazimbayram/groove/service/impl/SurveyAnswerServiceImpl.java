package com.github.kazimbayram.groove.service.impl;

import com.github.kazimbayram.groove.entity.SurveyAnswerEntity;
import com.github.kazimbayram.groove.entity.SurveyTopicEntity;
import com.github.kazimbayram.groove.exceptions.TopicNotFoundException;
import com.github.kazimbayram.groove.model.SurveyAnswerModel;
import com.github.kazimbayram.groove.reporistory.SurveyAnswerRepository;
import com.github.kazimbayram.groove.service.NpsOperationsService;
import com.github.kazimbayram.groove.service.SurveyAnswerService;
import com.github.kazimbayram.groove.service.TopicListingService;
import com.github.kazimbayram.groove.utility.Mapper;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.List;

@Service
public class SurveyAnswerServiceImpl implements SurveyAnswerService {

    private final TopicListingService topicListingService;
    private final NpsOperationsService npsOperationsService;
    private final SurveyAnswerRepository surveyAnswerRepository;
    private final EntityManager entityManager;
    private final Mapper mapper;

    public SurveyAnswerServiceImpl(
            TopicListingService topicListingService,
            NpsOperationsService npsOperationsService,
            SurveyAnswerRepository surveyAnswerRepository,
            EntityManager entityManager,
            Mapper mapper
    ) {
        this.topicListingService = topicListingService;
        this.npsOperationsService = npsOperationsService;
        this.surveyAnswerRepository = surveyAnswerRepository;
        this.entityManager = entityManager;
        this.mapper = mapper;
    }

    @Override
    @Transactional(Transactional.TxType.REQUIRES_NEW)
    public void saveAnswerWithTopicId(int id, SurveyAnswerModel answerModel) {
        var isTopicExists = this.topicListingService.isAnyTopicWithId(id);

        if (!isTopicExists) {
            throw new TopicNotFoundException(id);
        }

        var entity = mapper.map(answerModel, SurveyAnswerEntity.class);
        entity.setTopic(entityManager.getReference(SurveyTopicEntity.class, id));
        entity = surveyAnswerRepository.save(entity);

        var model = mapper.map(entity, SurveyAnswerModel.class);

        npsOperationsService.calculateNewScore(model);
    }

    @Override
    public List<SurveyAnswerModel> getAnswersByTopicId(int topicId) {
        var entities = surveyAnswerRepository.findByTopicId(topicId);
        return mapper.map(entities, SurveyAnswerModel.class);
    }
}
