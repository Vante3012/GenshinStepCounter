package com.example.aether_walker;

import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class DBHelper extends SQLiteOpenHelper {

    private static String DB_PATH = "/data/data/com.example.aether_walker.databases/databases/";
    private static String DB_NAME = "AetherDb";
    private SQLiteDatabase db;
    private Context myContext;

    public DBHelper(Context context){
        super(context, DB_NAME,null,1);
        this.myContext = context;
    }

    public void createDataBase() throws IOException {
        boolean dbExist = checkDataBase();
        if (dbExist) {
            //do nothing database already exist
        } else {
            //By calling this method and empty database will be created into the default system path
            //of your application so we are gonna be able to overwrite that database with our database.
            this.getWritableDatabase();
            try {
                copyDataBase();
            } catch (IOException e) {
                throw new Error("Error copying database");
            }
        }
    }

    private boolean checkDataBase() {
        SQLiteDatabase checkDB = null;
        try {
            String myPath = DB_PATH + DB_NAME;
            checkDB = SQLiteDatabase.openDatabase(myPath+".db", null, SQLiteDatabase.OPEN_READONLY);
    } catch (SQLiteException e){
        //database doesn't exist yet.
    }
    if (checkDB != null) {
        checkDB.close();
    }
        return checkDB != null? true : false;
    }

    public void copyDataBase() throws IOException {
        //Open your local db as the input file
        InputStream myInput = myContext.getAssets().open(DB_NAME + ".db");
        // Path to the just created empty db
        String outFileName = DB_PATH + DB_NAME;
        //Open the empty do file that was created by DBHelper as an output file
        OutputStream myOutput = new FileOutputStream(outFileName);

        //transfer bytes from the input file to the output file
        byte[] buffer = new byte[1024];
        int length;
        while ((length = myInput.read(buffer)) > 0) {
            myOutput.write(buffer, 0, length);
        }

        //Close the streams or the output file
        myOutput.flush();//write to output file
        myOutput.close();
        myInput.close();
    }

    public void openDataBase() throws SQLException {

        //Open the database
        String myPath = DB_PATH + DB_NAME;
        db = SQLiteDatabase.openDatabase(myPath, null, SQLiteDatabase.OPEN_READWRITE);
    }

    @Override
    public synchronized void close() {

        if (db != null)
            db.close();

        super.close();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
