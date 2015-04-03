package projet.listecontact;

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
    public static final String KEY_ROWADRESSE="_adresse";

    // définition de la BD
    private static final String TAG = "Contacts";
    private DatabaseHelper mDbHelper;
    private SQLiteDatabase mDb;

    private static final String DATABASE_CREATE =
            "create table contacts (_id integer primary key autoincrement, _nom text not null,_prenom text,_telephone text not null,_email text,_adresse text);";

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

    public ContactDbAdapter(Context ctx) {
        this.mCtx = ctx;
    }

    public ContactDbAdapter open() throws SQLException {
        mDbHelper = new DatabaseHelper(mCtx);
        mDb = mDbHelper.getWritableDatabase();
        return this;
    }

    //fermeture de la BD
    public void close() {
        mDbHelper.close();
    }

    //creation d'un contact
    public long createContact(String nom, String prenom,String telephone,String mail,String adresse) {
        ContentValues initialValues = new ContentValues();
        initialValues.put(KEY_ROWNOM, nom);
        initialValues.put(KEY_ROWPRENOM, prenom);
        initialValues.put(KEY_ROWNUM, telephone);
        initialValues.put(KEY_ROWMAIL, mail);
        initialValues.put(KEY_ROWADRESSE, adresse);
        return mDb.insert(DATABASE_TABLE, null, initialValues);
    }

    //suppression dans la base de données retourne true si la suppression a bien été faite
    public boolean deleteContact(long rowId) {

        return mDb.delete(DATABASE_TABLE, KEY_ROWID + "=" + rowId, null) > 0;
    }

    public boolean deleteAll(){
        return mDb.delete(DATABASE_TABLE,KEY_ROWID +"<>"+ 0,null)>0;
    }

    //select *
    public Cursor fetchAll() {

        return mDb.query(DATABASE_TABLE, new String[] {KEY_ROWID,KEY_ROWNOM,KEY_ROWPRENOM,KEY_ROWNUM,KEY_ROWMAIL,KEY_ROWADRESSE}, null, null, null, null, "upper("+KEY_ROWNOM+")ASC");
    }

    //selection d'un contact
    public Cursor fetch(long rowId) throws SQLException {

        Cursor mCursor =

                mDb.query(true, DATABASE_TABLE, new String[] {KEY_ROWID,KEY_ROWNOM,KEY_ROWPRENOM,KEY_ROWNUM,KEY_ROWMAIL,KEY_ROWADRESSE} , KEY_ROWID + "=" + rowId, null,
                        null, null, null, null);
        if (mCursor != null) {
            mCursor.moveToFirst();
        }
        return mCursor;

    }

    //maj d'un contact
    public boolean updateContact(long rowId, String nom, String prenom,String telephone,String mail,String adresse) {
        ContentValues args = new ContentValues();
        args.put(KEY_ROWNOM, nom);
        args.put(KEY_ROWPRENOM, prenom);
        args.put(KEY_ROWNUM, telephone);
        args.put(KEY_ROWMAIL, mail);
        args.put(KEY_ROWADRESSE, adresse);
        return mDb.update(DATABASE_TABLE, args, KEY_ROWID + "=" + rowId, null) > 0;
    }




}
