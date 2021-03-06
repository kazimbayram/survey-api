package com.github.kazimbayram.groove.controller;

import com.github.kazimbayram.groove.exceptions.TopicNotFoundException;
import com.github.kazimbayram.groove.model.SurveyAnswerModel;
import com.github.kazimbayram.groove.model.SurveyQuestionModel;
import com.github.kazimbayram.groove.service.SurveyAnswerService;
import com.github.kazimbayram.groove.service.SurveyTopicService;
import com.github.kazimbayram.groove.utility.Mapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController()
@RequestMapping("survey/{id}")
public class SurveyController {

    private final SurveyTopicService surveyListingService;
    private final SurveyAnswerService surveyAnswerService;
    private final Mapper mapper;

    public SurveyController(SurveyTopicService surveyListingService, SurveyAnswerService surveyAnswerService, Mapper mapper) {
        this.surveyListingService = surveyListingService;
        this.surveyAnswerService = surveyAnswerService;
        this.mapper = mapper;
    }

    @GetMapping
    public ResponseEntity<SurveyQuestionModel> getTopic(@PathVariable("id") int id) {
        var topic = surveyListingService.getTopicById(id);

        if (topic.isPresent()) {
            var result = mapper.map(topic.get(), SurveyQuestionModel.class);
            return ResponseEntity.ok(result);
        }

        throw new TopicNotFoundException(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void submitAnswer(@PathVariable("id") int id, @Valid @RequestBody() SurveyAnswerModel answerModel) {
        surveyAnswerService.saveAnswerWithTopicId(id, answerModel);
    }
}
