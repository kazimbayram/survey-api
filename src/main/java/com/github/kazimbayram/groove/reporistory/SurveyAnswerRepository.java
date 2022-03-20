package com.github.kazimbayram.groove.reporistory;

import com.github.kazimbayram.groove.entity.SurveyAnswer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SurveyAnswerRepository extends JpaRepository<SurveyAnswer, Integer> {

    List<SurveyAnswer> findByTopicId(int topicId);

    void deleteByTopicId(int topicId);
}
