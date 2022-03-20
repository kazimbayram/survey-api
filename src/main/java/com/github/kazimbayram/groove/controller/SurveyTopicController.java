package com.github.kazimbayram.groove.controller;

import com.github.kazimbayram.groove.model.SurveyAnswerModel;
import com.github.kazimbayram.groove.model.SurveyQuestionModel;
import com.github.kazimbayram.groove.model.SurveyTopicListModel;
import com.github.kazimbayram.groove.model.SurveyTopicModel;
import com.github.kazimbayram.groove.service.SurveyAnswerService;
import com.github.kazimbayram.groove.service.SurveyTopicService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("topic")
public class SurveyTopicController {

    private final SurveyTopicService surveyTopicService;
    private final SurveyAnswerService surveyAnswerService;

    public SurveyTopicController(SurveyTopicService surveyTopicService, SurveyAnswerService surveyAnswerService) {
        this.surveyTopicService = surveyTopicService;
        this.surveyAnswerService = surveyAnswerService;
    }

    @GetMapping
    public List<SurveyTopicListModel> getTopics() {
        return surveyTopicService.getAllTopics();
    }

    @GetMapping("{topicId}")
    public ResponseEntity<SurveyTopicModel> getTopic(@PathVariable("topicId") int topicId) {
        var topic = surveyTopicService.getTopicById(topicId);

        return ResponseEntity.of(topic);
    }

    @GetMapping("{topicId}/answers")
    public List<SurveyAnswerModel> getTopicAnswers(@PathVariable("topicId") int topicId) {
        return surveyAnswerService.getAnswersByTopicId(topicId);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void postTopic(@RequestBody() @Valid SurveyQuestionModel model) {
        surveyTopicService.createNewTopic(model);
    }

    @PutMapping("{topicId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void putTopic(@PathVariable("topicId") int topicId, @RequestBody() @Valid SurveyTopicModel model) {
        surveyTopicService.updateTopicById(topicId, model);
    }

    @DeleteMapping("{topicId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteTopic(@PathVariable("topicId") int topicId) {
        surveyTopicService.deleteTopicById(topicId);
    }
}
