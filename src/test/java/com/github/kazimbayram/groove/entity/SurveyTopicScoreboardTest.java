package com.github.kazimbayram.groove.entity;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class SurveyTopicScoreboardTest {

    @Test
    void incrementDetractors() {
        var entity = new SurveyTopicScoreboard();
        entity.setTotalSubmission(0);
        entity.setTotalDetractors(0);
        entity.setTotalPassives(0);
        entity.setTotalPromoters(0);

        entity.incrementDetractors();

        assertEquals(entity.getTotalSubmission(), 1);
        assertEquals(entity.getTotalDetractors(), 1);
    }

    @Test
    void incrementPassives() {
        var entity = new SurveyTopicScoreboard();
        entity.setTotalSubmission(0);
        entity.setTotalDetractors(0);
        entity.setTotalPassives(0);
        entity.setTotalPromoters(0);

        entity.incrementPassives();

        assertEquals(entity.getTotalSubmission(), 1);
        assertEquals(entity.getTotalPassives(), 1);
    }

    @Test
    void incrementPromoters() {
        var entity = new SurveyTopicScoreboard();
        entity.setTotalSubmission(0);
        entity.setTotalDetractors(0);
        entity.setTotalPassives(0);
        entity.setTotalPromoters(0);

        entity.incrementPromoters();

        assertEquals(entity.getTotalSubmission(), 1);
        assertEquals(entity.getTotalPromoters(), 1);
    }
}