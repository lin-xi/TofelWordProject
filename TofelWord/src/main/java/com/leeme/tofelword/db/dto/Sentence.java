package com.leeme.tofelword.db.dto;

/**
 * Created by Leeme on 13-6-17.
 */
public class Sentence {
    private int _id;
    private String word;
    private String sentences;
    private String memo1;
    private String meno2;

    public void setMeno2(String meno2) {
        this.meno2 = meno2;
    }

    public void setId(int _id) {
        this._id = _id;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public void setSentences(String sentences) {
        this.sentences = sentences;
    }

    public void setMemo1(String memo1) {
        this.memo1 = memo1;
    }

    public String getMeno2() {
        return meno2;
    }

    public int getId() {
        return _id;
    }

    public String getWord() {
        return word;
    }

    public String getSentences() {
        return sentences;
    }

    public String getMemo1() {
        return memo1;
    }

}
