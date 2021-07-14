package com.skydoves.magiclight_ble_control.model;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.HashMap;

public class DBHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "MyDBName.db";
    public static final String CONTACTS_TABLE_NAME = "contacts";
    public static final String CONTACTS_COLUMN_NAME = "name";
    public static final String CONTACTS_Column_Brightness = "brightness";
    public static final String CONTACTS_Column_PointX = "pointx";
    public static final String CONTACTS_Column_PointY = "pointy";
    public static final String CONTACTS_Column_Title = "title";
    public static final String CONTACTS_Column_Method = "method";
    public static final String CONTACTS_Column_Speed = "speed";

    public static final String table_color1 = "colorMode1";
    public static final String table_color2 = "colorMode2";
    public static final String table_color3 = "colorMode3";
    public static final String table_color4 = "colorMode4";
    public static final String table_color5 = "colorMode5";
    public static final String table_color6 = "colorMode6";
    public static final String table_color7 = "colorMode7";
    public static final String table_color8 = "colorMode8";
    public static final String table_color9 = "colorMode9";
    public static final String table_color0 = "colorMode0";
    private HashMap hp;


    public DBHelper(Context context) {
        super(context, DATABASE_NAME , null, 1);
    }

    public void onReCreate(int a) {
        SQLiteDatabase db = this.getWritableDatabase();
        if(a==1) {
            db.execSQL("DROP TABLE IF EXISTS " + table_color1);
            db.execSQL("create table " + table_color1 + "(id integer primary key, name text, brightness int, pointx int, pointy int, title text, method text, speed integer)");
        } else if (a==2) {
            db.execSQL("DROP TABLE IF EXISTS " + table_color2);
            db.execSQL("create table " + table_color2 + "(id integer primary key, name text, brightness int, pointx int, pointy int, title text, method text, speed integer)");
        } else if (a==3) {
            db.execSQL("DROP TABLE IF EXISTS " + table_color3);
            db.execSQL("create table " + table_color3 + "(id integer primary key, name text, brightness int, pointx int, pointy int, title text, method text, speed integer)");
        } else if (a==4) {
            db.execSQL("DROP TABLE IF EXISTS " + table_color4);
            db.execSQL("create table " + table_color4 + "(id integer primary key, name text, brightness int, pointx int, pointy int, title text, method text, speed integer)");
        } else if (a==5) {
            db.execSQL("DROP TABLE IF EXISTS " + table_color5);
            db.execSQL("create table " + table_color5 + "(id integer primary key, name text, brightness int, pointx int, pointy int, title text, method text, speed integer)");
        } else if (a==6) {
            db.execSQL("DROP TABLE IF EXISTS " + table_color6);
            db.execSQL("create table " + table_color6 + "(id integer primary key, name text, brightness int, pointx int, pointy int, title text, method text, speed integer)");
        } else if (a==7) {
            db.execSQL("DROP TABLE IF EXISTS " + table_color7);
            db.execSQL("create table " + table_color7 + "(id integer primary key, name text, brightness int, pointx int, pointy int, title text, method text, speed integer)");
        } else if (a==8) {
            db.execSQL("DROP TABLE IF EXISTS " + table_color8);
            db.execSQL("create table " + table_color8 + "(id integer primary key, name text, brightness int, pointx int, pointy int, title text, method text, speed integer)");
        } else if (a==9) {
            db.execSQL("DROP TABLE IF EXISTS " + table_color9);
            db.execSQL("create table " + table_color9 + "(id integer primary key, name text, brightness int, pointx int, pointy int, title text, method text, speed integer)");
        } else if (a==10) {
            db.execSQL("DROP TABLE IF EXISTS " + table_color0);
            db.execSQL("create table " + table_color0 + "(id integer primary key, name text, brightness int, pointx int, pointy int, title text, method text, speed integer)");
        }

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("DROP TABLE IF EXISTS " + table_color1);
        db.execSQL("create table " + table_color1 + "(id integer primary key, name text, brightness int, pointx int, pointy int, title text, method text, speed integer)");
        db.execSQL("DROP TABLE IF EXISTS " + table_color2);
        db.execSQL("create table " + table_color2 + "(id integer primary key, name text, brightness int, pointx int, pointy int, title text, method text, speed integer)");
        db.execSQL("DROP TABLE IF EXISTS " + table_color3);
        db.execSQL("create table " + table_color3 + "(id integer primary key, name text, brightness int, pointx int, pointy int, title text, method text, speed integer)");
        db.execSQL("DROP TABLE IF EXISTS " + table_color4);
        db.execSQL("create table " + table_color4 + "(id integer primary key, name text, brightness int, pointx int, pointy int, title text, method text, speed integer)");
        db.execSQL("DROP TABLE IF EXISTS " + table_color5);
        db.execSQL("create table " + table_color5 + "(id integer primary key, name text, brightness int, pointx int, pointy int, title text, method text, speed integer)");
        db.execSQL("DROP TABLE IF EXISTS " + table_color6);
        db.execSQL("create table " + table_color6 + "(id integer primary key, name text, brightness int, pointx int, pointy int, title text, method text, speed integer)");
        db.execSQL("DROP TABLE IF EXISTS " + table_color7);
        db.execSQL("create table " + table_color7 + "(id integer primary key, name text, brightness int, pointx int, pointy int, title text, method text, speed integer)");
        db.execSQL("DROP TABLE IF EXISTS " + table_color8);
        db.execSQL("create table " + table_color8 + "(id integer primary key, name text, brightness int, pointx int, pointy int, title text, method text, speed integer)");
        db.execSQL("DROP TABLE IF EXISTS " + table_color9);
        db.execSQL("create table " + table_color9 + "(id integer primary key, name text, brightness int, pointx int, pointy int, title text, method text, speed integer)");
        db.execSQL("DROP TABLE IF EXISTS " + table_color0);
        db.execSQL("create table " + table_color0 + "(id integer primary key, name text, brightness int, pointx int, pointy int, title text, method text, speed integer)");

        db.execSQL("DROP TABLE IF EXISTS count");
        db.execSQL("create table count " + "(id integer primary key, count_box integer)");

        db.execSQL("DROP TABLE IF EXISTS groupTable");
        db.execSQL("create table groupTable " + "(id integer primary key, name text)");

        db.execSQL("DROP TABLE IF EXISTS groupChildTable");
        db.execSQL("create table groupChildTable " + "(id integer primary key, name text, child text)");

        db.execSQL("DROP TABLE IF EXISTS DYITable");
        db.execSQL("create table DYITable " + "(id integer primary key, number text, bright text, color text, xpos text, ypos text)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + table_color1);
        onCreate(db);

    }

    public boolean insertColorMode1 (String name, int brightness, int pointx, int pointy, String title, String method, int speed) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("name", name);
        contentValues.put("brightness", brightness);
        contentValues.put("pointx", pointx);
        contentValues.put("pointy", pointy);
        contentValues.put("title", title);
        contentValues.put("method", method);
        contentValues.put("speed", speed);
        db.insert(table_color1, null, contentValues);
        return true;
    }
    public boolean insertColorMode2 (String name, int brightness, int pointx, int pointy, String title, String method, int speed) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("name", name);
        contentValues.put("brightness", brightness);
        contentValues.put("pointx", pointx);
        contentValues.put("pointy", pointy);
        contentValues.put("title", title);
        contentValues.put("method", method);
        contentValues.put("speed", speed);
        db.insert(table_color2, null, contentValues);
        return true;
    }
    public boolean insertColorMode3 (String name, int brightness, int pointx, int pointy, String title, String method, int speed) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("name", name);
        contentValues.put("brightness", brightness);
        contentValues.put("brightness", brightness);
        contentValues.put("pointx", pointx);
        contentValues.put("pointy", pointy);
        contentValues.put("title", title);
        contentValues.put("method", method);
        contentValues.put("speed", speed);
        db.insert(table_color3, null, contentValues);
        return true;
    }
    public boolean insertColorMode4 (String name, int brightness, int pointx, int pointy, String title, String method, int speed) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("name", name);
        contentValues.put("brightness", brightness);
        contentValues.put("pointx", pointx);
        contentValues.put("pointy", pointy);
        contentValues.put("title", title);
        contentValues.put("method", method);
        contentValues.put("speed", speed);
        db.insert(table_color4, null, contentValues);
        return true;
    }
    public boolean insertColorMode5 (String name, int brightness, int pointx, int pointy, String title, String method, int speed) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("name", name);
        contentValues.put("brightness", brightness);
        contentValues.put("pointx", pointx);
        contentValues.put("pointy", pointy);
        contentValues.put("title", title);
        contentValues.put("method", method);
        contentValues.put("speed", speed);
        db.insert(table_color5, null, contentValues);
        return true;
    }
    public boolean insertColorMode6 (String name, int brightness, int pointx, int pointy, String title, String method, int speed) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("name", name);
        contentValues.put("brightness", brightness);
        contentValues.put("pointx", pointx);
        contentValues.put("pointy", pointy);
        contentValues.put("title", title);
        contentValues.put("method", method);
        contentValues.put("speed", speed);
        db.insert(table_color6, null, contentValues);
        return true;
    }
    public boolean insertColorMode7 (String name, int brightness, int pointx, int pointy, String title, String method, int speed) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("name", name);
        contentValues.put("brightness", brightness);
        contentValues.put("pointx", pointx);
        contentValues.put("pointy", pointy);
        contentValues.put("title", title);
        contentValues.put("method", method);
        contentValues.put("speed", speed);
        db.insert(table_color7, null, contentValues);
        return true;
    }
    public boolean insertColorMode8 (String name, int brightness, int pointx, int pointy, String title, String method, int speed) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("name", name);
        contentValues.put("brightness", brightness);
        contentValues.put("pointx", pointx);
        contentValues.put("pointy", pointy);
        contentValues.put("title", title);
        contentValues.put("method", method);
        contentValues.put("speed", speed);
        db.insert(table_color8, null, contentValues);
        return true;
    }
    public boolean insertColorMode9 (String name, int brightness, int pointx, int pointy, String title, String method, int speed) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("name", name);
        contentValues.put("brightness", brightness);
        contentValues.put("pointx", pointx);
        contentValues.put("pointy", pointy);
        contentValues.put("title", title);
        contentValues.put("method", method);
        contentValues.put("speed", speed);
        db.insert(table_color9, null, contentValues);
        return true;
    }
    public boolean insertColorMode0 (String name, int brightness, int pointx, int pointy, String title, String method, int speed) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("name", name);
        contentValues.put("brightness", brightness);
        contentValues.put("pointx", pointx);
        contentValues.put("pointy", pointy);
        contentValues.put("title", title);
        contentValues.put("method", method);
        contentValues.put("speed", speed);
        db.insert(table_color0, null, contentValues);
        return true;
    }

    public void updateColorMode1 () {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("DROP TABLE IF EXISTS " + table_color1);
        db.execSQL("create table " + table_color1 + "(id integer primary key, name text, brightness int, pointx int, pointy int, title text, method text, speed integer)");

    }
    public void updateColorMode2 () {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("DROP TABLE IF EXISTS " + table_color2);
        db.execSQL("create table " + table_color2 + "(id integer primary key, name text, brightness int, pointx int, pointy int, title text, method text, speed integer)");
    }
    public void updateColorMode3 () {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("DROP TABLE IF EXISTS " + table_color3);
        db.execSQL("create table " + table_color3 + "(id integer primary key, name text, brightness int, pointx int, pointy int, title text, method text, speed integer)");

    }
    public void updateColorMode4 () {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("DROP TABLE IF EXISTS " + table_color4);
        db.execSQL("create table " + table_color4 + "(id integer primary key, name text, brightness int, pointx int, pointy int, title text, method text, speed integer)");
    }
    public void updateColorMode5 () {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("DROP TABLE IF EXISTS " + table_color5);
        db.execSQL("create table " + table_color5 + "(id integer primary key, name text, brightness int, pointx int, pointy int, title text, method text, speed integer)");
    }
    public void updateColorMode6 () {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("DROP TABLE IF EXISTS " + table_color6);
        db.execSQL("create table " + table_color6 + "(id integer primary key, name text, brightness int, pointx int, pointy int, title text, method text, speed integer)");
    }
    public void updateColorMode7 () {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("DROP TABLE IF EXISTS " + table_color7);
        db.execSQL("create table " + table_color7 + "(id integer primary key, name text, brightness int, pointx int, pointy int, title text, method text, speed integer)");
    }
    public void updateColorMode8 () {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("DROP TABLE IF EXISTS " + table_color8);
        db.execSQL("create table " + table_color8 + "(id integer primary key, name text, brightness int, pointx int, pointy int, title text, method text, speed integer)");
    }
    public void updateColorMode9 () {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("DROP TABLE IF EXISTS " + table_color9);
        db.execSQL("create table " + table_color9 + "(id integer primary key, name text, brightness int, pointx int, pointy int, title text, method text, speed integer)");
    }
    public void updateColorMode0 () {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("DROP TABLE IF EXISTS " + table_color0);
        db.execSQL("create table " + table_color0 + "(id integer primary key, name text, brightness int, pointx int, pointy int, title text, method text, speed integer)");
    }



    public Cursor getData(int id) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "select * from contacts where id="+id+"", null );
        return res;
    }

    public int numberOfRows(){
        SQLiteDatabase db = this.getReadableDatabase();
        int numRows = (int) DatabaseUtils.queryNumEntries(db, CONTACTS_TABLE_NAME);
        return numRows;
    }

    public boolean updateContact (Integer id, String name, String phone, String email, String street,String place) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("name", name);
        contentValues.put("phone", phone);
        contentValues.put("email", email);
        contentValues.put("street", street);
        contentValues.put("place", place);
        db.update("contacts", contentValues, "id = ? ", new String[] { Integer.toString(id) } );
        return true;
    }

    public Integer deleteContact (Integer id) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete("contacts",
                "id = ? ",
                new String[] { Integer.toString(id) });
    }

    public ArrayList<String> getColorMode1() {
        ArrayList<String> array_list = new ArrayList<String>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "select * from " + table_color1, null );
        res.moveToFirst();
        while(res.isAfterLast() == false){
            array_list.add(res.getString(res.getColumnIndex(CONTACTS_COLUMN_NAME)));
            res.moveToNext();
        }
        return array_list;
    }
    public ArrayList<String> getColorMode2() {
        ArrayList<String> array_list = new ArrayList<String>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "select * from " + table_color2, null );
        res.moveToFirst();
        while(res.isAfterLast() == false){
            array_list.add(res.getString(res.getColumnIndex(CONTACTS_COLUMN_NAME)));
            res.moveToNext();
        }
        return array_list;
    }
    public ArrayList<String> getColorMode3() {
        ArrayList<String> array_list = new ArrayList<String>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "select * from " + table_color3, null );
        res.moveToFirst();
        while(res.isAfterLast() == false){
            array_list.add(res.getString(res.getColumnIndex(CONTACTS_COLUMN_NAME)));
            res.moveToNext();
        }
        return array_list;
    }
    public ArrayList<String> getColorMode4() {
        ArrayList<String> array_list = new ArrayList<String>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "select * from " + table_color4, null );
        res.moveToFirst();
        while(res.isAfterLast() == false){
            array_list.add(res.getString(res.getColumnIndex(CONTACTS_COLUMN_NAME)));
            res.moveToNext();
        }
        return array_list;
    }
    public ArrayList<String> getColorMode5() {
        ArrayList<String> array_list = new ArrayList<String>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "select * from " + table_color5, null );
        res.moveToFirst();
        while(res.isAfterLast() == false){
            array_list.add(res.getString(res.getColumnIndex(CONTACTS_COLUMN_NAME)));
            res.moveToNext();
        }
        return array_list;
    }
    public ArrayList<String> getColorMode6() {
        ArrayList<String> array_list = new ArrayList<String>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "select * from " + table_color6, null );
        res.moveToFirst();
        while(res.isAfterLast() == false){
            array_list.add(res.getString(res.getColumnIndex(CONTACTS_COLUMN_NAME)));
            res.moveToNext();
        }
        return array_list;
    }
    public ArrayList<String> getColorMode7() {
        ArrayList<String> array_list = new ArrayList<String>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "select * from " + table_color7, null );
        res.moveToFirst();
        while(res.isAfterLast() == false){
            array_list.add(res.getString(res.getColumnIndex(CONTACTS_COLUMN_NAME)));
            res.moveToNext();
        }
        return array_list;
    }
    public ArrayList<String> getColorMode8() {
        ArrayList<String> array_list = new ArrayList<String>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "select * from " + table_color8, null );
        res.moveToFirst();
        while(res.isAfterLast() == false){
            array_list.add(res.getString(res.getColumnIndex(CONTACTS_COLUMN_NAME)));
            res.moveToNext();
        }
        return array_list;
    }
    public ArrayList<String> getColorMode9() {
        ArrayList<String> array_list = new ArrayList<String>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "select * from " + table_color9, null );
        res.moveToFirst();
        while(res.isAfterLast() == false){
            array_list.add(res.getString(res.getColumnIndex(CONTACTS_COLUMN_NAME)));
            res.moveToNext();
        }
        return array_list;
    }
    public ArrayList<String> getColorMode0() {
        ArrayList<String> array_list = new ArrayList<String>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "select * from " + table_color0, null );
        res.moveToFirst();
        while(res.isAfterLast() == false){
            array_list.add(res.getString(res.getColumnIndex(CONTACTS_COLUMN_NAME)));
            res.moveToNext();
        }
        return array_list;
    }

    public String getModeTitle1() {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "select * from " + table_color1, null );
        res.moveToFirst();
        String title = res.getString(res.getColumnIndex(CONTACTS_Column_Title));
        return title;
    }

    public String getModeTitle(int number) {
        SQLiteDatabase db = this.getReadableDatabase();
        String strColor = "'colorMode" + number + "'";
        String sql = "select * from " + strColor;
        Cursor res =  db.rawQuery( sql, null );
        res.moveToFirst();
        String title = res.getString(res.getColumnIndex(CONTACTS_Column_Title));
        return title;
    }

    public String getModeMethod(int number) {
        SQLiteDatabase db = this.getReadableDatabase();
        String strColor = "'colorMode" + number + "'";
        String sql = "select * from " + strColor;
        Cursor res =  db.rawQuery( sql, null );
        res.moveToFirst();
        String method = res.getString(res.getColumnIndex(CONTACTS_Column_Method));
        return method;
    }


    public int getModeSpeed1() {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "select * from " + table_color1, null );
        res.moveToFirst();
        int speed = res.getInt(res.getColumnIndex(CONTACTS_Column_Speed));
        return speed;
    }
    public int getModeSpeed2() {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "select * from " + table_color2, null );
        res.moveToFirst();
        int speed = res.getInt(res.getColumnIndex(CONTACTS_Column_Speed));
        return speed;
    }
    public int getModeSpeed3() {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "select * from " + table_color3, null );
        res.moveToFirst();
        int speed = res.getInt(res.getColumnIndex(CONTACTS_Column_Speed));
        return speed;
    }
    public int getModeSpeed4() {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "select * from " + table_color4, null );
        res.moveToFirst();
        int speed = res.getInt(res.getColumnIndex(CONTACTS_Column_Speed));
        return speed;
    }
    public int getModeSpeed5() {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "select * from " + table_color5, null );
        res.moveToFirst();
        int speed = res.getInt(res.getColumnIndex(CONTACTS_Column_Speed));
        return speed;
    }
    public int getModeSpeed6() {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "select * from " + table_color6, null );
        res.moveToFirst();
        int speed = res.getInt(res.getColumnIndex(CONTACTS_Column_Speed));
        return speed;
    }
    public int getModeSpeed7() {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "select * from " + table_color7, null );
        res.moveToFirst();
        int speed = res.getInt(res.getColumnIndex(CONTACTS_Column_Speed));
        return speed;
    }
    public int getModeSpeed8() {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "select * from " + table_color8, null );
        res.moveToFirst();
        int speed = res.getInt(res.getColumnIndex(CONTACTS_Column_Speed));
        return speed;
    }
    public int getModeSpeed9() {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "select * from " + table_color9, null );
        res.moveToFirst();
        int speed = res.getInt(res.getColumnIndex(CONTACTS_Column_Speed));
        return speed;
    }
    public int getModeSpeed0() {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "select * from " + table_color0, null );
        res.moveToFirst();
        int speed = res.getInt(res.getColumnIndex(CONTACTS_Column_Speed));
        return speed;
    }

    public ArrayList<Integer> getModeBrightness1() {
        ArrayList<Integer> arrayList = new ArrayList<Integer>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "select * from " + table_color1, null );
        res.moveToFirst();
        while(res.isAfterLast() == false){
            arrayList.add(res.getInt(res.getColumnIndex(CONTACTS_Column_Brightness)));
            res.moveToNext();
        }
        return arrayList;
    }
    public ArrayList<Integer> getModeBrightness2() {
        ArrayList<Integer> arrayList = new ArrayList<Integer>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "select * from " + table_color2, null );
        res.moveToFirst();
        while(res.isAfterLast() == false){
            arrayList.add(res.getInt(res.getColumnIndex(CONTACTS_Column_Brightness)));
            res.moveToNext();
        }
        return arrayList;
    }
    public ArrayList<Integer> getModeBrightness3() {
        ArrayList<Integer> arrayList = new ArrayList<Integer>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "select * from " + table_color3, null );
        res.moveToFirst();
        while(res.isAfterLast() == false){
            arrayList.add(res.getInt(res.getColumnIndex(CONTACTS_Column_Brightness)));
            res.moveToNext();
        }
        return arrayList;
    }
    public ArrayList<Integer> getModeBrightness4() {
        ArrayList<Integer> arrayList = new ArrayList<Integer>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "select * from " + table_color4, null );
        res.moveToFirst();
        while(res.isAfterLast() == false){
            arrayList.add(res.getInt(res.getColumnIndex(CONTACTS_Column_Brightness)));
            res.moveToNext();
        }
        return arrayList;
    }
    public ArrayList<Integer> getModeBrightness5() {
        ArrayList<Integer> arrayList = new ArrayList<Integer>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "select * from " + table_color5, null );
        res.moveToFirst();
        while(res.isAfterLast() == false){
            arrayList.add(res.getInt(res.getColumnIndex(CONTACTS_Column_Brightness)));
            res.moveToNext();
        }
        return arrayList;
    }
    public ArrayList<Integer> getModeBrightness6() {
        ArrayList<Integer> arrayList = new ArrayList<Integer>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "select * from " + table_color6, null );
        res.moveToFirst();
        while(res.isAfterLast() == false){
            arrayList.add(res.getInt(res.getColumnIndex(CONTACTS_Column_Brightness)));
            res.moveToNext();
        }
        return arrayList;
    }
    public ArrayList<Integer> getModeBrightness7() {
        ArrayList<Integer> arrayList = new ArrayList<Integer>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "select * from " + table_color7, null );
        res.moveToFirst();
        while(res.isAfterLast() == false){
            arrayList.add(res.getInt(res.getColumnIndex(CONTACTS_Column_Brightness)));
            res.moveToNext();
        }
        return arrayList;
    }
    public ArrayList<Integer> getModeBrightness8() {
        ArrayList<Integer> arrayList = new ArrayList<Integer>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "select * from " + table_color8, null );
        res.moveToFirst();
        while(res.isAfterLast() == false){
            arrayList.add(res.getInt(res.getColumnIndex(CONTACTS_Column_Brightness)));
            res.moveToNext();
        }
        return arrayList;
    }
    public ArrayList<Integer> getModeBrightness9() {
        ArrayList<Integer> arrayList = new ArrayList<Integer>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "select * from " + table_color9, null );
        res.moveToFirst();
        while(res.isAfterLast() == false){
            arrayList.add(res.getInt(res.getColumnIndex(CONTACTS_Column_Brightness)));
            res.moveToNext();
        }
        return arrayList;
    }
    public ArrayList<Integer> getModeBrightness0() {
        ArrayList<Integer> arrayList = new ArrayList<Integer>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "select * from " + table_color0, null );
        res.moveToFirst();
        while(res.isAfterLast() == false){
            arrayList.add(res.getInt(res.getColumnIndex(CONTACTS_Column_Brightness)));
            res.moveToNext();
        }
        return arrayList;
    }

    public ArrayList<Integer> getModePointX1() {
        ArrayList<Integer> arrayList = new ArrayList<Integer>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "select * from " + table_color1, null );
        res.moveToFirst();
        while(res.isAfterLast() == false){
            arrayList.add(res.getInt(res.getColumnIndex(CONTACTS_Column_PointX)));
            res.moveToNext();
        }
        return arrayList;
    }
    public ArrayList<Integer> getModePointX2() {
        ArrayList<Integer> arrayList = new ArrayList<Integer>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "select * from " + table_color2, null );
        res.moveToFirst();
        while(res.isAfterLast() == false){
            arrayList.add(res.getInt(res.getColumnIndex(CONTACTS_Column_PointX)));
            res.moveToNext();
        }
        return arrayList;
    }
    public ArrayList<Integer> getModePointX3() {
        ArrayList<Integer> arrayList = new ArrayList<Integer>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "select * from " + table_color3, null );
        res.moveToFirst();
        while(res.isAfterLast() == false){
            arrayList.add(res.getInt(res.getColumnIndex(CONTACTS_Column_PointX)));
            res.moveToNext();
        }
        return arrayList;
    }
    public ArrayList<Integer> getModePointX4() {
        ArrayList<Integer> arrayList = new ArrayList<Integer>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "select * from " + table_color4, null );
        res.moveToFirst();
        while(res.isAfterLast() == false){
            arrayList.add(res.getInt(res.getColumnIndex(CONTACTS_Column_PointX)));
            res.moveToNext();
        }
        return arrayList;
    }
    public ArrayList<Integer> getModePointX5() {
        ArrayList<Integer> arrayList = new ArrayList<Integer>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "select * from " + table_color5, null );
        res.moveToFirst();
        while(res.isAfterLast() == false){
            arrayList.add(res.getInt(res.getColumnIndex(CONTACTS_Column_PointX)));
            res.moveToNext();
        }
        return arrayList;
    }
    public ArrayList<Integer> getModePointX6() {
        ArrayList<Integer> arrayList = new ArrayList<Integer>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "select * from " + table_color6, null );
        res.moveToFirst();
        while(res.isAfterLast() == false){
            arrayList.add(res.getInt(res.getColumnIndex(CONTACTS_Column_PointX)));
            res.moveToNext();
        }
        return arrayList;
    }
    public ArrayList<Integer> getModePointX7() {
        ArrayList<Integer> arrayList = new ArrayList<Integer>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "select * from " + table_color7, null );
        res.moveToFirst();
        while(res.isAfterLast() == false){
            arrayList.add(res.getInt(res.getColumnIndex(CONTACTS_Column_PointX)));
            res.moveToNext();
        }
        return arrayList;
    }
    public ArrayList<Integer> getModePointX8() {
        ArrayList<Integer> arrayList = new ArrayList<Integer>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "select * from " + table_color8, null );
        res.moveToFirst();
        while(res.isAfterLast() == false){
            arrayList.add(res.getInt(res.getColumnIndex(CONTACTS_Column_PointX)));
            res.moveToNext();
        }
        return arrayList;
    }
    public ArrayList<Integer> getModePointX9() {
        ArrayList<Integer> arrayList = new ArrayList<Integer>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "select * from " + table_color9, null );
        res.moveToFirst();
        while(res.isAfterLast() == false){
            arrayList.add(res.getInt(res.getColumnIndex(CONTACTS_Column_PointX)));
            res.moveToNext();
        }
        return arrayList;
    }
    public ArrayList<Integer> getModePointX0() {
        ArrayList<Integer> arrayList = new ArrayList<Integer>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "select * from " + table_color0, null );
        res.moveToFirst();
        while(res.isAfterLast() == false){
            arrayList.add(res.getInt(res.getColumnIndex(CONTACTS_Column_PointX)));
            res.moveToNext();
        }
        return arrayList;
    }


    public ArrayList<Integer> getModePointY1() {
        ArrayList<Integer> arrayList = new ArrayList<Integer>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "select * from " + table_color1, null );
        res.moveToFirst();
        while(res.isAfterLast() == false){
            arrayList.add(res.getInt(res.getColumnIndex(CONTACTS_Column_PointY)));
            res.moveToNext();
        }
        return arrayList;
    }
    public ArrayList<Integer> getModePointY2() {
        ArrayList<Integer> arrayList = new ArrayList<Integer>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "select * from " + table_color2, null );
        res.moveToFirst();
        while(res.isAfterLast() == false){
            arrayList.add(res.getInt(res.getColumnIndex(CONTACTS_Column_PointY)));
            res.moveToNext();
        }
        return arrayList;
    }
    public ArrayList<Integer> getModePointY3() {
        ArrayList<Integer> arrayList = new ArrayList<Integer>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "select * from " + table_color3, null );
        res.moveToFirst();
        while(res.isAfterLast() == false){
            arrayList.add(res.getInt(res.getColumnIndex(CONTACTS_Column_PointY)));
            res.moveToNext();
        }
        return arrayList;
    }
    public ArrayList<Integer> getModePointY4() {
        ArrayList<Integer> arrayList = new ArrayList<Integer>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "select * from " + table_color4, null );
        res.moveToFirst();
        while(res.isAfterLast() == false){
            arrayList.add(res.getInt(res.getColumnIndex(CONTACTS_Column_PointY)));
            res.moveToNext();
        }
        return arrayList;
    }
    public ArrayList<Integer> getModePointY5() {
        ArrayList<Integer> arrayList = new ArrayList<Integer>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "select * from " + table_color5, null );
        res.moveToFirst();
        while(res.isAfterLast() == false){
            arrayList.add(res.getInt(res.getColumnIndex(CONTACTS_Column_PointY)));
            res.moveToNext();
        }
        return arrayList;
    }
    public ArrayList<Integer> getModePointY6() {
        ArrayList<Integer> arrayList = new ArrayList<Integer>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "select * from " + table_color6, null );
        res.moveToFirst();
        while(res.isAfterLast() == false){
            arrayList.add(res.getInt(res.getColumnIndex(CONTACTS_Column_PointY)));
            res.moveToNext();
        }
        return arrayList;
    }
    public ArrayList<Integer> getModePointY7() {
        ArrayList<Integer> arrayList = new ArrayList<Integer>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "select * from " + table_color7, null );
        res.moveToFirst();
        while(res.isAfterLast() == false){
            arrayList.add(res.getInt(res.getColumnIndex(CONTACTS_Column_PointY)));
            res.moveToNext();
        }
        return arrayList;
    }
    public ArrayList<Integer> getModePointY8() {
        ArrayList<Integer> arrayList = new ArrayList<Integer>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "select * from " + table_color8, null );
        res.moveToFirst();
        while(res.isAfterLast() == false){
            arrayList.add(res.getInt(res.getColumnIndex(CONTACTS_Column_PointY)));
            res.moveToNext();
        }
        return arrayList;
    }
    public ArrayList<Integer> getModePointY9() {
        ArrayList<Integer> arrayList = new ArrayList<Integer>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "select * from " + table_color9, null );
        res.moveToFirst();
        while(res.isAfterLast() == false){
            arrayList.add(res.getInt(res.getColumnIndex(CONTACTS_Column_PointY)));
            res.moveToNext();
        }
        return arrayList;
    }
    public ArrayList<Integer> getModePointY0() {
        ArrayList<Integer> arrayList = new ArrayList<Integer>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "select * from " + table_color0, null );
        res.moveToFirst();
        while(res.isAfterLast() == false){
            arrayList.add(res.getInt(res.getColumnIndex(CONTACTS_Column_PointY)));
            res.moveToNext();
        }
        return arrayList;
    }




    public int getCount(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res =  db.rawQuery( "select * from count", null );
        res.moveToFirst();
        int a=0;
        while(res.isAfterLast() == false){
            a = res.getInt(res.getColumnIndex("count_box"));
            res.moveToNext();
        }
        return  a;
    }

    public void insertCount (int a) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("count_box", a);
        db.insert("count", null, contentValues);
    }

    public void insertGroup(String groupname) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("name", groupname);
        db.insert("groupTable", null, contentValues);
    }

    public ArrayList<String> getGroupName() {
        ArrayList<String> arrayList = new ArrayList<String>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "select * from " + "groupTable", null );
        res.moveToFirst();
        while(res.isAfterLast() == false){
            arrayList.add(res.getString(res.getColumnIndex("name")));
            res.moveToNext();
        }
        return arrayList;
    }

    public void insertChildGroup(String groupname, String childname) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("name", groupname);
        contentValues.put("child", childname);
        db.insert("groupChildTable", null, contentValues);
    }

    public void insertDIYInfo(String strNo, String brightness, String colorName, String xposition, String yposition) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("number", strNo);
        contentValues.put("bright", brightness);
        contentValues.put("color", colorName);
        contentValues.put("xpos", xposition);
        contentValues.put("ypos", yposition);
        db.insert("DYITable", null, contentValues);
    }

    public boolean updateDIYInfo(String strNo, String brightness, String colorName, String xposition, String yposition) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("number", strNo);
        contentValues.put("bright", brightness);
        contentValues.put("color", colorName);
        contentValues.put("xpos", xposition);
        contentValues.put("ypos", yposition);
        db.update("DYITable", contentValues, "number = ? ", new String[] { strNo } );
        return true;
    }

    public String getDYIColor(String strNo){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "select * from DYITable where number='" + strNo + "'";
        Cursor res =  db.rawQuery( query, null );
        res.moveToFirst();
        String color = "0";
        while(res.isAfterLast() == false){
            color = res.getString(res.getColumnIndex("color"));
            res.moveToNext();
        }
        return  color;
    }

    public String getDYIBright(String strNo){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "select * from DYITable where number='" + strNo + "'";
        Cursor res =  db.rawQuery( query, null );
        res.moveToFirst();
        String bright = "0";
        while(res.isAfterLast() == false){
            bright = res.getString(res.getColumnIndex("bright"));
            res.moveToNext();
        }
        return  bright;
    }

    public String getDYIXPos(String strNo){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "select * from DYITable where number='" + strNo + "'";
        Cursor res =  db.rawQuery( query, null );
        res.moveToFirst();
        String xpos = "0";
        while(res.isAfterLast() == false){
            xpos = res.getString(res.getColumnIndex("xpos"));
            res.moveToNext();
        }
        return  xpos;
    }

    public String getDYIYPos(String strNo){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "select * from DYITable where number='" + strNo + "'";
        Cursor res =  db.rawQuery( query, null );
        res.moveToFirst();
        String ypos = "0";
        while(res.isAfterLast() == false){
            ypos = res.getString(res.getColumnIndex("ypos"));
            res.moveToNext();
        }
        return  ypos;
    }

    public ArrayList<String> getGroupParentName() {
        ArrayList<String> arrayList = new ArrayList<String>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "select * from " + "groupChildTable", null );
        res.moveToFirst();
        while(res.isAfterLast() == false){
            arrayList.add(res.getString(res.getColumnIndex("name")));
            res.moveToNext();
        }
        return arrayList;
    }

    public ArrayList<String> getGroupChildName() {
        ArrayList<String> arrayList = new ArrayList<String>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "select * from " + "groupChildTable", null );
        res.moveToFirst();
        while(res.isAfterLast() == false){
            arrayList.add(res.getString(res.getColumnIndex("child")));
            res.moveToNext();
        }
        return arrayList;
    }

    public int deleteGroup(String groupname) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete("groupTable",
                "name = ? ",
                new String[] {groupname });
    }

    public boolean updateGroup(String oldname, String newname) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("name", newname);
        db.update("groupTable", contentValues, "name = ? ", new String[] { oldname } );
        return true;
    }

    public int deleteFromGroup(String parentname, String childname) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete("groupChildTable",
                "name = ? ",
                new String[] {parentname });
    }

    public boolean updateChild(String childOldname, String childNewname) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("child", childNewname);
        db.update("groupChildTable", contentValues, "child = ? ", new String[] { childOldname } );
        return true;
    }


}
