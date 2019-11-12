package com.example.contentprovider;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteQueryBuilder;
import android.net.Uri;

import androidx.annotation.Nullable;

import java.net.URI;

public class UserProvider extends ContentProvider {
    public static final String AUTHORITY = "com.tmt.my.user.provider";
    public static final String TABLE_NAME = "user";
    public static final Uri CONTENT_URI_1 = Uri.parse("content://" + AUTHORITY);

    static UriMatcher uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);

    static {
        // allow 2 type of uri , select all , and select with specific id
        uriMatcher.addURI(AUTHORITY, TABLE_NAME, 1);
        uriMatcher.addURI(AUTHORITY, "user/#", 2);
    }

    private SQLiteDatabase db;

    public UserProvider() {
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        // Implement this to handle requests to delete one or more rows.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public String getType(Uri uri) {
        // TODO: Implement this to handle requests for the MIME type of the data
        // at the given URI.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public Uri insert(Uri uri, ContentValues values) {

        long row = db.insert(TABLE_NAME , null , values);
        if(row > 0)
        {
            uri = ContentUris.withAppendedId(CONTENT_URI_1 , row);
            getContext().getContentResolver().notifyChange(uri , null);
        }

        return uri;

    }

    @Override
    public boolean onCreate() {
        CoreDatabase coreDatabase = new CoreDatabase(getContext());
        db = coreDatabase.getWritableDatabase();
        if (db != null) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public Cursor query(Uri uri, String[] projection, String selection,
                        String[] selectionArgs, String sortOrder) {

        SQLiteQueryBuilder myQuery = new SQLiteQueryBuilder();
        myQuery.setTables(TABLE_NAME);

        Cursor cr = myQuery.query(db , null , null , null ,null,null,"id");

        cr.setNotificationUri(getContext().getContentResolver() , uri);
        return cr;
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection,
                      String[] selectionArgs) {
        // TODO: Implement this to handle requests to update one or more rows.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    public class CoreDatabase extends SQLiteOpenHelper {
        private static final String DB_NAME = "TMT";
        private static final String TABLE_NAME = "user";
        private static final int VERSION = 1;

        public CoreDatabase(Context context) {
            super(context, DB_NAME, null, VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL("CREATE TABLE " + TABLE_NAME + " (id integer primary key autoincrement, name text , profession text);");
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME + ";");
            this.onCreate(db);
        }
    }
}
