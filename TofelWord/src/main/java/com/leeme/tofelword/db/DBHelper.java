package com.leeme.tofelword.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.os.Environment;
import android.util.Log;

import com.leeme.tofelword.R;

import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.File;

/**
 * DBHelper
 */
public class DBHelper {
    private static final String DATABASE_PATH = Environment.getExternalStorageDirectory().getAbsolutePath() + "/leeme";

    private DBHelper() {

    }

    public static SQLiteDatabase openDatabase(Context ctx, String dbname) {
        try{
            // 获得dictionary.db文件的绝对路径
            String databaseFilename = DATABASE_PATH + "/" + dbname;
            File dir = new File(DATABASE_PATH);
            // 如果/sdcard/dictionary目录中存在，创建这个目录
            if (!dir.exists()){
                dir.mkdir();
            }
            // 如果在/sdcard/dictionary目录中不存在
            // dictionary.db文件，则从res\raw目录中复制这个文件到
            // SD卡的目录（/sdcard/dictionary）
            if (!(new File(databaseFilename)).exists()){
                // 获得封装dictionary.db文件的InputStream对象
                InputStream is;
                if("words.db".equals(dbname)){
                    is = ctx.getResources().openRawResource(R.raw.words);
                }else{
                    is = ctx.getResources().openRawResource(R.raw.sentences);
                }
                FileOutputStream fos = new FileOutputStream(databaseFilename);
                byte[] buffer = new byte[8192];
                int count = 0;
                // 开始复制dictionary.db文件
                while ((count = is.read(buffer)) > 0)
                {
                    fos.write(buffer, 0, count);
                }

                fos.close();
                is.close();
            }
            // 打开/sdcard/dictionary目录中的dictionary.db文件
            SQLiteDatabase database = SQLiteDatabase.openOrCreateDatabase(databaseFilename, null);
            return database;
        } catch (Exception e){
            e.printStackTrace();
            Log.d("tofelword", e.getLocalizedMessage());
        }
        return null;
    }
}
