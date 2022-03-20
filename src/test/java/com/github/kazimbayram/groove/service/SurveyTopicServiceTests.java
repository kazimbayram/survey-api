package com.github.kazimbayram.groove.service;

import com.github.kazimbayram.groove.exceptions.TopicNotFoundException;
import com.github.kazimbayram.groove.model.SurveyQuestionModel;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class SurveyTopicServiceTests {

    @Autowired
    SurveyTopicService surveyTopicService;

    @Test
    void isAnyTopicWithId() {
        var topic = new SurveyQuestionModel();
        topic.setTopic("Test1");
        topic.setQuestion("Test1");

        topic = surveyTopicService.createNewTopic(topic);

        var topicId = topic.getId();

        var topic2 = new SurveyQuestionModel();
        topic2.setTopic("Test1");
        topic2.setQuestion("Test1");

        topic2 = surveyTopicService.createNewTopic(topic2);

        var topicId2 = topic2.getId();

        var result1 = surveyTopicService.isAnyTopicWithId(topicId);
        var result2 = surveyTopicService.isAnyTopicWithId(99);

        assertTrue(result1);
        assertFalse(result2);

    }

    @Test
    void createNewTopic() {
        var topic2 = new SurveyQuestionModel();
        topic2.setTopic("createNewTopic");
        topic2.setQuestion("createNewTopic");

        topic2 = surveyTopicService.createNewTopic(topic2);
        assertNotNull(topic2.getId());
    }

    @Test
    void updateTopicById() {
        var topic = new SurveyQuestionModel();
        topic.setTopic("createNewTopic");
        topic.setQuestion("createNewTopic");

        topic = surveyTopicService.createNewTopic(topic);

        var retrieved = surveyTopicService.getTopicById(topic.getId());

        assertTrue(retrieved.isPresent());


        var willUpdate = retrieved.get();
        willUpdate.setTopic("Updated createNewTopic");

        surveyTopicService.updateTopicById(willUpdate.getId(), willUpdate);

        var retrieved2 = surveyTopicService.getTopicById(topic.getId());

        assertTrue(retrieved2.isPresent());
        assertEquals(retrieved2.get().getTopic(), "Updated createNewTopic");
    }

    @Test
    void Given_updateTopicById_When_TopicId_IsInvalid_Then_ThrowTopicNotFoundException() {
        var topic = new SurveyQuestionModel();
        topic.setTopic("createNewTopic");
        topic.setQuestion("createNewTopic");

        topic = surveyTopicService.createNewTopic(topic);

        var retrieved = surveyTopicService.getTopicById(topic.getId());

        assertTrue(retrieved.isPresent());


        var willUpdate = retrieved.get();
        willUpdate.setTopic("Updated createNewTopic");

        assertThrows(TopicNotFoundException.class, () -> surveyTopicService.updateTopicById(999, willUpdate));
    }


    @Test
    void deleteTopicById() {
        var topic = new SurveyQuestionModel();
        topic.setTopic("createNewTopic");
        topic.setQuestion("createNewTopic");

        topic = surveyTopicService.createNewTopic(topic);

        var retrieved = surveyTopicService.getTopicById(topic.getId());

        assertTrue(retrieved.isPresent());

        surveyTopicService.deleteTopicById(topic.getId());

        var retrieved2 = surveyTopicService.getTopicById(topic.getId());

        assertTrue(retrieved2.isEmpty());
    }
}
