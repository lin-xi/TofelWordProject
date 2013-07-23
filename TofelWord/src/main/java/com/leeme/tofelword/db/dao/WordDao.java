package com.leeme.tofelword.db.dao;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import com.leeme.tofelword.db.dto.Word;
import com.leeme.tofelword.db.DBHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * WordDao
 */
public class WordDao {
    private SQLiteDatabase db;

    public WordDao(Context context) {
        DBHelper helper = new DBHelper(context);
        db = helper.getWritableDatabase();
    }

    public long getCount() {
        String sql = "select count(*) from words";
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
    public List<Word> query(int start, int amount) {
        ArrayList<Word> words = new ArrayList<Word>();
        String sql = "SELECT _id, word, pronounce, meaning FROM words limit ?, ?";
        Cursor c = db.rawQuery(sql, new String[]{String.valueOf(start), String.valueOf(amount)});
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
}
