package com.github.kazimbayram.groove.service.impl;

import com.github.kazimbayram.groove.enums.NpsType;
import com.github.kazimbayram.groove.model.SurveyTopicScoreboardModel;
import com.github.kazimbayram.groove.service.NetPromoterScoreCalculatorService;
import org.springframework.stereotype.Service;

@Service
public class NetPromoterScoreCalculatorServiceImpl implements NetPromoterScoreCalculatorService {

    @Override
    public NpsType getByScore(int score) {

        if (score <= 0 || score > 10) {
            throw new IllegalArgumentException("Score should be min 1 and max 10");
        }

        if (score <= 6) {
            return NpsType.DETRACTOR;
        } else if (score <= 8) {
            return NpsType.PASSIVE;
        } else {
            return NpsType.PROMOTER;
        }
    }

    @Override
    public int calculateNpsScore(SurveyTopicScoreboardModel scoreboardModel) {
        if (scoreboardModel.getTotalSubmission() == 0) {
            return 0;
        }
        return (scoreboardModel.getTotalPromoters() - scoreboardModel.getTotalDetractors()) * 100 / scoreboardModel.getTotalSubmission();
    }
}
