package com.github.kazimbayram.groove.nps;


import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class NetPromoterScoreCalculatorServiceTest {

    @Test
    void Given_SurveyAnswer_When_CalculationRequested_Then_ShouldReturnCalculatedScore() {
    }

    @Test
    void Given_SurveyAnswer_When_ScoreBetween1And6_Then_ShouldEvaluateAs_DETRACTORS() {
    }

    @Test
    void Given_SurveyAnswer_When_ScoreBetween7And8_Then_ShouldEvaluateAs_PASSIVES() {
    }

    @Test
    void Given_SurveyAnswer_When_ScoreBetween9And10_Then_ShouldEvaluateAs_PROMOTERS() {
    }

    @Test
    void Given_100Answers_When_40_DECRACTORS_and_20_PASSIVES_and_40_PROMOTERS_THEN_NPS_Should_be_0() {
    }

    @Test
    void Given_100Answers_When_10_DECRACTORS_and_20_PASSIVES_and_70_PROMOTERS_THEN_NPS_Should_be_60() {

    }

    @Test
    void Given_100Answers_When_0_DECRACTORS_and_20_PASSIVES_and_80_PROMOTERS_THEN_NPS_Should_be_80() {

    }

    @Test
    void Given_100Answers_When_0_DECRACTORS_and_0_PASSIVES_and_100_PROMOTERS_THEN_NPS_Should_be_100() {

    }

    @Test
    void Given_100Answers_When_100_DECRACTORS_and_0_PASSIVES_and_0_PROMOTERS_THEN_NPS_Should_be_minus_100() {

    }

    @Test
    void Given_100Answers_When_0_DECRACTORS_and_100_PASSIVES_and_0_PROMOTERS_THEN_NPS_Should_be_0() {

    }
}
