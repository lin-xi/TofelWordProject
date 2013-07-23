package com.leeme.tofelword.handlers;

import android.content.Context;
import android.os.Handler;

import com.google.gson.Gson;
import com.leeme.tofelword.db.dao.WordDao;
import com.leeme.tofelword.db.dto.Word;

import java.util.List;

/**
 * WordtHandlerInterface
 */
public class WordtHandlerInterface extends BaseHandler{
    private WordDao dao;
    private Gson gson = new Gson();

    public WordtHandlerInterface(Context context, Handler handler){
        super(handler);
        dao = new WordDao(context);
    }

    public void getCount(final String uid) {
        long count = dao.getCount();

        response(uid, String.valueOf(count));
    }
    public  void getSentence(String uid, String start, String count){
        List<Word> list = dao.query(Integer.parseInt(start), Integer.parseInt(count));
        String json = gson.toJson(list);

        response(uid, json);
    }
}
