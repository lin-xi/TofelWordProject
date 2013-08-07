package com.leeme.tofelword.db.dao;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.leeme.tofelword.db.DBHelper;
import com.leeme.tofelword.db.dto.Sentence;

import java.util.ArrayList;
import java.util.List;

/**
 * SentenceDao
 */
public class SentenceDao {
    private SQLiteDatabase db;

    public SentenceDao(Context ctx) {
        db =  DBHelper.openDatabase(ctx, "sentences.db");
    }

    /**
     * query all persons, return list
     * @return List<Person>
     */
    public List<Sentence> query(String word) {
        ArrayList<Sentence> sentences = new ArrayList<Sentence>();
        String sql = "SELECT sentences FROM sentences where word = ?";
        Cursor c = db.rawQuery(sql, new String[]{word});
        if(c.getCount()>0){
            while (c.moveToNext()) {
                Sentence sentence = new Sentence();
                sentence.setSentences(c.getString(0));
                sentences.add(sentence);
            }
        }
        c.close();
        return sentences;
    }
}
