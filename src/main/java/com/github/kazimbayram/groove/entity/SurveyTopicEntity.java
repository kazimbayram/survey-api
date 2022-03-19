package com.github.kazimbayram.groove.entity;

import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.util.List;
import java.util.Objects;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

@Entity
@Table(name = "topics")
@AttributeOverride(name = "id", column = @Column(name = "topic_id"))
public class SurveyTopicEntity extends BaseEntity {

    @Column(name = "topic", nullable = false)
    private String topic;

    @Column(name = "question", nullable = false)
    private String question;

    @Min(0)
    @Column(name = "score", nullable = false)
    private int score;

    @JoinColumn(name = "topic_id", referencedColumnName = "topic_id")
    @OneToOne(fetch = FetchType.LAZY)
    private SurveyTopicScoreboard scoreboard;

    @JoinColumn(name = "topic_id", referencedColumnName = "topic_id")
    @OneToMany(fetch = FetchType.LAZY)
    private List<SurveyAnswerEntity> answers;

}
