package com.leeme.tofelword.handlers;

import android.content.Context;

import com.google.gson.Gson;
import com.leeme.tofelword.db.dao.WordDao;
import com.leeme.tofelword.db.dao.WordUnitDao;
import com.leeme.tofelword.db.dto.WordUnit;
import com.leeme.tofelword.db.dto.Word;

import java.util.List;

/**
 * WordtHandlerInterface
 */
public class WordtHandlerInterface extends BaseHandler{
    private WordDao dao;
    private WordUnitDao unitDao;
    private Gson gson = new Gson();

    public WordtHandlerInterface(Context context){
        super();
        dao = new WordDao(context);
        unitDao = new WordUnitDao(context);
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
    public  String getWord(String id){
        Word  word = dao.queryWord(id);
        String json = gson.toJson(word);
        return json;
    }

    public String getUnits(){
        List<WordUnit> list = unitDao.query();
        String json = gson.toJson(list);
        return json;
    }

    /*
    public void insertUnit(){
        for(int i=0; i<319; i++){
            unitDao.insert(i);
        }
    }
    */

    public void updateLastWordIndex(String unit, String index){
        unitDao.updateLastWordIndex(Integer.parseInt(unit), Integer.parseInt(index));
    }

    public void updateReviewLevel(String unit, String level){
        unitDao.updateReviewLevel(Integer.parseInt(unit), Integer.parseInt(level));
    }

    public void updateStudyFlag(String unit, String flag){
        unitDao.updateStudyFlag(Integer.parseInt(unit), Integer.parseInt(flag));
    }

    public void addStudyLog(String unit, String datetime, String studytype, String score, String costtime){
        dao.insertStudylog(Integer.parseInt(unit), Integer.parseInt(datetime), Integer.parseInt(studytype), Integer.parseInt(score), Integer.parseInt(costtime));
        unitDao.updateStudyFlag(Integer.parseInt(unit), 1);
    }

    public void updateYesRate(String unit, String rate){
        unitDao.updateYesRate(Integer.parseInt(unit), Integer.parseInt(rate));
    }


}
