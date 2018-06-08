package com.example.hp.offermagnet;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by ASUS on 29/04/2018.
 */

public class UserDatabaseHandler extends SQLiteOpenHelper {
    public UserDatabaseHandler(Context context, String DATABASE_NAME, SQLiteDatabase.CursorFactory factory, int DATABASE_VERSION) {
        super(context, DATABASE_NAME, factory, DATABASE_VERSION);
    }
    static final int  DATABASE_VERSION =1;
    static final String DATABASE_NAME="OfferMagnet";
    final String KEY_NAME="username";
    final String TABLE_NAME ="user";

    final String KEY_ID="id";
    final  String KEY_GENDER="gender";
    final String KEY_BIRTHDATE="birthDate";
    final String KEY_CITY="city";
    final String KEY_PHONE="phone";
    String create="CREATE TABLE IF NOT EXISTS "+TABLE_NAME+" ( "+KEY_ID + " INTEGER PRIMARY KEY , " + KEY_NAME
            + " TEXT , " +KEY_PHONE +" TEXT , "+ KEY_BIRTHDATE + " TEXT , "+  KEY_GENDER
            + " TEXT ,"+ KEY_CITY
            + " TEXT ) " ;








    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(create);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }
    public void insert(User user){
        SQLiteDatabase db=this.getWritableDatabase();

        ContentValues cv=new ContentValues();
        cv.put(KEY_ID,user.getId());
        cv.put(KEY_NAME,user.getUserName());
        cv.put(KEY_PHONE,user.getPhone());
        cv.put(KEY_BIRTHDATE,user.getBirthDate());

        cv.put( KEY_GENDER,user.getCity());
        cv.put(KEY_CITY,user.getCity());
        db.insert(TABLE_NAME, null, cv);
        db.close();
    }

public int getId(){
    SQLiteDatabase db= this.getReadableDatabase();
    String Query="SELECT "+  KEY_ID+" FROM "+TABLE_NAME ;
    Cursor cursor=db.rawQuery(Query, null);
    if(cursor !=null){
        cursor.moveToFirst();
    }
User user=new User();
    user.setId(Integer.parseInt(cursor.getString(0)));
  return Integer.parseInt(cursor.getString(0));


}
public User getForRequest(){
    SQLiteDatabase db= this.getReadableDatabase();
    String Query="SELECT "+KEY_BIRTHDATE+" ,"+  KEY_GENDER+", "+  KEY_CITY+" FROM "+TABLE_NAME ;
    Cursor cursor=db.rawQuery(Query, null);
    User user=new User((cursor.getString(0)),cursor.getString(1),cursor.getString(2));
    return user;
}

}
