package com.github.kazimbayram.groove.answer;


import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class SurveyAnswerServiceTests {

    @Test
    void Given_SurveyQuestion_When_SubmitAnAnswer_Then_AnswerShouldHaveAnId() {

    }

    @Test
    void Given_SurveyQuestion_When_SubmitAnAnswer_Then_Answer_Should_Assigned_to_a_TopicId() {

    }

    @Test
    void Given_EmptySurveyQuestion_WhenSubmitAnAnswer_Then_Throws_InvalidAnswerException() {

    }

    @Test
    void Given_SurveyQuestionWithInvalidTopicId_When_SubmitAnAnswer_Then_ThrowsTopicNotFoundException() {

    }

    @Test
    void Given_SurveyQuestionWithInvalidScoreRange_When_SubmitAnAnswer_Then_ThrowsInvalidAnswerException() {

    }
}
