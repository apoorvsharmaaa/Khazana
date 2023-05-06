package com.example.tsp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {
    private String TABLE_NAME = "user_table";
    private String TABLE_NAME1 = "transfers_table";

    public DatabaseHelper(@Nullable Context context) {
        super(context, "User.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + TABLE_NAME +" (PHONENUMBER INTEGER PRIMARY KEY ,NAME TEXT,BALANCE DECIMAL,EMAIL VARCHAR,ACCOUNT_NO VARCHAR,IFSC_CODE VARCHAR)");
        db.execSQL("create table " + TABLE_NAME1 +" (TRANSACTIONID INTEGER PRIMARY KEY AUTOINCREMENT,DATE TEXT,FROMNAME TEXT,TONAME TEXT,AMOUNT DECIMAL,STATUS TEXT)");
        db.execSQL("insert into user_table values(1234567890,'Apoorv',9472.00,'apoorv.02@gmail.com','XXXXXXXXXXXX1234','ABC09876543')");
        db.execSQL("insert into user_table values(2345678901,'Stuti',582.67,'stuti.03@gmail.com','XXXXXXXXXXXX2341','BCA98765432')");
        db.execSQL("insert into user_table values(3456789012,'Arshad',1359.56,'arshu.04@gmail.com','XXXXXXXXXXXX3412','CAB87654321')");
        db.execSQL("insert into user_table values(4567890123,'Asis',1500.01,'asis.05@gmail.com','XXXXXXXXXXXX4123','ABC76543210')");
        db.execSQL("insert into user_table values(5678901234,'Farhan',2603.48,'farhu.06@gmail.com','XXXXXXXXXXXX2345','BCA65432109')");
        db.execSQL("insert into user_table values(6789012345,'Ayush',945.16,'ayush.07@gmail.com','XXXXXXXXXXXX3452','CAB54321098')");
        db.execSQL("insert into user_table values(7890123456,'Akriti',5936.00,'akriti.08@gmail.com','XXXXXXXXXXXX4523','ABC43210987')");
        db.execSQL("insert into user_table values(8901234567,'Vipul',857.22,'vipvipul.09@gmail.com','XXXXXXXXXXXX5234','BCA32109876')");
        db.execSQL("insert into user_table values(9012345678,'Sallu',4398.46,'srk.10@gmail.com','XXXXXXXXXXXX3456','CAB21098765')");
        db.execSQL("insert into user_table values(1234567809,'Lawrence',273.90,'bishnoi.01@gmail.com','XXXXXXXXXXXX4563','ABC10987654')");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME1);
        onCreate(db);
    }

    public Cursor readalldata(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from user_table", null);
        return cursor;
    }

    public Cursor readparticulardata(String phonenumber){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from user_table where phonenumber = " +phonenumber, null);
        return cursor;
    }

    public Cursor readselectuserdata(String phonenumber) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from user_table except select * from user_table where phonenumber = " +phonenumber, null);
        return cursor;
    }

    public void updateAmount(String phonenumber, String amount){
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("update user_table set balance = " + amount + " where phonenumber = " +phonenumber);
    }

    public Cursor readtransferdata(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from transfers_table", null);
        return cursor;
    }

    public boolean insertTransferData(String date,String from_name, String to_name, String amount, String status){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("DATE", date);
        contentValues.put("FROMNAME", from_name);
        contentValues.put("TONAME", to_name);
        contentValues.put("AMOUNT", amount);
        contentValues.put("STATUS", status);
        Long result = db.insert(TABLE_NAME1, null, contentValues);
        if(result == -1){
            return false;
        }else{
            return true;
        }
    }
}