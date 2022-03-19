package com.github.kazimbayram.groove.entity;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

@Entity
@Table(name = "answers")
@AttributeOverride(name = "id", column = @Column(name = "submit_id"))
public class SurveyAnswerEntity extends BaseEntity {

    @JoinColumn(name = "topic_id", nullable = false)
    @ManyToOne(fetch = FetchType.LAZY)
    private SurveyTopicEntity topic;

    @Column(name = "score")
    private int score;

    @Column(name = "feedback")
    private String feedback;


}
