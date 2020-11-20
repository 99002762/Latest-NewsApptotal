package com.example.latestnewsapp.database;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.Nullable;
import android.util.Log;


import com.example.latestnewsapp.database.FeedReaderContract.FeedEntry;

/**
 * This class will help me create a  db  n a  table in db
 */
public class DbHelper  extends SQLiteOpenHelper {

    private static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE " + FeedEntry.TABLE_NAME + " (" +
                    FeedEntry._ID + " INTEGER PRIMARY KEY," +
                    FeedEntry.COLUMN_NAME_TITLE + " TEXT," +
                    FeedEntry.COLUMN_NAME_SUBTITLE + " TEXT)";

    private static final String SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS " + FeedEntry.TABLE_NAME;
    private static final String TAG = DbHelper.class.getSimpleName() ;

    public DbHelper(@Nullable Context context) {

        super(context, "notesdb", null, 1);
        Log.i(TAG,"db created");


    }

    /**
     * this method will be called for the first time when the db is created
     * @param db
     */
    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.i(TAG,"onCreate table");
        //create table tablename(id integer primary key not null, title VARCHAR notnull, subtitle varchar notnull);
        db.execSQL(SQL_CREATE_ENTRIES);
    }


    /**
     * when app is updated from ver 1-2 you may want to add new columns or delete some cols in new version
     * @param db
     * @param oldVersion
     * @param newVersion
     */
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.i(TAG,"onUpgrade");

    }
}
