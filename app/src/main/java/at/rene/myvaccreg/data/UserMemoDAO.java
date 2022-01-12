package at.rene.myvaccreg.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

public class UserMemoDAO {
    private static final String LOG_TAG = VaccinesMemoDAO.class.getSimpleName();

    private SQLiteDatabase database;
    private UserMemoDBHelper dbHelper;

    public long insertData(String name, String vaccine, String date)
    {
        SQLiteDatabase dbb = dbHelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(dbHelper.COLUMN_VACCINATION, vaccine);
        contentValues.put(dbHelper.COLUMN_NAME, name);
        contentValues.put(dbHelper.COLUMN_DATE, date);
        long id = dbb.insert(dbHelper.TABLE_USER_VACCINES, null , contentValues);
        return id;
    }

    public String getData()
    {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        String[] columns = {dbHelper.COLUMN_NAME,dbHelper.COLUMN_VACCINATION,dbHelper.COLUMN_DATE};
        Cursor cursor =db.query(dbHelper.TABLE_USER_VACCINES,columns,null,null,null,null,null);
        StringBuffer buffer= new StringBuffer();
        while (cursor.moveToNext())
        {
            String vaccine =cursor.getString(cursor.getColumnIndexOrThrow(dbHelper.COLUMN_VACCINATION));
            String  name =cursor.getString(cursor.getColumnIndexOrThrow(dbHelper.COLUMN_NAME));
            String  date =cursor.getString(cursor.getColumnIndexOrThrow(dbHelper.COLUMN_DATE));
            buffer.append(vaccine + ";" + name +";" + date + "\n");
        }
        return buffer.toString();
    }

    public  int delete(String uname)
    {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        String[] whereArgs ={uname};

        int count =db.delete(dbHelper.TABLE_USER_VACCINES ,dbHelper.COLUMN_NAME+" = ?",whereArgs);
        return  count;
    }

    public int updateName(String oldName , String newName)
    {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(dbHelper.COLUMN_NAME,newName);
        String[] whereArgs= {oldName};
        int count =db.update(dbHelper.TABLE_USER_VACCINES,contentValues, dbHelper.COLUMN_NAME+" = ?",whereArgs );
        return count;
    }

    public UserMemoDAO(Context context) {
        Log.d(LOG_TAG, "Unsere DataSource erzeugt jetzt den dbHelper.");
        dbHelper = new UserMemoDBHelper(context);
    }
}
