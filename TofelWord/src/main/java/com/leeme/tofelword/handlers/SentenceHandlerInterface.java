package com.leeme.tofelword.handlers;

import android.content.Context;
import android.os.Handler;

import com.google.gson.Gson;
import com.leeme.tofelword.db.dao.SentenceDao;
import com.leeme.tofelword.db.dto.Sentence;

import java.util.List;

/**
 * SentenceJavaScriptInterface
 */
public class SentenceHandlerInterface extends BaseHandler{
    private SentenceDao dao;

    public SentenceHandlerInterface(Context context) {
        super();
        dao = new SentenceDao(context);
    }

    public String getSentence(String word){
        List<Sentence> list = dao.query(word);
        Gson gson = new Gson();
        String json = gson.toJson(list);
        return json;
    }
}
