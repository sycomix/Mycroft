package www.mabase.tech.mycroft.Databases;

/**
 * Created by Chris on 10/25/2017.
 */

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import www.mabase.tech.mycroft.Databases.MycrofDatabaseContract.*;

/**
 * The design of this database is supposed to be a simplistic recreation of Adapts database in the standard Python Mycroft. Adapt itself will load all of the details for parsing from its internal database, rather than trying to load them from skills at runtime.
 */

public class MycroftDbHelper extends SQLiteOpenHelper {

    private static final String VOCAB_CREATE_ENTRIES =
            "CREATE TABLE " + MycroftDatabase.VOCAB_TABLE_NAME + " (" +
                    MycroftDatabase._ID + " INTEGER PRIMARY KEY," +
                    MycroftDatabase.COLUMN_NAME_SKILL + " TEXT," +
                    MycroftDatabase.COLUMN_NAME_FILENAME + " TEXT," +
                    MycroftDatabase.COLUMN_NAME_VARIABLE + " TEXT)";
    private static final String REGEX_CREATE_ENTRIES =
            "CREATE TABLE " + MycroftDatabase.REGEX_TABLE_NAME + " (" +
                    MycroftDatabase._ID + " INTEGER PRIMARY KEY," +
                    MycroftDatabase.COLUMN_NAME_SKILL + " TEXT," +
                    MycroftDatabase.COLUMN_NAME_FILENAME + " TEXT," +
                    MycroftDatabase.COLUMN_NAME_VARIABLE + " TEXT)";
    private static final String INTENT_CREATE_ENTRIES =
            "CREATE TABLE " + MycroftDatabase.INTENT_TABLE_NAME + " (" +
                    MycroftDatabase._ID + " INTEGER PRIMARY KEY," +
                    MycroftDatabase.COLUMN_NAME_SKILL + " TEXT," +
                    MycroftDatabase.COLUMN_NAME_INTENT + " TEXT," +
                    MycroftDatabase.COLUMN_NAME_FILENAME + " TEXT," +
                    //increases, based on order. Simple counter
                    MycroftDatabase.COLUMN_NAME_ORDER + " INTEGER," +
                    //0 = yes, 1 = optional, 2 = other, 3 = etc
                    MycroftDatabase.COLUMN_NAME_OPTIONAL + " INTEGER)";
    private static final String VOCAB_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS " + MycroftDatabase.VOCAB_TABLE_NAME;
    private static final String REGEX_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS " + MycroftDatabase.REGEX_TABLE_NAME;
    private static final String INTENT_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS " + MycroftDatabase.INTENT_TABLE_NAME;

        // If you change the database schema, you must increment the database version.
        public static final int DATABASE_VERSION = 1;
        public static final String DATABASE_NAME = "FeedReader.db";

        // This initializes the database
        public MycroftDbHelper(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
        }

        public void onCreate(SQLiteDatabase db) {
            db.execSQL(VOCAB_CREATE_ENTRIES);
            db.execSQL(REGEX_CREATE_ENTRIES);
            db.execSQL(INTENT_CREATE_ENTRIES);
        }
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            // This database is only a cache for online data, so its upgrade policy is
            // to simply to discard the data and start over
            db.execSQL(VOCAB_DELETE_ENTRIES);
            db.execSQL(REGEX_DELETE_ENTRIES);
            db.execSQL(INTENT_DELETE_ENTRIES);
            onCreate(db);
        }
        public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgrade(db, oldVersion, newVersion);
    }
}
