package com.github.kazimbayram.groove.reporistory;

import com.github.kazimbayram.groove.entity.SurveyTopicScoreboard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SurveyTopicScoreboardRepository extends JpaRepository<SurveyTopicScoreboard, Integer> {

    Optional<SurveyTopicScoreboard> findByTopicId(int topicId);

    void deleteByTopicId(int topicId);
}
