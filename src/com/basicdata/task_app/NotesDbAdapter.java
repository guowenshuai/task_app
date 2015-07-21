package com.basicdata.task_app;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.sql.SQLException;
import java.util.Date;

/**
 * Created by jky on 15-7-20.
 */
public class NotesDbAdapter {

    private Context mCtx = null;
    private DatabaseHelper dbHelper;
    private SQLiteDatabase db;

    private static final String DATABASE_NAME = "notes.db";
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_TABLE = "notes";
    private static final String  DATABASE_CREATE =
            "create table notes ("
                +"_id INTEGER PRIMARY KEY,"
                +"note TEXT NOT NULL,"
                +"created INTEGER"
            +");";

    private static class DatabaseHelper extends SQLiteOpenHelper {

        public DatabaseHelper(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            System.out.println("oncreate db");
            db.execSQL(DATABASE_CREATE);
            Log.v("notes", "over");
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            db.execSQL("DROP TABLE IF EXISTS" + DATABASE_TABLE);
            onCreate(db);
        }
    }
    /*Constructor*/
    public NotesDbAdapter(Context ctx) {
        this.mCtx = ctx;

    }

    public NotesDbAdapter open() throws SQLException {
        System.out.println("open db");
        dbHelper = new DatabaseHelper(mCtx);
        db = dbHelper.getWritableDatabase();
        return this;
    }

    public void close() {
        dbHelper.close();
    }

    public static final String KEY_ROWID = "_id";
    public static final String KEY_NOTE = "note";
    public static final String KEY_CREATED = "created";

    String[] strCols = new String[] {
            KEY_ROWID,
            KEY_NOTE,
            KEY_CREATED
    };

    public Cursor getall() {
        /*查询语句*/
//        return db.rawQuery("SELECT * FROM notes", null);

        /*query 查询语句*/
        return db.query(DATABASE_TABLE, /*which table to select 查询的数据表的名称*/
                strCols,    /*which columns to return   查询的数据表的字段*/
                null,   /*where clause ****** selection WHERE语句*/
                null,   /*where arguments *******   selectionArgs WHERE语句的参数*/
                null,   /*group by clause   ******  groupBy GROUP BY 语句*/
                null,   /*having clause ******  having语句*/
                null   /*Order-by clause  ********  order-by 语句*/
                );
    }

    public long create(String Note) {
        Date now = new Date();
        ContentValues args = new ContentValues();
        args.put(KEY_NOTE, Note);
        args.put(KEY_CREATED, now.getTime());

        return db.insert(DATABASE_TABLE, null, args);
    }
}
