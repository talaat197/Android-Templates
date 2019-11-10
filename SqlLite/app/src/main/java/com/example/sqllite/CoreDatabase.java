package com.example.sqllite;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;


public class CoreDatabase extends SQLiteOpenHelper {
    private static final String DB_NAME = "Work";
    private static final String DB_TABLE = "user";
    private static final int DB_VERSION = 1;

    Context context;
    SQLiteDatabase DB;

    public CoreDatabase(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE "+DB_TABLE+" (id integer primary key autoincrement , name text , profession text); ");
        Log.i("Database" , "Table Created");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+DB_TABLE);
        onCreate(db);
    }

    public void insertData(String name , String profession)
    {
        DB = getWritableDatabase();

        DB.execSQL("insert into "+DB_TABLE+" (name , profession) values('"+name+"' , '"+profession+"');");
        Toast.makeText(context , "Data Saved" , Toast.LENGTH_SHORT).show();
    }

    public String[] getAll()
    {
        DB = getReadableDatabase();
        Cursor cursor = DB.rawQuery("SELECT * FROM "+DB_TABLE+";" , null);
        String data[] = new String[2];
        while(cursor.moveToNext())
        {
            data[0] = cursor.getString(1);
            data[1] = cursor.getString(2);
        }
        return data;
    }
}











