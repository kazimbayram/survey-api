package com.github.kazimbayram.groove.service.impl;

import com.github.kazimbayram.groove.entity.SurveyTopicScoreboard;
import com.github.kazimbayram.groove.model.SurveyAnswerModel;
import com.github.kazimbayram.groove.model.SurveyQuestionModel;
import com.github.kazimbayram.groove.model.SurveyTopicScoreboardModel;
import com.github.kazimbayram.groove.reporistory.SurveyTopicRepository;
import com.github.kazimbayram.groove.reporistory.SurveyTopicScoreboardRepository;
import com.github.kazimbayram.groove.service.NetPromoterScoreCalculatorService;
import com.github.kazimbayram.groove.service.NpsOperationsService;
import com.github.kazimbayram.groove.utility.Mapper;
import org.springframework.stereotype.Service;

@Service
public class NpsOperationsServiceImpl implements NpsOperationsService {

    private final SurveyTopicScoreboardRepository surveyTopicScoreboardRepository;
    private final SurveyTopicRepository surveyTopicRepository;
    private final NetPromoterScoreCalculatorService netPromoterScoreCalculatorService;
    private final Mapper mapper;

    public NpsOperationsServiceImpl(
            SurveyTopicScoreboardRepository surveyTopicScoreboardRepository,
            SurveyTopicRepository surveyTopicRepository,
            Mapper mapper,
            NetPromoterScoreCalculatorService netPromoterScoreCalculatorService
    ) {
        this.surveyTopicScoreboardRepository = surveyTopicScoreboardRepository;
        this.surveyTopicRepository = surveyTopicRepository;
        this.mapper = mapper;
        this.netPromoterScoreCalculatorService = netPromoterScoreCalculatorService;
    }

    @Override
    public void calculateNewScore(SurveyAnswerModel model) {
        var scoreboard = surveyTopicScoreboardRepository
                .findByTopicId(model.getTopicId())
                .orElseGet(() -> createScoreBoard(model.getTopicId()));

        var submissionType = netPromoterScoreCalculatorService.getByScore(model.getScore());
        switch (submissionType) {
            case DETRACTOR:
                scoreboard.incrementDetractors();
                break;
            case PASSIVE:
                scoreboard.incrementPassives();
                break;
            case PROMOTER:
                scoreboard.incrementPromoters();
                break;
        }

        scoreboard = surveyTopicScoreboardRepository.save(scoreboard);
        var scoreboardModel = mapper.map(scoreboard, SurveyTopicScoreboardModel.class);

        var npsScore = netPromoterScoreCalculatorService.calculateNpsScore(scoreboardModel);

        var topic = surveyTopicRepository.getById(model.getTopicId());
        topic.setScore(npsScore);
        surveyTopicRepository.save(topic);
    }

    @Override
    public void createNewScoreboard(SurveyQuestionModel model) {
        createScoreBoard(model.getId());
    }

    private SurveyTopicScoreboard createScoreBoard(int topicId) {
        var scoreboard = new SurveyTopicScoreboard();
        scoreboard.setTopic(surveyTopicRepository.getById(topicId));
        scoreboard.setTotalSubmission(0);
        scoreboard.setTotalPassives(0);
        scoreboard.setTotalPromoters(0);
        scoreboard.setTotalDetractors(0);

        return surveyTopicScoreboardRepository.save(scoreboard);
    }
}
