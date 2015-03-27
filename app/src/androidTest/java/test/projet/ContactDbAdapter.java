package test.projet;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by nicol_000 on 27/03/2015.
 */
public class ContactDbAdapter {

    // définition des attributs
    public static final String KEY_ROWID = "_id";
    public static final String KEY_ROWNOM = "_nom";
    public static final String KEY_ROWPRENOM = "_prenom";
    public static final String KEY_ROWNUM = "_telephone";
    public static final String KEY_ROWMAIL = "_email";

    // définition de la BD
    private static final String TAG = "Contacts";
    private DatabaseHelper mDbHelper;
    private SQLiteDatabase mDb;

    private static final String DATABASE_CREATE =
            "create table taches (_id integer primary key autoincrement, _nom text not null,_prenom text not null,_telephone text not null,_email text not null);";

    private static final String DATABASE_NAME = "contacts_db";
    private static final String DATABASE_TABLE = "contacts";
    private static final int DATABASE_VERSION = 2;

    private final Context mCtx;

    //dbHelper
    private static class DatabaseHelper extends SQLiteOpenHelper {

        DatabaseHelper(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {

            db.execSQL(DATABASE_CREATE);
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            Log.w(TAG, "Upgrading database from version " + oldVersion + " to "
                    + newVersion + ", which will destroy all old data");
            db.execSQL("DROP TABLE IF EXISTS contacts");
            onCreate(db);
        }
    }

    public TachesDbAdapter(Context ctx) {
        this.mCtx = ctx;
    }




}
