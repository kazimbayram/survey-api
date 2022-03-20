package com.github.kazimbayram.groove.service;


import static org.junit.jupiter.api.Assertions.*;

import com.github.kazimbayram.groove.enums.NpsType;
import com.github.kazimbayram.groove.model.SurveyTopicScoreboardModel;
import com.github.kazimbayram.groove.service.NetPromoterScoreCalculatorService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class NetPromoterScoreCalculatorServiceTest {

    @Autowired
    NetPromoterScoreCalculatorService netPromoterScoreCalculatorService;

    @Test
    void Given_SurveyTopicScoreboardModel_When_calculateNpsScore_Then_ShouldReturnCalculatedScore() {

        SurveyTopicScoreboardModel model = new SurveyTopicScoreboardModel();
        model.setTotalSubmission(100);
        model.setTotalDetractors(10);
        model.setTotalPromoters(50);
        model.setTotalPassives(40);

        var result = netPromoterScoreCalculatorService.calculateNpsScore(model);

        assertNotEquals(result, 0);
    }

    @Test
    void Given_SurveyTopicScoreboardModel_When_calculateNpsScore_ScoreBetween1And6_Then_ShouldEvaluateAs_DETRACTORS() {
        var result1 = netPromoterScoreCalculatorService.getByScore(1);
        var result2 = netPromoterScoreCalculatorService.getByScore(2);
        var result3 = netPromoterScoreCalculatorService.getByScore(3);
        var result4 = netPromoterScoreCalculatorService.getByScore(4);
        var result5 = netPromoterScoreCalculatorService.getByScore(5);
        var result6 = netPromoterScoreCalculatorService.getByScore(6);

        assertEquals(result1, NpsType.DETRACTOR);
        assertEquals(result2, NpsType.DETRACTOR);
        assertEquals(result3, NpsType.DETRACTOR);
        assertEquals(result4, NpsType.DETRACTOR);
        assertEquals(result5, NpsType.DETRACTOR);
        assertEquals(result6, NpsType.DETRACTOR);
    }

    @Test
    void Given_SurveyTopicScoreboardModel_When_calculateNpsScore_ScoreBetween7And8_Then_ShouldEvaluateAs_PASSIVES() {
        var result1 = netPromoterScoreCalculatorService.getByScore(7);
        var result2 = netPromoterScoreCalculatorService.getByScore(8);

        assertEquals(result1, NpsType.PASSIVE);
        assertEquals(result2, NpsType.PASSIVE);
    }

    @Test
    void Given_SurveyTopicScoreboardModel_When_ScoreBetween9And10_Then_ShouldEvaluateAs_PROMOTERS() {
        var result1 = netPromoterScoreCalculatorService.getByScore(9);
        var result2 = netPromoterScoreCalculatorService.getByScore(10);

        assertEquals(result1, NpsType.PROMOTER);
        assertEquals(result2, NpsType.PROMOTER);
    }

    @Test
    void Given_SurveyTopicScoreboardModel_When_ScoreLowerThan1AndHigherThan10_Then_ShouldThrowIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> netPromoterScoreCalculatorService.getByScore(-1));
        assertThrows(IllegalArgumentException.class, () -> netPromoterScoreCalculatorService.getByScore(11));
    }

    @Test
    void Given_SurveyTopicScoreboardModel_With_100Answers_When_40_DECRACTORS_and_20_PASSIVES_and_40_PROMOTERS_THEN_NPS_Should_be_0() {
        SurveyTopicScoreboardModel model = new SurveyTopicScoreboardModel();
        model.setTotalSubmission(100);
        model.setTotalDetractors(40);
        model.setTotalPromoters(40);
        model.setTotalPassives(20);

        var result = netPromoterScoreCalculatorService.calculateNpsScore(model);

        assertEquals(result, 0);
    }

    @Test
    void Given_SurveyTopicScoreboardModel_With_100Answers_When_10_DECRACTORS_and_20_PASSIVES_and_70_PROMOTERS_THEN_NPS_Should_be_60() {
        SurveyTopicScoreboardModel model = new SurveyTopicScoreboardModel();
        model.setTotalSubmission(100);
        model.setTotalDetractors(10);
        model.setTotalPromoters(70);
        model.setTotalPassives(20);

        var result = netPromoterScoreCalculatorService.calculateNpsScore(model);

        assertEquals(result, 60);
    }

    @Test
    void Given_SurveyTopicScoreboardModel_With_100Answers_When_0_DECRACTORS_and_20_PASSIVES_and_80_PROMOTERS_THEN_NPS_Should_be_80() {
        SurveyTopicScoreboardModel model = new SurveyTopicScoreboardModel();
        model.setTotalSubmission(100);
        model.setTotalDetractors(0);
        model.setTotalPromoters(80);
        model.setTotalPassives(20);

        var result = netPromoterScoreCalculatorService.calculateNpsScore(model);

        assertEquals(result, 80);
    }

    @Test
    void Given_SurveyTopicScoreboardModel_With_100Answers_When_0_DECRACTORS_and_0_PASSIVES_and_100_PROMOTERS_THEN_NPS_Should_be_100() {
        SurveyTopicScoreboardModel model = new SurveyTopicScoreboardModel();
        model.setTotalSubmission(100);
        model.setTotalDetractors(0);
        model.setTotalPromoters(100);
        model.setTotalPassives(0);

        var result = netPromoterScoreCalculatorService.calculateNpsScore(model);

        assertEquals(result, 100);
    }

    @Test
    void Given_SurveyTopicScoreboardModel_With_100Answers_When_100_DECRACTORS_and_0_PASSIVES_and_0_PROMOTERS_THEN_NPS_Should_be_minus_100() {
        SurveyTopicScoreboardModel model = new SurveyTopicScoreboardModel();
        model.setTotalSubmission(100);
        model.setTotalDetractors(100);
        model.setTotalPromoters(0);
        model.setTotalPassives(0);

        var result = netPromoterScoreCalculatorService.calculateNpsScore(model);

        assertEquals(result, -100);
    }

    @Test
    void Given_SurveyTopicScoreboardModel_With_100Answers_When_0_DECRACTORS_and_100_PASSIVES_and_0_PROMOTERS_THEN_NPS_Should_be_0() {
        SurveyTopicScoreboardModel model = new SurveyTopicScoreboardModel();
        model.setTotalSubmission(100);
        model.setTotalDetractors(0);
        model.setTotalPromoters(0);
        model.setTotalPassives(100);

        var result = netPromoterScoreCalculatorService.calculateNpsScore(model);

        assertEquals(result, 0);
    }

    @Test
    void Given_SurveyTopicScoreboardModel_With_0_Answers_When_0_DECRACTORS_and_0_PASSIVES_and_0_PROMOTERS_THEN_NPS_Should_be_0() {
        SurveyTopicScoreboardModel model = new SurveyTopicScoreboardModel();
        model.setTotalSubmission(0);
        model.setTotalDetractors(0);
        model.setTotalPromoters(0);
        model.setTotalPassives(0);

        var result = netPromoterScoreCalculatorService.calculateNpsScore(model);

        assertEquals(result, 0);
    }
}
