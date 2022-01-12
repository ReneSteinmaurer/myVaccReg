package at.rene.myvaccreg.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class VaccinesMemoDBHelper extends SQLiteOpenHelper {
    private static final String LOG_TAG = VaccinesMemoDBHelper.class.getSimpleName();

    public static final String DB_NAME = "vaccines.db";
    public static final int DB_VERSION = 11;

    public static final String TABLE_VACCINES = "vaccines_list";
    public static final String COLUMN_NAME = "name";
    public static final String COLUMN_DESCRIPTION = "description";
    public static final String COLUMN_DATE = "date";
    public static final String COLUMN_ILLNESS = "illness";
    public static final String COLUMN_MANUFACTURER = "manufacturer";
    private static final String DROP_TABLE ="DROP TABLE IF EXISTS "+TABLE_VACCINES;

    public static final String SQL_CREATE =
            "CREATE TABLE " + TABLE_VACCINES +
                    "("+ COLUMN_NAME+ " TEXT PRIMARY KEY, " +
                    COLUMN_DESCRIPTION + " TEXT NOT NULL, " +
                    COLUMN_ILLNESS + " TEXT NOT NULL, " +
                    COLUMN_MANUFACTURER + " TEXT NOT NULL, " +
                    COLUMN_DATE + " TEXT NOT NULL);";

    public VaccinesMemoDBHelper(Context context) {
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
