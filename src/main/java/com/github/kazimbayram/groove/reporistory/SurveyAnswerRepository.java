package com.github.kazimbayram.groove.reporistory;

import com.github.kazimbayram.groove.entity.SurveyAnswerEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SurveyAnswerRepository extends JpaRepository<SurveyAnswerEntity, Integer> {

    List<SurveyAnswerEntity> findByTopicId(int topicId);
}
