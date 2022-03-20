package com.github.kazimbayram.groove.entity;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

@Entity
@Table(name = "topics")
@AttributeOverride(name = "id", column = @Column(name = "topic_id"))
public class SurveyTopic extends BaseEntity {

    @Column(name = "topic", nullable = false)
    private String topic;

    @Column(name = "question", nullable = false)
    private String question;

    @Column(name = "score", nullable = false)
    private int score;

    @JoinColumn(name = "topic_id", referencedColumnName = "topic_id")
    @OneToOne(fetch = FetchType.LAZY)
    private SurveyTopicScoreboard scoreboard;

    @JoinColumn(name = "topic_id", referencedColumnName = "topic_id")
    @OneToMany(fetch = FetchType.LAZY)
    private List<SurveyAnswer> answers;

}
