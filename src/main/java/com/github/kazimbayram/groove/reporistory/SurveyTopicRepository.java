package com.github.kazimbayram.groove.reporistory;

import com.github.kazimbayram.groove.entity.SurveyTopic;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SurveyTopicRepository extends JpaRepository<SurveyTopic, Integer> {
}
