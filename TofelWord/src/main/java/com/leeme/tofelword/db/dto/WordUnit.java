package com.leeme.tofelword.db.dto;

/**
 * Created by Bona on 13-8-25.
 */

public class WordUnit {
    private int id;
    private int unit;
    private int yesRate;
    private int reviewLevel;
    private int studyFlag;
    private int lastWordIndex;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUnit() {
        return unit;
    }

    public void setUnit(int unit) {
        this.unit = unit;
    }

    public int getYesRate() {
        return yesRate;
    }

    public void setYesRate(int yesRate) {
        this.yesRate = yesRate;
    }

    public int getReviewLevel() {
        return reviewLevel;
    }

    public void setReviewLevel(int reviewLevel) {
        this.reviewLevel = reviewLevel;
    }

    public int isStudyFlag() {
        return studyFlag;
    }

    public void setStudyFlag(int studyFlag) {
        this.studyFlag = studyFlag;
    }

    public int getLastWordIndex() {
        return lastWordIndex;
    }

    public void setLastWordIndex(int lastWordIndex) {
        this.lastWordIndex = lastWordIndex;
    }




}
