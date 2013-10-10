package com.leeme.tofelword.db.dao;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.leeme.tofelword.db.DBHelper;
import com.leeme.tofelword.db.dto.WordUnit;
import com.leeme.tofelword.db.dto.Word;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Bona on 13-8-25.
 */
public class WordUnitDao {
    private SQLiteDatabase db;

    public WordUnitDao(Context ctx) {
        db =  DBHelper.openDatabase(ctx, "words.db");
    }

    public long getCount() {
        String sql = "select count(*) from words_unit";
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
    public List<WordUnit> query() {
        List<WordUnit> units = new ArrayList<WordUnit>();
        String sql = "SELECT _id, unit, yesrate, review_level, last_word_index, study_flag FROM words_unit";
        Cursor c = db.rawQuery(sql, new String[]{});
        while (c.moveToNext()) {
            WordUnit wunit = new WordUnit();
            wunit.setId(c.getInt(c.getColumnIndex("_id")));
            wunit.setUnit(c.getInt(c.getColumnIndex("unit")));
            wunit.setLastWordIndex(c.getInt(c.getColumnIndex("last_word_index")));
            wunit.setReviewLevel(c.getInt(c.getColumnIndex("review_level")));
            wunit.setStudyFlag(c.getInt(c.getColumnIndex("study_flag")));
            units.add(wunit);
        }
        c.close();
        return units;
    }

    /**
     * query all persons, return list
     * @return List<Person>
     */
    public List<WordUnit> queryUnit(String unit) {
        List<WordUnit> units = new ArrayList<WordUnit>();
        String sql = "SELECT _id, unit, yesrate, review_level, last_word_index, study_flag FROM words_unit where unit =  ?";
        Cursor c = db.rawQuery(sql, new String[]{unit});
        while (c.moveToNext()) {
            WordUnit wunit = new WordUnit();
            wunit.setId(c.getInt(c.getColumnIndex("_id")));
            wunit.setUnit(c.getInt(c.getColumnIndex("unit")));
            wunit.setLastWordIndex(c.getInt(c.getColumnIndex("last_word_index")));
            wunit.setReviewLevel(c.getInt(c.getColumnIndex("review_level")));
            wunit.setStudyFlag(c.getInt(c.getColumnIndex("study_flag")));
            units.add(wunit);
        }
        c.close();
        return units;
    }

    public void insert(int unit) {
        try{
            String sql = "insert into words_unit (unit) values (?)";
            db.execSQL(sql, new Integer[]{unit});
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }

    public void updateStudyFlag(int unit, int flag) {
        try{
            String sql = "update words_unit set flag = ? where unit = ?";
            db.execSQL(sql, new Integer[]{flag, unit});
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }

    public void updateReviewLevel(int unit, int level) {
        try{
            String sql = "update words_unit set review_level = ? where unit = ?";
            db.execSQL(sql, new Integer[]{level, unit});
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }

    public void updateLastWordIndex(int unit, int index) {
        try{
            String sql = "update words_unit set last_word_index = ? where unit = ?";
            db.execSQL(sql, new Integer[]{index, unit});
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }

    public void updateYesRate(int unit, int rate) {
        try{
            String sql = "update words_unit set yesrate = ? where unit = ?";
            db.execSQL(sql, new Integer[]{rate, unit});
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }
}
