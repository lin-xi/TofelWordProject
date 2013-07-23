package com.leeme.tofelword.db.dto;

/**
 * Created by Leeme on 13-6-17.
 */
public class Word {
    private int _id;
    private String word;
    private String pronounce;
    private String meaning;

    public int getId() {
        return _id;
    }

    public String getWord() {
        return word;
    }

    public String getPronounce() {
        return pronounce;
    }

    public String getMeaning() {
        return meaning;
    }

    public void setId(int _id) {
        this._id = _id;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public void setPronounce(String pronounce) {
        this.pronounce = pronounce;
    }

    public void setMeaning(String meaning) {
        this.meaning = meaning;
    }

}
