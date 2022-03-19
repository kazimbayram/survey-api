package com.github.kazimbayram.groove.controller;

import com.github.kazimbayram.groove.model.SurveyAnswerModel;
import com.github.kazimbayram.groove.model.SurveyQuestionModel;
import com.github.kazimbayram.groove.model.SurveyTopicListModel;
import com.github.kazimbayram.groove.model.SurveyTopicModel;
import com.github.kazimbayram.groove.service.SurveyAnswerService;
import com.github.kazimbayram.groove.service.TopicListingService;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("topic")
public class TopicListingController {

    private final TopicListingService topicListingService;
    private final SurveyAnswerService surveyAnswerService;

    public TopicListingController(TopicListingService topicListingService, SurveyAnswerService surveyAnswerService) {
        this.topicListingService = topicListingService;
        this.surveyAnswerService = surveyAnswerService;
    }

    @GetMapping
    public List<SurveyTopicListModel> getTopics() {
        return topicListingService.getAllTopics();
    }

    @GetMapping("{topicId}")
    public ResponseEntity<SurveyTopicModel> getTopic(@PathVariable("topicId") int topicId) {
        var topic = topicListingService.getTopicById(topicId);

        return ResponseEntity.of(topic);
    }

    @GetMapping("{topicId}/answers")
    public List<SurveyAnswerModel> getTopicAnswers(@PathVariable("topicId") int topicId) {
        return surveyAnswerService.getAnswersByTopicId(topicId);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void postTopic(@RequestBody() SurveyQuestionModel model) {
        topicListingService.createNewTopic(model);
    }

    @PutMapping("{topicId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void putTopic(@PathVariable("topicId") int topicId, @RequestBody() SurveyQuestionModel model) {
        topicListingService.updateTopicById(topicId, model);
    }

    @DeleteMapping("{topicId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteTopic(@PathVariable("topicId") int topicId) {
        topicListingService.deleteTopicById(topicId);
    }
}
