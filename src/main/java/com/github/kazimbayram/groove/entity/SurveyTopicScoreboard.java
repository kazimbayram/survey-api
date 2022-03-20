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
@Table(name = "topic_scoreboard")
@AttributeOverride(name = "id", column = @Column(name = "scoreboard_id"))
public class SurveyTopicScoreboard extends BaseEntity {

    @JoinColumn(name = "topic_id", nullable = false)
    @OneToOne(fetch = FetchType.LAZY)
    private SurveyTopic topic;

    @Column(name = "total_submission", nullable = false)
    private int totalSubmission;

    @Column(name = "total_detractors", nullable = false)
    private int totalDetractors;

    @Column(name = "total_passives", nullable = false)
    private int totalPassives;

    @Column(name = "total_promoters", nullable = false)
    private int totalPromoters;


    @Transient
    public void incrementDetractors() {
        this.totalSubmission++;
        this.totalDetractors++;
    }

    @Transient
    public void incrementPassives() {
        this.totalSubmission++;
        this.totalPassives++;
    }

    @Transient
    public void incrementPromoters() {
        this.totalSubmission++;
        this.totalPromoters++;
    }

}
