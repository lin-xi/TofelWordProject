package com.leeme.tofelword.db.dto;

/**
 * Created by Leeme on 13-8-22.
 */
public class StudyStatus {

    private int id;
    private int lastWordIndex;

    public int getLastWordIndex() {
        return lastWordIndex;
    }

    public void setLastWordIndex(int lastWordIndex) {
        this.lastWordIndex = lastWordIndex;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
