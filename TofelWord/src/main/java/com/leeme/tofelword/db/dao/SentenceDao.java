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

    public SentenceDao(Context context) {
        DBHelper helper = new DBHelper(context);
        db = helper.getWritableDatabase();
    }

    public long getCount() {
        String sql = "select count(*) from sentences";
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
    public List<Sentence> query(String word) {
        ArrayList<Sentence> sentences = new ArrayList<Sentence>();
        String sql = "SELECT _id, word, pronounce, meaning FROM sentences where word = ?";
        Cursor c = db.rawQuery(sql, new String[]{word});
        while (c.moveToNext()) {
            Sentence sentence = new Sentence();
            sentence.setId(c.getInt(c.getColumnIndex("_id")));
            sentence.setWord(c.getString(c.getColumnIndex("word")));
            sentence.setSentences(c.getString(c.getColumnIndex("sentences")));
            sentences.add(sentence);
        }
        c.close();
        return sentences;
    }
}
