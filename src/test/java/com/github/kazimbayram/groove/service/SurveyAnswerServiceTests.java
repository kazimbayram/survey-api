package com.github.kazimbayram.groove.service;


import com.github.kazimbayram.groove.exceptions.TopicNotFoundException;
import com.github.kazimbayram.groove.model.SurveyAnswerModel;
import com.github.kazimbayram.groove.model.SurveyQuestionModel;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.validation.ValidationException;

@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class SurveyAnswerServiceTests {

    static Integer topicId;
    static Integer topicId2;

    @Autowired
    SurveyAnswerService surveyAnswerService;

    @Autowired
    SurveyTopicService surveyTopicService;

    @BeforeAll
    public void setup() {
        var topic = new SurveyQuestionModel();
        topic.setTopic("Test1");
        topic.setQuestion("Test1");

        topic = surveyTopicService.createNewTopic(topic);

        topicId = topic.getId();

        var topic2 = new SurveyQuestionModel();
        topic2.setTopic("Test1");
        topic2.setQuestion("Test1");

        topic2 = surveyTopicService.createNewTopic(topic2);

        topicId2 = topic2.getId();

        var answer1 = new SurveyAnswerModel();
        answer1.setScore(6);
        answer1.setFeedback("Test");
        surveyAnswerService.saveAnswerWithTopicId(topicId, answer1);

        var answer2 = new SurveyAnswerModel();
        answer2.setScore(10);
        answer2.setFeedback("Test 2");
        surveyAnswerService.saveAnswerWithTopicId(topicId, answer2);

        var answer3 = new SurveyAnswerModel();
        answer3.setScore(6);
        answer3.setFeedback("Test4");
        surveyAnswerService.saveAnswerWithTopicId(topicId2, answer3);

        var answer4 = new SurveyAnswerModel();
        answer4.setScore(10);
        answer4.setFeedback("Test 5");
        surveyAnswerService.saveAnswerWithTopicId(topicId, answer4);
    }


    @Test
    void Given_SurveyAnswerModel_When_saveAnswerWithTopicId_Then_AnswerShouldHaveAnId() {

        var submission = new SurveyAnswerModel();
        submission.setScore(5);
        submission.setFeedback("This is feedback");

        var result = surveyAnswerService.saveAnswerWithTopicId(topicId, submission);

        assertNotNull(result.getId());
    }

    @Test
    void Given_SurveyQuestion_When_SubmitAnAnswer_Then_Answer_Should_Assigned_to_a_TopicId() {

        var submission = new SurveyAnswerModel();
        submission.setScore(5);
        submission.setFeedback("This is feedback");

        var result = surveyAnswerService.saveAnswerWithTopicId(topicId, submission);

        assertEquals(result.getTopicId(), topicId);
    }

    @Test
    void Given_SurveyQuestionWithInvalidTopicId_When_SubmitAnAnswer_Then_ThrowsTopicNotFoundException() {
        var submission = new SurveyAnswerModel();
        submission.setScore(10);
        submission.setFeedback("This is feedback");

        assertThrows(TopicNotFoundException.class, () -> surveyAnswerService.saveAnswerWithTopicId(999, submission));

    }

    @Test
    void Given_SurveyQuestionWithInvalidScoreRange_When_SubmitAnAnswer_Then_ThrowsInvalidAnswerException() {
        var submission = new SurveyAnswerModel();
        submission.setScore(-1);
        submission.setFeedback("This is feedback");

        assertThrows(ValidationException.class, () -> surveyAnswerService.saveAnswerWithTopicId(topicId, submission));

        var submission2 = new SurveyAnswerModel();
        submission2.setScore(11);
        submission2.setFeedback("This is feedback");

        assertThrows(ValidationException.class, () -> surveyAnswerService.saveAnswerWithTopicId(topicId, submission2));
    }


    @Test
    void Given_getAnswersByTopicId_When_ListHaveAnswers_Then_AnswersShouldHaveSameTopicId() {

        var result = surveyAnswerService.getAnswersByTopicId(topicId);

        for (var item : result) {
            assertEquals(item.getTopicId(), topicId);
        }

        var result2 = surveyAnswerService.getAnswersByTopicId(topicId2);

        for (var item : result2) {
            assertEquals(item.getTopicId(), topicId2);
        }

    }

    @Test
    void Given_AnswerRequestWithInvalidTopicId_When_ListAnswers_Then_Throw_TopicNotFoundException() {
        assertThrows(TopicNotFoundException.class, () -> surveyAnswerService.getAnswersByTopicId(999));
    }

}
