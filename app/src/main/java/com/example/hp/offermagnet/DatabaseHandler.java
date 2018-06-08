package com.example.hp.offermagnet;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHandler  extends SQLiteOpenHelper{
   static final int DATABASE_VERSION=1;
    static final String DATABASE_NAME="contactsManager";
    final String TABLE_NAME="contacts";
    final String KEY_NAME="name";
    final String KEY_ID="id";
    final String KEY_PHONE="phone";

public  DatabaseHandler(Context context ){
    super(context,DATABASE_NAME,null,DATABASE_VERSION);
}
    @Override
    public void onCreate(SQLiteDatabase db) {
        String create="CREATE TABLE IF NOT EXISTS "+TABLE_NAME+" ( "+KEY_ID+" INTEGER PRIMARY KEY , "+KEY_NAME+
                " TEXT "+KEY_PHONE +" TEXT )";
        db.execSQL(create);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
        onCreate(db);
    }
    public void insert(contact contacts){
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues cv=new ContentValues();
        cv.put(KEY_NAME,contacts.getName());
        cv.put(KEY_PHONE,contacts.getPhone());
        db.insert(TABLE_NAME,null,cv);
        db.close();
    }
    public  contact get(int id){
        SQLiteDatabase db=this.getReadableDatabase();
        Cursor cursor=db.query(TABLE_NAME,new String[]{KEY_ID,KEY_NAME,KEY_PHONE},KEY_ID +" =? "
                ,new  String[]{String.valueOf(id)},null,null,null,null);
        if(cursor!=null){
            cursor.moveToFirst();
        }
        contact contacts=new contact(Integer.parseInt(cursor.getString(0)),cursor.getString(1),cursor.getString(2));
        return  contacts;
    }


}
