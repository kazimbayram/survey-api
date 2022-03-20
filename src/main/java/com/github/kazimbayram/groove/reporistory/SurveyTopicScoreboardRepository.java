package com.github.kazimbayram.groove.reporistory;

import com.github.kazimbayram.groove.entity.SurveyTopicEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SurveyTopicRepository extends JpaRepository<SurveyTopicEntity, Integer> {
}
