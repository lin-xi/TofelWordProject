package com.leeme.tofelword.db.dao;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.leeme.tofelword.db.DBHelper;
import com.leeme.tofelword.db.dto.StudyStatus;


/**
 * Created by Leeme on 13-8-22.
 */
public class StudyStatusDao {

    private SQLiteDatabase db;

    public StudyStatusDao(Context ctx) {
        db =  DBHelper.openDatabase(ctx, "words.db");
    }


    public StudyStatus queryStatus(String unitId) {
        StudyStatus status = new StudyStatus();
        String sql = "SELECT _id, last_word_index FROM words_studystatus where _id = ?";
        Cursor c = db.rawQuery(sql, new String[]{unitId});
        if(c.getCount()>0){
            c.moveToFirst();
            status.setId(c.getInt(0));
            status.setLastWordIndex(c.getInt(1));
        }
        c.close();
        return status;
    }

    public void insertStudyStatus(int unitId, int lastWordIndex) {
        try{
            String sql = "insert into word_studystatus (_id, last_word_index) values (?, ?)";
            db.execSQL(sql, new Integer[]{unitId, lastWordIndex});
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }
}
