package com.leeme.tofelword.handlers;

import android.content.Context;
import android.os.Handler;

import com.google.gson.Gson;
import com.leeme.tofelword.db.dao.WordDao;
import com.leeme.tofelword.db.dto.Studylog;
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
    public  String getWords(String start, String count){
        List<Word> list = dao.query(start, count);
        String json = gson.toJson(list);
        return json;
    }
    public String getStudyLogs(){
        List<Studylog> list = dao.queryStudylog();
        String json = gson.toJson(list);
        return json;
    }
    public void addStudyLog(String unit, String datetime, String studytype,  String score, String costtime){
        dao.insertStudylog(Integer.parseInt(unit), Integer.parseInt(datetime), Integer.parseInt(studytype),  Integer.parseInt(score), Integer.parseInt(costtime));
    }
    public  String getWord(String id){
        Word  word = dao.queryWord(id);
        String json = gson.toJson(word);
        return json;
    }

}
