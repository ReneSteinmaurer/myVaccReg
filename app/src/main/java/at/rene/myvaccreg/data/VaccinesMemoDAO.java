package at.rene.myvaccreg.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

public class VaccinesMemoDAO {
    private static final String LOG_TAG = VaccinesMemoDAO.class.getSimpleName();

    private SQLiteDatabase database;
    private VaccinesMemoDBHelper dbHelper;

    public long insertData(String name, String desc, String date, String illness, String manufacturer)
    {
        SQLiteDatabase dbb = dbHelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(dbHelper.COLUMN_NAME, name);
        contentValues.put(dbHelper.COLUMN_DESCRIPTION, desc);
        contentValues.put(dbHelper.COLUMN_DATE, date);
        contentValues.put(dbHelper.COLUMN_ILLNESS, illness);
        contentValues.put(dbHelper.COLUMN_MANUFACTURER, manufacturer);
        long id = dbb.insert(dbHelper.TABLE_VACCINES, null , contentValues);
        return id;
    }

    public String getData()
    {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        String[] columns = {dbHelper.COLUMN_NAME,dbHelper.COLUMN_DESCRIPTION,dbHelper.COLUMN_DATE, dbHelper.COLUMN_ILLNESS, dbHelper.COLUMN_MANUFACTURER};
        Cursor cursor =db.query(dbHelper.TABLE_VACCINES,columns,null,null,null,null,null);
        StringBuffer buffer= new StringBuffer();
        while (cursor.moveToNext())
        {
            String name =cursor.getString(cursor.getColumnIndexOrThrow(dbHelper.COLUMN_NAME));
            String  desc =cursor.getString(cursor.getColumnIndexOrThrow(dbHelper.COLUMN_DESCRIPTION));
            String  date =cursor.getString(cursor.getColumnIndexOrThrow(dbHelper.COLUMN_DATE));
            String  illness =cursor.getString(cursor.getColumnIndexOrThrow(dbHelper.COLUMN_ILLNESS));
            String  manufacturer =cursor.getString(cursor.getColumnIndexOrThrow(dbHelper.COLUMN_MANUFACTURER));
            buffer.append(name + ";" + desc +";" + date + "\n");
        }
        return buffer.toString();
    }

    public  int delete(String uname)
    {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        String[] whereArgs ={uname};

        int count =db.delete(dbHelper.TABLE_VACCINES ,dbHelper.COLUMN_NAME+" = ?",whereArgs);
        return  count;
    }

    public int updateName(String oldName , String newName)
    {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(dbHelper.COLUMN_NAME,newName);
        String[] whereArgs= {oldName};
        int count =db.update(dbHelper.TABLE_VACCINES,contentValues, dbHelper.COLUMN_NAME+" = ?",whereArgs );
        return count;
    }

    public VaccinesMemoDAO(Context context) {
        Log.d(LOG_TAG, "Unsere DataSource erzeugt jetzt den dbHelper.");
        dbHelper = new VaccinesMemoDBHelper(context);
    }
}
