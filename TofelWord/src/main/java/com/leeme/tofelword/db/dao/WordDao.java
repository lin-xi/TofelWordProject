package com.leeme.tofelword.db.dao;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import com.leeme.tofelword.db.dto.Word;
import com.leeme.tofelword.db.dto.Studylog;
import com.leeme.tofelword.db.DBHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * WordDao
 */
public class WordDao {
    private SQLiteDatabase db;

    public WordDao(Context ctx) {
        db =  DBHelper.openDatabase(ctx, "words.db");
    }

    public long getCount() {
        String sql = "select count(*) from words_tofel";
        Cursor c = db.rawQuery(sql, null);
        c.moveToFirst();
        long length = c.getLong(0);
        c.close();
        return length;
    }

    /**
     * query all persons, return list
     * @return List<Person>
     */
    public List<Word> query(String start, String amount) {
        List<Word> words = new ArrayList<Word>();
        String sql = "SELECT _id, word, pronounce, meaning FROM words_tofel limit ?, ?";
        Cursor c = db.rawQuery(sql, new String[]{start, amount});
        while (c.moveToNext()) {
            Word word = new Word();
            word.setId(c.getInt(c.getColumnIndex("_id")));
            word.setWord(c.getString(c.getColumnIndex("word")));
            word.setPronounce(c.getString(c.getColumnIndex("pronounce")));
            word.setMeaning(c.getString(c.getColumnIndex("meaning")));
            words.add(word);
        }
        c.close();
        return words;
    }

    /**
     * query all persons, return list
     * @return List<Person>
     */
    public Word queryWord(String id) {
        Word word = new Word();
        try{
            String sql = "SELECT _id, word, pronounce, meaning FROM words_tofel where _id = ?";
            Cursor c = db.rawQuery(sql, new String[]{id});
            if(c.getCount()>0){
                c.moveToFirst();
                word.setId(c.getInt(c.getColumnIndex("_id")));
                word.setWord(c.getString(c.getColumnIndex("word")));
                word.setPronounce(c.getString(c.getColumnIndex("pronounce")));
                word.setMeaning(c.getString(c.getColumnIndex("meaning")));
                c.close();
            }
        }catch (Exception ex){
        }
        return word;
    }

    public List<Studylog> queryStudylog() {
        List<Studylog> logs = new ArrayList<Studylog>();
        String sql = "SELECT count(_id), groupid FROM studylog group by groupid";
        Cursor c = db.rawQuery(sql, new String[]{});
        while (c.moveToNext()) {
            Studylog log = new Studylog();
            log.setUnit(c.getString(1));
            log.setCount(c.getString(0));
            logs.add(log);
        }
        c.close();
        return logs;
    }

    public void insertStudylog(int unit, int datetime, int studytype, int score, int costtime) {
        try{
            String sql = "insert into studylog (datetime, unit, studytype, score, costtime) values (?, ?, ?, ?, ?)";
            db.execSQL(sql, new Integer[]{datetime, unit, studytype, score, costtime});
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }
}
