package at.rene.myvaccreg.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class UserMemoDBHelper extends SQLiteOpenHelper {
    private static final String LOG_TAG = UserMemoDBHelper.class.getSimpleName();

    public static final String DB_NAME = "vaccines.db";
    public static final int DB_VERSION = 11;

    public static final String TABLE_USER_VACCINES = "user_vaccines";
    public static final String COLUMN_NAME = "name";
    public static final String COLUMN_VACCINATION = "vaccination";
    public static final String COLUMN_DATE = "date";
    private static final String DROP_TABLE ="DROP TABLE IF EXISTS "+TABLE_USER_VACCINES;

    public static final String SQL_CREATE =
            "CREATE TABLE " + TABLE_USER_VACCINES +
                    "("+ COLUMN_VACCINATION+ " TEXT PRIMARY KEY, " +
                    COLUMN_NAME + " TEXT NOT NULL, " +
                    COLUMN_DATE + " TEXT NOT NULL);";

    public UserMemoDBHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
        Log.d(LOG_TAG, "DbHelper hat die Datenbank: " + getDatabaseName() + " erzeugt.");
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        try {
            Log.d(LOG_TAG, "Die Tabelle wird mit SQL-Befehl: " + SQL_CREATE + " angelegt.");
            db.execSQL(SQL_CREATE);
        }
        catch (Exception ex) {
            Log.e(LOG_TAG, "Fehler beim Anlegen der Tabelle: " + ex.getMessage());
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        try {
            db.execSQL(DROP_TABLE);
            onCreate(db);
        }catch (Exception e) {
        }
    }
}
