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

    public WordtHandlerInterface(Context context){
        super();
        dao = new WordDao(context);
    }

    public String getCount() {
        long count = dao.getCount();
        return String.valueOf(count);
    }
    public  String getSentence(String start, String count){
        List<Word> list = dao.query(Integer.parseInt(start), Integer.parseInt(count));
        String json = gson.toJson(list);
        return json;
    }

    public String getPhoneNumber(){
        return "15510103020";
    }
}
